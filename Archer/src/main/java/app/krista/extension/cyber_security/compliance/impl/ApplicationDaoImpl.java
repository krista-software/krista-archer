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

package app.krista.extension.cyber_security.compliance.impl;

import app.krista.extension.cyber_security.compliance.RestClient;
import app.krista.extension.cyber_security.compliance.catalog.CatalogueTypes;
import app.krista.extension.cyber_security.compliance.impl.model.Root;
import app.krista.extension.cyber_security.compliance.service.Application;
import app.krista.extension.cyber_security.compliance.service.ApplicationDao;
import app.krista.extension.cyber_security.compliance.impl.util.Constant;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of ApplicationDao for retrieving application metadata from the Archer API.
 */
public class ApplicationDaoImpl implements ApplicationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationDaoImpl.class);


    /**
     * Retrieves a list of all applications from the Archer system.
     * <p>
     * Calls the specified Archer API endpoint and parses the response into Application objects.
     * </p>
     *
     * @param url        the API endpoint URL for retrieving applications
     * @param restClient the REST client for making API calls
     * @return a list of Application objects, or an empty list if no applications are found or an error occurs
     */
    @Override
    public List<Application> getAllList(String url, RestClient restClient) {
        List<Application> applications = new ArrayList<>();
        try {
            Response response = restClient.get(url);
            if (response != null && response.body() != null) {
                Root[] roots = Constant.GSON.fromJson(
                        Constant.GSON.fromJson(response.body().string(), JsonArray.class), Root[].class);
                for (Root root : roots) {
                    applications.add(CatalogueTypes.createApplication(root));
                }
            } else {
                System.err.println("Application List is Empty");
                return Collections.emptyList();
            }
            return applications;
        } catch (IOException exception) {
            LOGGER.debug(exception.getMessage(), exception.getCause());
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves a specific application by its numeric ID.
     *
     * @param id         the unique numeric ID of the application
     * @param restClient the REST client for making API calls
     * @return the Application object if found, or null if not found or an error occurs
     */
    @Override
    public Application getApplicationById(Integer id, RestClient restClient) {

        try {
            Response response = restClient.get("/api/core/system/application/" + id);
            if (response != null && response.body() != null) {
                Root root = Constant.GSON.fromJson(
                        Constant.GSON.fromJson(response.body().string(), JsonObject.class), Root.class);
                return CatalogueTypes.createApplication(root);

            }
            return null;
        } catch (IOException exception) {
            LOGGER.debug(exception.getMessage(), exception.getCause());
            return null;
        }
    }
}
