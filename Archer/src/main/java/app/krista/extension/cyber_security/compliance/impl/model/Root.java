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

package app.krista.extension.cyber_security.compliance.impl.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Root {

    @SerializedName("Links")
    private List<Object> links = new ArrayList<>();

    @SerializedName("RequestedObject")
    private RequestedObject requestedObject;

    @SerializedName("IsSuccessful")
    private Boolean isSuccessful;

    @SerializedName("ValidationMessages")
    private List<Object> validationMessages = new ArrayList<>();


    public List<Object> getLinks() {
        return links;
    }

    public void setLinks(List<Object> links) {
        this.links = links;
    }

    public RequestedObject getRequestedObject() {
        return requestedObject;
    }

    public void setRequestedObject(RequestedObject requestedObject) {
        this.requestedObject = requestedObject;
    }

    public Boolean getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public List<Object> getValidationMessages() {
        return validationMessages;
    }

    public void setValidationMessages(List<Object> validationMessages) {
        this.validationMessages = validationMessages;
    }
}
