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
import app.krista.extension.cyber_security.compliance.impl.util.Constant;
import app.krista.extension.cyber_security.compliance.impl.util.HttpQueryParameters;
import app.krista.extension.cyber_security.compliance.service.ContentDao;
import app.krista.extension.cyber_security.compliance.impl.util.ResponseModification;

import java.util.Map;

import okhttp3.Response;

/**
 * Implementation of ContentDao for retrieving content records from the Archer API.
 */
public class ContentDaoImpl implements ContentDao {

    /**
     * Retrieves all records from an Archer application with pagination support.
     * <p>
     * Uses the Archer Content API with OData query parameters for pagination.
     * Maximum page size is 1000 records per request.
     * </p>
     *
     * @param applicationAlias the alias of the Archer application
     * @param restClient       the REST client for making API calls
     * @param pageSize         the maximum number of records to return (defaults to 1000 if null)
     * @param itemIndex        the starting index for pagination (optional)
     * @return a map containing the response data formatted as a table with status information
     */
    @Override
    public Map<String, Object> getRecordsOfTheApplication(String applicationAlias, RestClient restClient
            , Double pageSize, Double itemIndex) {

        // A Thousand is used because maximum it could fetch is 1000 at a time
        Response response = restClient.get("/" + Constant.CONTENTAPI + "/" + applicationAlias +
                HttpQueryParameters.sortingAndOrderBy(pageSize == null ? Constant.THOUSAND : String.valueOf(pageSize.intValue()),
                        itemIndex == null ? null : String.valueOf(itemIndex.intValue())));
        return ResponseModification.responseToTable(response);
    }

    /**
     * Retrieves detailed information for a specific record by its tracking ID.
     *
     * @param restClient the REST client for making API calls
     * @param appName    the alias of the Archer application containing the record
     * @param trackingId the unique tracking ID of the record
     * @return a map containing the record data formatted as a table with status information
     */
    @Override
    public Map<String, Object> getIndividualRecordDetail(RestClient restClient, String appName,
                                                         Integer trackingId) {
        Response response = restClient.get("/" + Constant.CONTENTAPI + "/" + appName + "(" + trackingId + ")");
        return ResponseModification.responseToTable(response);
    }
}


