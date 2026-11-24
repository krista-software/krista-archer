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

import app.krista.extension.cyber_security.compliance.service.Application;

public class ApplicationImpl implements Application {

   private Integer type;

    private Boolean notification;

    private Boolean authoratativeSource;

    private Integer searchView;

    private Boolean taskManagementEnabled;

    private Integer languageId;

    private Boolean isDirectToEditEnabled;

    private Boolean keepLicensed;

    private Boolean isDeprecated;

    private Boolean isWaveEnabled;

    private Boolean isMobileEnabled;

    private Boolean isEngageEnabled;

    private Integer id;

    private String name;

    private String alias;

    private String description;

    private Integer status;

    private Boolean isDeleted;

    private Boolean isGeneric;

    private String guid;

    private String directory;

    private Integer asostatus;

    private String asoname;

    private Boolean isSystem;

    private Boolean delayCalcsForBulkActions;

    private String createDate;

    private String updateDate;

    private Integer createLogin;

    private Integer updateLogin;


    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public Boolean getIsNotification() {
        return notification;
    }

    @Override
    public Boolean getIsAuthoratativeSource() {
        return authoratativeSource;
    }

    @Override
    public Integer getSearchView() {
        return searchView;
    }

    @Override
    public Boolean getIsTaskManagementEnabled() {
        return taskManagementEnabled;
    }

    @Override
    public Integer getLanguageId() {
        return languageId;
    }

    @Override
    public Boolean getIsDirectToEditEnabled() {
        return isDirectToEditEnabled;
    }

    @Override
    public Boolean getKeepLicensed() {
        return keepLicensed;
    }

    @Override
    public Boolean getIsDeprecated() {
        return isDeprecated;
    }

    @Override
    public Boolean getIsWaveEnabled() {
        return isWaveEnabled;
    }

    @Override
    public Boolean getIsMobileEnabled() {
        return isMobileEnabled;
    }

    @Override
    public Boolean getIsEngageEnabled() {
        return isEngageEnabled;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAlias() {
        return alias;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    @Override
    public Boolean getIsGeneric() {
        return isGeneric;
    }

    @Override
    public String getGuid() {
        return guid;
    }

    @Override
    public String getDirectory() {
        return directory;
    }

    @Override
    public Integer getAsostatus() {
        return asostatus;
    }

    @Override
    public String getAsoname() {
        return asoname;
    }

    @Override
    public Boolean getIsSystem() {
        return isSystem;
    }

    @Override
    public Boolean getDelayCalcsForBulkActions() {
        return delayCalcsForBulkActions;
    }

    @Override
    public String getCreateDate() {
        return createDate;
    }

    @Override
    public String getUpdateDate() {
        return updateDate;
    }

    @Override
    public Integer getCreateLogin() {
        return createLogin;
    }

    @Override
    public Integer getUpdateLogin() {
        return updateLogin;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setNotification(Boolean notification) {
        this.notification = notification;
    }

    public void setAuthoratativeSource(Boolean authoratativeSource) {
        this.authoratativeSource = authoratativeSource;
    }

    public void setSearchView(Integer searchView) {
        this.searchView = searchView;
    }

    public void setTaskManagementEnabled(Boolean taskManagementEnabled) {
        this.taskManagementEnabled = taskManagementEnabled;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public void setDirectToEditEnabled(Boolean directToEditEnabled) {
        isDirectToEditEnabled = directToEditEnabled;
    }

    public void setKeepLicensed(Boolean keepLicensed) {
        this.keepLicensed = keepLicensed;
    }

    public void setDeprecated(Boolean deprecated) {
        isDeprecated = deprecated;
    }

    public void setWaveEnabled(Boolean waveEnabled) {
        isWaveEnabled = waveEnabled;
    }

    public void setMobileEnabled(Boolean mobileEnabled) {
        isMobileEnabled = mobileEnabled;
    }

    public void setEngageEnabled(Boolean engageEnabled) {
        isEngageEnabled = engageEnabled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public void setGeneric(Boolean generic) {
        isGeneric = generic;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public void setAsostatus(Integer asostatus) {
        this.asostatus = asostatus;
    }

    public void setAsoname(String asoname) {
        this.asoname = asoname;
    }

    public void setSystem(Boolean system) {
        isSystem = system;
    }

    public void setDelayCalcsForBulkActions(Boolean delayCalcsForBulkActions) {
        this.delayCalcsForBulkActions = delayCalcsForBulkActions;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setCreateLogin(Integer createLogin) {
        this.createLogin = createLogin;
    }

    public void setUpdateLogin(Integer updateLogin) {
        this.updateLogin = updateLogin;
    }
}
