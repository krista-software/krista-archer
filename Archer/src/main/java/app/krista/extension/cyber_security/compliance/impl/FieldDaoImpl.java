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
import app.krista.extension.cyber_security.compliance.service.FieldDao;
import app.krista.extension.cyber_security.compliance.impl.util.ResponseModification;

import java.util.Map;

import okhttp3.Response;
import org.jvnet.hk2.annotations.Service;

/**
 * Implementation of FieldDao for retrieving field definitions and content from the Archer API.
 */
@Service
public class FieldDaoImpl implements FieldDao {

    /**
     * Retrieves field definitions for a specific Archer application.
     * <p>
     * Calls the Archer Field Definition API to get metadata about all fields
     * configured in the specified application.
     * </p>
     *
     * @param restClient    the REST client for making API calls
     * @param applicationId the numeric ID of the Archer application
     * @return a map containing field definitions formatted as a table with status information
     */
    @Override
    public Map<String, Object> getApplicationDataFieldDetails(RestClient restClient,
                                                              Integer applicationId) {
        Response response = restClient.get("/api/core/system/fielddefinition/application/" + applicationId);
        return ResponseModification.responseToTable(response);
    }

    /**
     * Retrieves field content for a specific record by its tracking ID.
     * <p>
     * Calls the Archer Content API to get the actual field values for a record.
     * </p>
     *
     * @param restClient the REST client for making API calls
     * @param trackingId the unique tracking ID of the record
     * @return a map containing field content formatted as a table with status information
     */
    @Override
    public Map<String, Object> getFieldContent(RestClient restClient, Integer trackingId) {
        Response response = restClient.get("/api/core/content/" + trackingId);
        return ResponseModification.responseToTable(response);
    }
}
