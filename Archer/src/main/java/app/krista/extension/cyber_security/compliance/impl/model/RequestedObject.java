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

public class RequestedObject {

    @SerializedName("Type")
    private Integer type;

    @SerializedName("IsNotification")
    private Boolean isNotification;

    @SerializedName("IsAuthoratativeSource")
    private Boolean isAuthoratativeSource;

    @SerializedName("SearchView")
    private Integer searchView;

    @SerializedName("IsTaskManagementEnabled")
    private Boolean isTaskManagementEnabled;

    @SerializedName("LanguageId")
    private Integer languageId;

    @SerializedName("IsDirectToEditEnabled")
    private Boolean isDirectToEditEnabled;

    @SerializedName("KeepLicensed")
    private Boolean keepLicensed;

    @SerializedName("IsDeprecated")
    private Boolean isDeprecated;

    @SerializedName("IsWaveEnabled")
    private Boolean isWaveEnabled;

    @SerializedName("IsMobileEnabled")
    private Boolean isMobileEnabled;

    @SerializedName("IsEngageEnabled")
    private Boolean isEngageEnabled;

    @SerializedName("Id")
    private Integer id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Alias")
    private String alias;

    @SerializedName("Description")
    private String description;

    @SerializedName("Status")
    private Integer status;

    @SerializedName("IsDeleted")
    private Boolean isDeleted;

    @SerializedName("IsGeneric")
    private Boolean isGeneric;

    @SerializedName("Guid")
    private String guid;

    @SerializedName("Directory")
    private String directory;

    @SerializedName("ASOStatus")
    private Integer aSOStatus;

    @SerializedName("ASOName")
    private String aSOName;

    @SerializedName("IsSystem")
    private Boolean isSystem;

    @SerializedName("DelayCalcsForBulkActions")
    private Boolean delayCalcsForBulkActions;

    @SerializedName("UpdateInformation")
    private UpdateInformation updateInformation;

    @SerializedName("IsFieldNameVisible")
    private Boolean fieldNameVisible;

    @SerializedName("IsFindingsEnabled")
    private Boolean findingEnabled;

    @SerializedName("QuestionTypeFormatOverride")
    private Integer questionTypeFormatOverride;

    @SerializedName("TargetLevelId")
    private Integer targetLevelId;

    @SerializedName("TargetModuleId")
    private Integer targetModuleId;

    @SerializedName("FindingsText")
    private String findingsText;

    @SerializedName("FindingsGenerationFilterId")
    private Integer findingsGenerationFilterId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsNotification() {
        return isNotification;
    }

    public void setIsNotification(Boolean isNotification) {
        this.isNotification = isNotification;
    }

    public Boolean getIsAuthoratativeSource() {
        return isAuthoratativeSource;
    }

    public void setIsAuthoratativeSource(Boolean isAuthoratativeSource) {
        this.isAuthoratativeSource = isAuthoratativeSource;
    }

    public Integer getSearchView() {
        return searchView;
    }

    public void setSearchView(Integer searchView) {
        this.searchView = searchView;
    }

    public Boolean getIsTaskManagementEnabled() {
        return isTaskManagementEnabled;
    }

    public void setIsTaskManagementEnabled(Boolean isTaskManagementEnabled) {
        this.isTaskManagementEnabled = isTaskManagementEnabled;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Boolean getIsDirectToEditEnabled() {
        return isDirectToEditEnabled;
    }

    public void setIsDirectToEditEnabled(Boolean isDirectToEditEnabled) {
        this.isDirectToEditEnabled = isDirectToEditEnabled;
    }

    public Boolean getKeepLicensed() {
        return keepLicensed;
    }

    public void setKeepLicensed(Boolean keepLicensed) {
        this.keepLicensed = keepLicensed;
    }

    public Boolean getIsDeprecated() {
        return isDeprecated;
    }

    public void setIsDeprecated(Boolean isDeprecated) {
        this.isDeprecated = isDeprecated;
    }

    public Boolean getIsWaveEnabled() {
        return isWaveEnabled;
    }

    public void setIsWaveEnabled(Boolean isWaveEnabled) {
        this.isWaveEnabled = isWaveEnabled;
    }

    public Boolean getIsMobileEnabled() {
        return isMobileEnabled;
    }

    public void setIsMobileEnabled(Boolean isMobileEnabled) {
        this.isMobileEnabled = isMobileEnabled;
    }

    public Boolean getIsEngageEnabled() {
        return isEngageEnabled;
    }

    public void setIsEngageEnabled(Boolean isEngageEnabled) {
        this.isEngageEnabled = isEngageEnabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsGeneric() {
        return isGeneric;
    }

    public void setIsGeneric(Boolean isGeneric) {
        this.isGeneric = isGeneric;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public Integer getASOStatus() {
        return aSOStatus;
    }

    public void setASOStatus(Integer aSOStatus) {
        this.aSOStatus = aSOStatus;
    }

    public String getASOName() {
        return aSOName;
    }

    public void setASOName(String aSOName) {
        this.aSOName = aSOName;
    }

    public Boolean getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Boolean isSystem) {
        this.isSystem = isSystem;
    }

    public Boolean getDelayCalcsForBulkActions() {
        return delayCalcsForBulkActions;
    }

    public void setDelayCalcsForBulkActions(Boolean delayCalcsForBulkActions) {
        this.delayCalcsForBulkActions = delayCalcsForBulkActions;
    }

    public UpdateInformation getUpdateInformation() {
        return updateInformation;
    }

    public void setUpdateInformation(UpdateInformation updateInformation) {
        this.updateInformation = updateInformation;
    }

    public Boolean getFieldNameVisible() {
        return fieldNameVisible;
    }

    public void setFieldNameVisible(Boolean fieldNameVisible) {
        this.fieldNameVisible = fieldNameVisible;
    }

    public Boolean getFindingEnabled() {
        return findingEnabled;
    }

    public void setFindingEnabled(Boolean findingEnabled) {
        this.findingEnabled = findingEnabled;
    }

    public Integer getQuestionTypeFormatOverride() {
        return questionTypeFormatOverride;
    }

    public void setQuestionTypeFormatOverride(Integer questionTypeFormatOverride) {
        this.questionTypeFormatOverride = questionTypeFormatOverride;
    }

    public Integer getTargetLevelId() {
        return targetLevelId;
    }

    public void setTargetLevelId(Integer targetLevelId) {
        this.targetLevelId = targetLevelId;
    }

    public Integer getTargetModuleId() {
        return targetModuleId;
    }

    public void setTargetModuleId(Integer targetModuleId) {
        this.targetModuleId = targetModuleId;
    }

    public String getFindingsText() {
        return findingsText;
    }

    public void setFindingsText(String findingsText) {
        this.findingsText = findingsText;
    }

    public Integer getFindingsGenerationFilterId() {
        return findingsGenerationFilterId;
    }

    public void setFindingsGenerationFilterId(Integer findingsGenerationFilterId) {
        this.findingsGenerationFilterId = findingsGenerationFilterId;
    }
}
