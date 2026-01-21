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

package app.krista.extension.cyber_security.compliance;

import app.krista.extension.cyber_security.compliance.impl.util.Constant;
import app.krista.extension.executor.Invoker;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jvnet.hk2.annotations.Service;

@Service
public class RestClient {

    public static final String AUTHORIZATION = "Authorization";

    public static final String ARCHER_SESSION_ID = "Archer session-id=";

    private final String username;

    private final String password;

    private final String instanceName;

    private final String userDomain;

    private final String instanceUrl;

    public RestClient(Map<String, Object> attributes) {
        this.instanceName = (String) attributes.get(Constant.INSTANCE_NAME);
        this.username = (String) attributes.get(Constant.USERNAME);
        this.password = (String) attributes.get(Constant.PASSWORD);
        this.userDomain = (String) attributes.get(Constant.USER_DOMAIN);
        this.instanceUrl = (String) attributes.get(Constant.INSTANCEURL);
    }

    @Inject
    public RestClient(Invoker invoker) {
        this(invoker.getAttributes());
    }

    /**
     * Creates an HTTP request body for session token authentication.
     *
     * @param params the authentication parameters including username, password, and instance name
     * @return a RequestBody containing the JSON-encoded authentication parameters
     */
    public static RequestBody createRequestBodyForToken(Map<String, Object> params) {
        Gson gson = new Gson();
        String jsonBody = gson.toJson(params);
        MediaType mediaType = MediaType.parse("application/json");
        return RequestBody.create(mediaType, jsonBody);
    }

    /**
     * Extracts the success status from an Archer API JSON response.
     *
     * @param jsonResponse the JSON response string from the Archer API
     * @return true if the response indicates success, false otherwise
     */
    public static boolean extractIsSuccessful(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        return jsonObject.get("IsSuccessful").getAsBoolean();
    }

    /**
     * Extracts the session token from an Archer login API JSON response.
     *
     * @param jsonResponse the JSON response string from the Archer login API
     * @return the session token string for authenticating subsequent API requests
     */
    public static String extractSessionToken(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
        return jsonObject
                .get("RequestedObject")
                .getAsJsonObject()
                .get("SessionToken")
                .getAsString();
    }

    /**
     * Creates a session token by authenticating with the Archer login API.
     * <p>
     * This method sends a POST request to the Archer login endpoint with the provided
     * credentials and returns a session token that can be used for subsequent API calls.
     * </p>
     *
     * @param params the authentication parameters containing username, password, instance name,
     *               and optionally user domain
     * @return the session token string for authenticating subsequent API requests
     * @throws IllegalArgumentException if authentication fails due to invalid credentials,
     *                                  connection issues, or other errors
     */
    public String createSessionToken(Map<String, Object> params) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody requestBody = createRequestBodyForToken(params);
            System.out.println("Url for CreateSessionToken " + instanceUrl + "/api/core/security/login");
            Request request = new Request.Builder()
                    .url(instanceUrl + "/api/core/security/login")
                    .addHeader(Constant.CONTENT_TYPE, Constant.APPLICATION_JSON)
                    .addHeader("Accept", Constant.APPLICATION_JSON)
                    .post(requestBody)
                    .build();

