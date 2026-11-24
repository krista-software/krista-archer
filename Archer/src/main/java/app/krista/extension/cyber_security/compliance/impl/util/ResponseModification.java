/*
 * Archer Extension for Krista
 * Copyright (C) 2025 Krista Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package app.krista.extension.cyber_security.compliance.impl.util;

import app.krista.model.base.FreeForm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for transforming Archer API responses into table format for Krista.
 * <p>
 * This class provides methods to convert HTTP responses from the Archer API into
 * structured map objects that can be displayed as tables in the Krista platform.
 * </p>
 */
public class ResponseModification {

  private static final String STATUS_AND_MESSAGE = "Status and Message";

  private static final String RESPONSE = "Response";

  private static final String RESPONSE_INFO = "Response Info";



  private ResponseModification() {
  }

  /**
   * Converts an HTTP response from the Archer API into a table-formatted map.
   * <p>
   * The returned map contains two keys:
   * <ul>
   *   <li>"Response" - An ArrayList of LinkedHashMaps representing the response data as table rows</li>
   *   <li>"Response Info" - A FreeForm object containing the HTTP status code and message</li>
   * </ul>
   * For error responses, the method generates a structured error response with code and message.
   * </p>
   *
   * @param response the HTTP Response object from the Archer API
   * @return a map containing the formatted response data and status information,
   *         or an empty map if the response is null
   */
  public static Map<String, Object> responseToTable(Response response) {
    FreeForm freeForm = new FreeForm();
    if (response != null) {
      String responseBody = getResponseBody(response);
      freeForm.put(STATUS_AND_MESSAGE, "Text", response.code() + " " + response.message());
      ArrayList<LinkedHashMap<Object, Object>> responseBodyJsonToMap = convertJsonToTable(
              responseBody);
      if (!responseBodyJsonToMap.isEmpty()) {
        int maxSize = 0;
        int maxIndex = 0;
        for (int i = 0; i < responseBodyJsonToMap.size(); i++) {
          int currentSize = responseBodyJsonToMap.get(i).size();
          if (currentSize > maxSize) {
            maxSize = currentSize;
            maxIndex = i;
          }
        }
        if (maxIndex > 0) {
          Collections.swap(responseBodyJsonToMap, 0, maxIndex);
        }
      }

      Map<String, Object> responseDetails = new HashMap<>();
      responseDetails.put(RESPONSE, responseBodyJsonToMap);
      responseDetails.put(RESPONSE_INFO, freeForm);
      return responseDetails;
    }
    else{

      return Collections.emptyMap();
    }
  }

  /**
   * Converts a JSON string into a list of LinkedHashMaps for table representation.
   * <p>
   * Handles both JSON arrays and single JSON objects. For single objects, wraps
   * the result in a list with one element.
   * </p>
   *
   * @param jsonList the JSON string to convert
   * @return an ArrayList of LinkedHashMaps representing the JSON data, or an empty list on error
   */
  private static ArrayList<LinkedHashMap<Object, Object>> convertJsonToTable(String jsonList) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readTree(jsonList);

      if (jsonNode.isArray()) {
        return mapper.readValue(jsonList,
            new TypeReference<ArrayList<LinkedHashMap<Object, Object>>>() {
            });
      } else {
        Map<String, Object> singleObject = mapper.readValue(jsonList,
            new TypeReference<Map<String, Object>>() {
            });
        ArrayList<LinkedHashMap<Object, Object>> resultList = new ArrayList<>();
        LinkedHashMap<Object, Object> result = new LinkedHashMap<>();
        result.putAll(singleObject);
        resultList.add(result);
        return resultList;
      }
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  /**
   * Extracts the response body from an HTTP response.
   * <p>
   * For unsuccessful responses, generates a structured error response.
   * For successful responses, returns the raw body string.
   * </p>
   *
   * @param response the HTTP Response object
   * @return the response body as a string, an error JSON for failed responses, or empty JSON if null
   * @throws IllegalStateException if an I/O error occurs while reading the response body
   */
  private static String getResponseBody(Response response) {
    try {
      if(response != null) {
        if ( !response.isSuccessful()) {
          return errorResponse(response);
        }
        else if (response.body() != null) {
          return response.body().string();
        }
      }
    }
    catch (IOException cause) {
      throw new IllegalStateException("Failed to read Archer API response: " + cause.getMessage() + ". Please retry the request.",
          cause);
    }
    return Constant.EMPTY_JSON;
  }



  /**
   * Generates a JSON error response for failed HTTP requests.
   *
   * @param response the failed HTTP Response object, or null if no response was received
   * @return a JSON string containing the response code and error message
   */
  private static String errorResponse(Response response){
    Gson gson = Constant.GSON;
    JsonObject jsonObject = new JsonObject();

    if(response == null){
      jsonObject.addProperty("Response", "No response received from Archer. Please verify your connection and retry.");
     return  gson.toJson(jsonObject);
    }
    // Add key-value pairs to the JSON object
    jsonObject.addProperty("Response Code", response.code());
    jsonObject.addProperty("Response Message", response.message().isEmpty() ? "Request failed. Please verify your input parameters and try again." : response.message());

    // Convert the JSON object to a string
    return gson.toJson(jsonObject);

  }
}
