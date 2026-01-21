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

package app.krista.extension.cyber_security.compliance.catalog;


import app.krista.extension.cyber_security.compliance.RestClient;
import app.krista.extension.cyber_security.compliance.impl.FieldDaoImpl;
import app.krista.extension.impl.anno.CatalogRequest;
import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Field;

import java.util.Map;
import javax.inject.Inject;

@Domain(id = "catEntryDomain_c54a8d90-5657-4586-bf45-ce2252889764",
        name = "Compliance",
        ecosystemId = "catEntryEcosystem_5516afe8-3809-4918-b911-d89597fc96e0",
        ecosystemName = "Cyber Security",
        ecosystemVersion = "72f4ad93-6257-4734-9c8a-dfa337515d10")
public class FieldArea {

    FieldDaoImpl fieldDao;

    RestClient restClient;

    @Inject
    public FieldArea(RestClient restClient) {
        this.restClient = restClient;
        fieldDao = new FieldDaoImpl();
    }


    /**
     * Retrieves field definitions for a specific Archer application.
     * <p>
     * This catalog request fetches metadata about all fields defined in the specified
     * application, including field types, names, and configuration details.
     * </p>
     *
     * @param applicationID the numeric ID of the Archer application
     * @return a map containing "Response" (field definitions as a table) and "Response Info" (status details)
     * @throws IllegalArgumentException if the application ID is invalid or authorization fails
     */
    @CatalogRequest(
            id = "localDomainRequest_c210fc0a-a9de-4fcd-84fe-1625dd5cc6bc",
            name = "Get Application Data  Fields",
            description = "Get Overview Of Type Of Data Field  Based on Application ID",
            area = "Field",
            type = CatalogRequest.Type.CHANGE_SYSTEM)
    @Field(name = "Response Info", type = "FreeForm", required = false, attributes = {}, options = {})
    @Field.Desc(name = "Response", type = "[ Composite ]", required = false)
    public Map<String, Object> getApplicationDataFields(
            @Field(name = "Application ID", type = "Number", required = true, attributes = {}, options = {}) Double applicationID) {
        return fieldDao.getApplicationDataFieldDetails(restClient, applicationID.intValue());
    }

    /**
     * Retrieves field content for a specific record using its tracking ID.
     * <p>
     * This catalog request fetches the actual field values and content data for a record
     * identified by its tracking ID.
     * </p>
     *
     * @param trackingID the unique tracking ID of the record
     * @return a map containing "Response" (field content as a table) and "Response Info" (status details)
     * @throws IllegalArgumentException if the tracking ID is invalid or authorization fails
     */
    @CatalogRequest(
            id = "localDomainRequest_293b0d66-a7f8-43b0-ac28-07e191919ab3",
            name = "Get The Field Content",
            description = "Get The Field Content Based On Tracking ID",
            area = "Field",
            type = CatalogRequest.Type.CHANGE_SYSTEM)
    @Field(name = "Response Info", type = "FreeForm", required = false, attributes = {}, options = {})
    @Field.Desc(name = "Response", type = "[ Composite ]", required = false)
    public Map<String, Object> getTheFieldContent(
            @Field(name = "Tracking ID", type = "Number", required = true, attributes = {}, options = {}) Double trackingID) {
        return fieldDao.getFieldContent(restClient, trackingID.intValue());
    }

}
