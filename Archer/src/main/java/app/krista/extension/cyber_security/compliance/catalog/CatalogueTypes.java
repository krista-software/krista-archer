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

import app.krista.extension.cyber_security.compliance.impl.model.Root;
import app.krista.extension.cyber_security.compliance.impl.ApplicationImpl;
import app.krista.extension.cyber_security.compliance.impl.QuestionnaireImpl;
import app.krista.extension.cyber_security.compliance.service.Application;
import app.krista.extension.cyber_security.compliance.service.Questionnaire;

/**
 * Factory class for creating Application and Questionnaire entities from Archer API responses.
 * <p>
 * This class provides static factory methods to convert raw API response objects (Root)
 * into domain entities used by the Krista platform.
 * </p>
 */
public class CatalogueTypes {

    private CatalogueTypes() {

    }

    /**
     * Creates an Application entity from an Archer API response object.
     * <p>
     * Maps the Root object fields to the Application entity, including ID, name, alias,
     * description, status, type, and GUID.
     * </p>
     *
     * @param root the Root object containing application data from the Archer API
     * @return a new Application entity populated with data from the root object
     */
    public static Application createApplication(Root root) {
        ApplicationImpl applicationImpl = new ApplicationImpl();
        applicationImpl.setType(root.getRequestedObject().getType());
        applicationImpl.setNotification(root.getRequestedObject().getIsNotification());
        applicationImpl.setAuthoratativeSource(root.getRequestedObject().getIsAuthoratativeSource());
        applicationImpl.setSearchView(root.getRequestedObject().getSearchView());
        applicationImpl.setTaskManagementEnabled(root.getRequestedObject().getIsTaskManagementEnabled());
        applicationImpl.setLanguageId(root.getRequestedObject().getLanguageId());
        applicationImpl.setDirectToEditEnabled(root.getRequestedObject().getIsDirectToEditEnabled());
        applicationImpl.setKeepLicensed(root.getRequestedObject().getKeepLicensed());
        applicationImpl.setDeprecated(root.getRequestedObject().getIsDeprecated());
        applicationImpl.setWaveEnabled(root.getRequestedObject().getIsWaveEnabled());
        applicationImpl.setMobileEnabled(root.getRequestedObject().getIsMobileEnabled());
        applicationImpl.setEngageEnabled(root.getRequestedObject().getIsEngageEnabled());
        applicationImpl.setId(root.getRequestedObject().getId());
        applicationImpl.setName(root.getRequestedObject().getName());
        applicationImpl.setAlias(root.getRequestedObject().getAlias());
        applicationImpl.setDescription(root.getRequestedObject().getDescription());
        applicationImpl.setDeleted(root.getRequestedObject().getIsDeleted());
        applicationImpl.setGeneric(root.getRequestedObject().getIsGeneric());
        applicationImpl.setGuid(root.getRequestedObject().getGuid());
        applicationImpl.setDirectory(root.getRequestedObject().getDirectory());
        applicationImpl.setAsoname(root.getRequestedObject().getASOName());
        applicationImpl.setAsostatus(root.getRequestedObject().getASOStatus());
        applicationImpl.setCreateDate(root.getRequestedObject().getUpdateInformation().getCreateDate());
        applicationImpl.setUpdateDate(root.getRequestedObject().getUpdateInformation().getUpdateDate());
        applicationImpl.setCreateLogin(root.getRequestedObject().getUpdateInformation().getCreateLogin());
        applicationImpl.setUpdateLogin(root.getRequestedObject().getUpdateInformation().getUpdateLogin());
        return applicationImpl;
    }

