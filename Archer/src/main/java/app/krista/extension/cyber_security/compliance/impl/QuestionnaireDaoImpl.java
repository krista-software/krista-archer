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
import app.krista.extension.cyber_security.compliance.service.Questionnaire;
import app.krista.extension.cyber_security.compliance.service.QuestionnaireDao;
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
 * Implementation of QuestionnaireDao for retrieving questionnaire metadata from the Archer API.
 */
public class QuestionnaireDaoImpl implements QuestionnaireDao {


    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionnaireDaoImpl.class);

    /**
     * Retrieves a list of all questionnaires from the Archer system.
     * <p>
     * Calls the specified Archer API endpoint and parses the response into Questionnaire objects.
     * </p>
     *
     * @param url        the API endpoint URL for retrieving questionnaires
     * @param restClient the REST client for making API calls
     * @return a list of Questionnaire objects, or an empty list if none are found or an error occurs
     */
    @Override
    public List<Questionnaire> getAllQuestionnaire(String url, RestClient restClient) {

        List<Questionnaire> questionnaires = new ArrayList<>();
        try {
            Response response = restClient.get(url);
            if (response != null && response.body() != null) {
                Root[] roots = Constant.GSON.fromJson(
                        Constant.GSON.fromJson(response.body().string(), JsonArray.class), Root[].class);
                for (Root root : roots) {
                    questionnaires.add(CatalogueTypes.createQuestionnaire(root));
                }
                return questionnaires;
            } else {
                return Collections.emptyList();
            }

        } catch (IOException exception) {
            LOGGER.debug(exception.getMessage(), exception.getCause());
            return Collections.emptyList();
        }

    }

    /**
     * Retrieves a specific questionnaire by its numeric ID.
     *
     * @param id         the unique numeric ID of the questionnaire
     * @param restClient the REST client for making API calls
     * @return the Questionnaire object if found, or null if not found or an error occurs
     */
    @Override
    public Questionnaire getQuestionnaireById(Integer id, RestClient restClient) {
        try {
            Response response = restClient.get("/api/core/system/questionnaire/" + id);
            if (response != null && response.body() != null) {
                Root root = Constant.GSON.fromJson(
                        Constant.GSON.fromJson(response.body().string(), JsonObject.class), Root.class);
                return CatalogueTypes.createQuestionnaire(root);

            }
            return null;
        } catch (IOException exception) {
            return null;
        }

    }
}