            String response = handleResponse(client.newCall(request).execute()).body().string();
            boolean isSuccessful = extractIsSuccessful(response);
            System.out.println("Create Session Token is Successful " + isSuccessful);
            if (isSuccessful) {
                return extractSessionToken(response);
            } else {
                throw new IllegalArgumentException("Authentication failed: Invalid username, password, or instance name. Please verify your Archer credentials.");
            }
        } catch (IOException cause) {
            System.out.println("Could not make connection " + cause.getMessage());
            throw new IllegalArgumentException(cause.getMessage());
        } catch (Exception cause) {
            System.out.println("Final Catch Exception " + cause.getMessage());
            throw new IllegalArgumentException(cause.getMessage());
        }
    }

    /**
     * Executes an HTTP GET request to the specified Archer API endpoint with automatic retry logic.
     * <p>
     * This method creates a new session token for each request attempt and includes it in the
     * Authorization header. If the request fails, it will retry up to 2 times for unsuccessful
     * responses or timeout exceptions.
     * </p>
     *
     * @param endpoint the API endpoint path to call (e.g., "/api/core/system/application")
     * @return the HTTP Response from the Archer API, or null if all retry attempts fail
     * @throws RuntimeException if the request fails after all retry attempts due to network issues
     */
    public Response get(String endpoint) {
        int maxRetries = 2; // Maximum number of retries
        int retryCount = 0; // Current retry attempt
        boolean retry; // Flag to control the retry loop

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        while (retryCount < maxRetries) {
            try {
                Request request = new Request.Builder()
                        .url(instanceUrl + endpoint)
                        .addHeader(AUTHORIZATION,
                                ARCHER_SESSION_ID + createSessionToken(getQueryParams()))
                        .build();

                Response response = client.newCall(request).execute();

                if (!response.isSuccessful() && retryCount < maxRetries - 1) {
                    // Check if response is unsuccessful and we have retries left
                    response.close(); // Important to close the response before retrying
                    retry = true;
                } else {
                    return handleResponse(response); // Return the successful response or the last attempt
                }
            } catch (IOException cause) {
                System.out.println("IOException " + cause.getMessage());
                if (cause.getMessage().equals("timeout") && retryCount < maxRetries - 1) {
                    // Retry only for timeout exceptions and if we have retries left
                    retry = true;
                } else {
                    throw new RuntimeException("Request to Archer failed after 2 retry attempts. Please check your network connection and Archer server availability.", cause);
                }
            }

            if (retry) {
                retryCount++;
                System.out.println("Retrying... Attempt " + (retryCount + 1));
                retry = false; // Reset retry flag for the next iteration
            }
        }

        System.out.println("Request failed after " + maxRetries + " attempts");
        return null;
    }

    private Map<String, Object> getQueryParams() {
        if (userDomain != null && !userDomain.isEmpty()) {
            return Map.of(Constant.USERNAME, username, Constant.PASSWORD, password, Constant.INSTANCE_NAME, instanceName, Constant.USER_DOMAIN, userDomain);
        } else {
            return Map.of(Constant.USERNAME, username, Constant.PASSWORD, password, Constant.INSTANCE_NAME, instanceName);
        }
    }

    /**
     * Handles and validates an HTTP response from the Archer API.
     * <p>
     * This method checks the HTTP status code and either returns the response for successful
     * requests (200, 201) or throws appropriate exceptions for error status codes.
     * </p>
     *
     * @param response the HTTP Response object to handle
     * @return the original response if successful (status 200 or 201), or for unhandled status codes
     * @throws IllegalArgumentException if the response indicates an authorization failure (401),
     *                                  bad request (400), or server error (500)
     */
    public Response handleResponse(Response response) {
        try {
            int statusCode = response.code();
            if (statusCode == 200 || statusCode == 201) {
                System.out.println(Constant.STATUS_CODE_IS + response.code() + " Request is Success");
                return response;
            } else if (statusCode == 401) {
                System.out.println(Constant.STATUS_CODE_IS + response.code() + Constant.YOU_ARE_NOT_AUTHORIZED);
                throw new IllegalArgumentException("Authorization failed: Your session has expired or you do not have permission to access this resource. Please verify your credentials and permissions.");
            } else if (statusCode == 400) {
                System.out.println(Constant.STATUS_CODE_IS + response.code() + Constant.BAD_REQUEST);
                throw new IllegalArgumentException("Bad request: One or more input parameters are invalid. Please verify the Application ID, Tracking ID, or Application Name you provided.");
            } else if (statusCode == 500) {
                System.out.println(Constant.STATUS_CODE_IS + response.code() + Constant.INTERNAL_SERVER_ERROR);
                throw new IllegalArgumentException("Archer server error: The server encountered an internal error. Please contact your Archer administrator.");
            } else {
                System.out.println("Request failed with status code" + statusCode + response.body().string());
                return response;
            }
        } catch (IOException cause) {
            System.out.println("Error reading response body " + cause.getMessage());
        }
        return response;
    }

}
