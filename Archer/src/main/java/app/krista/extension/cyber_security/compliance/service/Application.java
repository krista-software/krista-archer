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

package app.krista.extension.cyber_security.compliance.service;

public interface Application {

    public Integer getType();

    public Boolean getIsNotification();

    public Boolean getIsAuthoratativeSource();

    public Integer getSearchView();

    public Boolean getIsTaskManagementEnabled();

    public Integer getLanguageId();

    public Boolean getIsDirectToEditEnabled();

    public Boolean getKeepLicensed();

    public Boolean getIsDeprecated();

    public Boolean getIsWaveEnabled();

    public Boolean getIsMobileEnabled();

    public Boolean getIsEngageEnabled();

    public Integer getId();

    public String getName();

    public String getAlias();

    public String getDescription();

    public Integer getStatus();

    public Boolean getIsDeleted();

    public Boolean getIsGeneric();

    public String getGuid();

    public String getDirectory();

    public Integer getAsostatus();

    public String getAsoname();

    public Boolean getIsSystem();

    public Boolean getDelayCalcsForBulkActions();

    public String getCreateDate();

    public String getUpdateDate();

    public Integer getCreateLogin();

    public Integer getUpdateLogin();
}
