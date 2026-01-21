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
import app.krista.extension.cyber_security.compliance.impl.ContentDaoImpl;
import app.krista.extension.impl.anno.Attribute;
import app.krista.extension.impl.anno.CatalogRequest;
import app.krista.extension.impl.anno.Domain;
import app.krista.extension.impl.anno.Field;

import javax.inject.Inject;
import java.util.Map;

@Domain(id = "catEntryDomain_c54a8d90-5657-4586-bf45-ce2252889764",
        name = "Compliance",
        ecosystemId = "catEntryEcosystem_5516afe8-3809-4918-b911-d89597fc96e0",
        ecosystemName = "Cyber Security",
        ecosystemVersion = "c7b8bc40-cc02-41da-be5f-fa82f45bd779")
public class ContentArea {

    RestClient restClient;

    ContentDaoImpl contentDao;

    @Inject
    public ContentArea(RestClient restClient) {
        contentDao = new ContentDaoImpl();
        this.restClient = restClient;
    }

    /**
     * Retrieves detailed information for a specific record from an Archer application.
     * <p>
     * This catalog request fetches a single record's complete details using the application
     * alias and the record's tracking ID.
     * </p>
     *
     * @param applicationName the alias of the Archer application containing the record
     * @param trackingID      the unique tracking ID of the record to retrieve
     * @return a map containing "Response" (record data as a table) and "Response Info" (status details)
     * @throws IllegalArgumentException if the application name or tracking ID is invalid,
     *                                  or if authorization fails
     */
    @CatalogRequest(
            id = "localDomainRequest_2b0b02ad-2586-4520-8803-77321deaca92",
            name = "Get Individual Record Detail",
            description = "Get Individual Record Detail Based On The Tracking ID",
            area = "Content",
            type = CatalogRequest.Type.CHANGE_SYSTEM)
    @Field(name = "Response Info", type = "FreeForm", required = false, attributes = {}, options = {})
    @Field.Desc(name = "Response", type = "[ Composite ]", required = false)
    public Map<String, Object> getIndividualRecordDetail(
            @Field.Text(name = "Application name", required = true, attributes = {}, options = {}) String applicationName,
            @Field(name = "Tracking ID", type = "Number", required = true, attributes = {}, options = {}) Double trackingID) {
        return contentDao.getIndividualRecordDetail(restClient, applicationName, trackingID.intValue());
    }

    /**
     * Retrieves all records from an Archer application with pagination support.
     * <p>
     * This catalog request fetches records from the specified application using its alias.
     * Pagination is supported through pageSize (maximum 1000) and itemIndex parameters.
     * </p>
     *
     * @param applicationName the alias of the Archer application to retrieve records from
     * @param pageSize        the maximum number of records to return (optional, defaults to 1000)
     * @param itemIndex       the starting index for pagination (optional, defaults to 0)
     * @return a map containing "Response" (records as a table) and "Response Info" (status details)
     * @throws IllegalArgumentException if the application name is invalid or authorization fails
     */
    @CatalogRequest(
            id = "localDomainRequest_cb1025c4-bd08-46e7-90ad-cdf1aadfcf76",
            name = "Get All The Record Of The Application",
            description = "Get All The Record Of The Application Based On Alias Of The Application",
            area = "Content",
            type = CatalogRequest.Type.CHANGE_SYSTEM)
    @Field(name = "Response Info", type = "FreeForm", required = false, attributes = {}, options = {})
    @Field.Desc(name = "Response", type = "[ Composite ]", required = false)
    public Map<String, Object> getAllTheRecordOfTheApplication(
            @Field.Text(name = "Application Name", required = true, attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) String applicationName,
            @Field(name = "Page Size", type = "Number", required = false, attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double pageSize,
            @Field(name = "Item Index", type = "Number", required = false, attributes = {@Attribute(name = "visualWidth", value = "S")}, options = {}) Double itemIndex) {
        return contentDao.getRecordsOfTheApplication(applicationName, restClient, pageSize, itemIndex);
    }
}
