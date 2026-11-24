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

package app.krista.extension.cyber_security.compliance.catalog.stores;

import app.krista.extension.cyber_security.compliance.RestClient;
import app.krista.extension.cyber_security.compliance.catalog.CatalogueTypes;
import app.krista.extension.cyber_security.compliance.catalog.entities.Questionnaire;
import app.krista.extension.cyber_security.compliance.impl.QuestionnaireDaoImpl;
import app.krista.extension.cyber_security.compliance.impl.util.Constant;
import app.krista.extension.executor.SearchCondition;
import app.krista.extension.executor.SearchQuery;
import app.krista.extension.util.EntityStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.inject.Inject;

import org.jvnet.hk2.annotations.Service;

/**
 * Entity store implementation for Questionnaire entities.
 * <p>
 * Provides methods to retrieve and search for Archer questionnaires through the catalog system.
 * Implements the EntityStore interface for integration with the Krista platform.
 * </p>
 */
@Service
public class QuestionnaireStore implements EntityStore<Questionnaire> {

    private final RestClient restClient;

    private final QuestionnaireDaoImpl questionnaireDaoImpl;

    @Inject
    public QuestionnaireStore(RestClient restClient) {
        this.restClient = restClient;
        questionnaireDaoImpl = new QuestionnaireDaoImpl();
    }

    @Override
    public Questionnaire create(Map<String, Object> map) throws IOException {
        return null;
    }

    /**
     * Retrieves a specific questionnaire by its numeric ID.
     *
     * @param primaryKey the string representation of the questionnaire's numeric ID
     * @return the Questionnaire entity if found, or null if not found or ID is invalid
     * @throws IOException if an I/O error occurs during the API call
     */
    @Override
    public Questionnaire get(String primaryKey) throws IOException {
        return CatalogueTypes.toQuestionnaireEntity(questionnaireDaoImpl.getQuestionnaireById(Integer.valueOf(primaryKey), restClient));
    }

    @Override
    public Questionnaire update(Questionnaire questionnaire) throws IOException {
        return null;
    }

    @Override
    public void delete(String s) throws IOException {
        // not implemented
    }

    @Override
    public boolean contains(String s) throws IOException {
        return false;
    }

    @Override
    public List<Questionnaire> search(List<SearchCondition> list, long l, int i) throws IOException {
        return Collections.emptyList();
    }

    /**
     * Searches for questionnaires based on the provided search query with pagination.
     * <p>
     * Supports filtering by Id, Guid, Type, Alias, and Name fields using case-insensitive
     * partial matching. If no matches are found with a single condition, returns all questionnaires.
     * </p>
     *
     * @param searchQuery the search query containing filter conditions
     * @param pageIndex   the 1-based page index for pagination
     * @param pageSize    the maximum number of results per page
     * @return a paginated list of Questionnaire entities matching the search criteria
     * @throws IOException if an I/O error occurs during the API call
     */
    @Override
    public List<Questionnaire> search(SearchQuery searchQuery, long pageIndex, int pageSize) throws IOException {

        List<Questionnaire> questionnaires = new ArrayList<>();

        List<SearchCondition> conditions = searchQuery.getSearchConditions();
        for (SearchCondition con : conditions) {
            String match = (con.getOperand() != null ? String.valueOf(con.getOperand()).trim()
                    : "").trim().toLowerCase();

            Stream<Questionnaire> questionnaireStream;

            List<Questionnaire> collect = questionnaireDaoImpl.getAllQuestionnaire(
                    Constant.API_CORE_SYSTEM_QUESTIONNAIRE, restClient).stream().map(
                    CatalogueTypes::toQuestionnaireEntity).collect(Collectors.toList());

            switch (con.getFieldName()) {
                case "Id":
                    questionnaireStream = collect.stream()
                            .filter(e -> e.id != null && e.id.toLowerCase().contains(match));
                    break;
                case "Guid":
                    questionnaireStream = collect.stream()
                            .filter(e -> e.guid != null && e.guid.toLowerCase().contains(match));
                    break;
                case "Type":
                    questionnaireStream = collect.stream()
                            .filter(e -> e.type != null && e.type.toLowerCase().contains(match));
                    break;
                case "Alias":
                    questionnaireStream = collect.stream()
                            .filter(e -> e.alias != null && e.alias.toLowerCase().contains(match));
                    break;
                case "Name":
                    questionnaireStream = collect.stream()
                            .filter(e -> e.name != null && e.name.toLowerCase().contains(match));
                    break;
                default:
                    questionnaireStream = null;
                    break;

            }

            if (questionnaireStream != null) {
                questionnaires = questionnaireStream.collect(Collectors.toList());
            }

        }

        if (conditions.size() == 1 && questionnaires.isEmpty()) {
            questionnaires = questionnaireDaoImpl.getAllQuestionnaire(
                            Constant.API_CORE_SYSTEM_QUESTIONNAIRE, restClient)
                    .stream()
                    .map(CatalogueTypes::toQuestionnaireEntity).collect(Collectors.toList());
        }

        return questionnaires.stream().skip(Math.max((pageIndex - 1) * pageSize, 0)).limit(pageSize)
                .collect(Collectors.toList());


    }

    @Override
    public long count(List<SearchCondition> list) throws IOException {
        return 0;
    }

    @Override
    public List<String> lookup(Map<String, Object> map, List<String> list) throws IOException {
        return Collections.emptyList();
    }
}