    /**
     * Creates a Questionnaire entity from an Archer API response object.
     * <p>
     * Maps the Root object fields to the Questionnaire entity, including all questionnaire-specific
     * properties such as notification settings, task management, mobile/wave/engage enablement,
     * and findings configuration.
     * </p>
     *
     * @param root the Root object containing questionnaire data from the Archer API
     * @return a new Questionnaire entity populated with data from the root object
     */
    public static Questionnaire createQuestionnaire(Root root) {

        QuestionnaireImpl questionnaireImpl = new QuestionnaireImpl();
        questionnaireImpl.setType(root.getRequestedObject().getType());
        questionnaireImpl.setNotification(root.getRequestedObject().getIsNotification());
        questionnaireImpl.setAuthoratativeSource(root.getRequestedObject().getIsAuthoratativeSource());
        questionnaireImpl.setSearchView(root.getRequestedObject().getSearchView());
        questionnaireImpl.setTaskManagementEnabled(root.getRequestedObject().getIsTaskManagementEnabled());
        questionnaireImpl.setLanguageId(root.getRequestedObject().getLanguageId());
        questionnaireImpl.setDirectToEditEnabled(root.getRequestedObject().getIsDirectToEditEnabled());
        questionnaireImpl.setKeepLicensed(root.getRequestedObject().getKeepLicensed());
        questionnaireImpl.setDeprecated(root.getRequestedObject().getIsDeprecated());
        questionnaireImpl.setWaveEnabled(root.getRequestedObject().getIsWaveEnabled());
        questionnaireImpl.setMobileEnabled(root.getRequestedObject().getIsMobileEnabled());
        questionnaireImpl.setEngageEnabled(root.getRequestedObject().getIsEngageEnabled());
        questionnaireImpl.setId(root.getRequestedObject().getId());
        questionnaireImpl.setName(root.getRequestedObject().getName());
        questionnaireImpl.setAlias(root.getRequestedObject().getAlias());
        questionnaireImpl.setDescription(root.getRequestedObject().getDescription());
        questionnaireImpl.setDeleted(root.getRequestedObject().getIsDeleted());
        questionnaireImpl.setGeneric(root.getRequestedObject().getIsGeneric());
        questionnaireImpl.setGuid(root.getRequestedObject().getGuid());
        questionnaireImpl.setDirectory(root.getRequestedObject().getDirectory());
        questionnaireImpl.setAsoname(root.getRequestedObject().getASOName());
        questionnaireImpl.setAsostatus(root.getRequestedObject().getASOStatus());
        questionnaireImpl.setFieldNameVisible(root.getRequestedObject().getFieldNameVisible());
        questionnaireImpl.setFindingsEnabled(root.getRequestedObject().getFindingEnabled());
        questionnaireImpl.setQuestionTypeFormatOverride(root.getRequestedObject().getQuestionTypeFormatOverride());
        questionnaireImpl.setTargetLevelId(root.getRequestedObject().getTargetLevelId());
        questionnaireImpl.setTargetModuleId(root.getRequestedObject().getTargetModuleId());
        questionnaireImpl.setFindingText(root.getRequestedObject().getFindingsText());
        questionnaireImpl.setFindingGenerationFilterId(root.getRequestedObject().getFindingsGenerationFilterId());
        questionnaireImpl.setCreateDate(root.getRequestedObject().getUpdateInformation().getCreateDate());
        questionnaireImpl.setUpdateDate(root.getRequestedObject().getUpdateInformation().getUpdateDate());
        questionnaireImpl.setCreateLogin(root.getRequestedObject().getUpdateInformation().getCreateLogin());
        questionnaireImpl.setUpdateLogin(root.getRequestedObject().getUpdateInformation().getUpdateLogin());

        return questionnaireImpl;

    }

    /**
     * Converts an Application domain object to an Application entity for catalog display.
     *
     * @param application the Application domain object to convert
     * @return an Application entity with name, type, alias, guid, and id fields, or null if input is null
     */
    public static app.krista.extension.cyber_security.compliance.catalog.entities.Application toApplicationEntity(
            Application application) {
        if (application != null) {
            app.krista.extension.cyber_security.compliance.catalog.entities.Application applicationEntity = new app.krista.extension.cyber_security.compliance.catalog.entities.Application();
            applicationEntity.name = application.getName();
            applicationEntity.type = String.valueOf(application.getType());
            applicationEntity.alias = application.getAlias();
            applicationEntity.guid = application.getGuid();
            applicationEntity.id = String.valueOf(application.getId());
            return applicationEntity;
        }
        return null;
    }

    /**
     * Converts a Questionnaire domain object to a Questionnaire entity for catalog display.
     *
     * @param questionnaire the Questionnaire domain object to convert
     * @return a Questionnaire entity with id, guid, type, name, and alias fields, or null if input is null
     */
    public static app.krista.extension.cyber_security.compliance.catalog.entities.Questionnaire toQuestionnaireEntity(
            Questionnaire questionnaire) {
        if (questionnaire != null) {
            app.krista.extension.cyber_security.compliance.catalog.entities.Questionnaire questionnaireEntity = new app.krista.extension.cyber_security.compliance.catalog.entities.Questionnaire();
            questionnaireEntity.id = String.valueOf(questionnaire.getId());
            questionnaireEntity.guid = questionnaire.getGuid();
            questionnaireEntity.type = String.valueOf(questionnaire.getType());
            questionnaireEntity.name = questionnaire.getName();
            questionnaireEntity.alias = questionnaire.getAlias();
            return questionnaireEntity;
        }
        return null;
    }
}
