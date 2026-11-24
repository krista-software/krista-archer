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

package app.krista.extension.cyber_security.compliance.impl.util;

import com.google.gson.Gson;
import java.util.Map;

public class Constant {

  public static final app.krista.model.field.Field textField = new app.krista.model.field.Field("com.krista.fields.Text", Map.of(), Map.of());

  public static final String INSTANCE_NAME = "InstanceName";

  public static final String USERNAME = "Username";

  public static final String PASSWORD = "Password";

  public static final String USER_DOMAIN = "UserDomain";

  public static final String STATUS_CODE_IS = "Status code is ";

  public static final String APPLICATION_JSON = "application/json";

  public static final String YOU_ARE_NOT_AUTHORIZED = "You are not authorized";

  public static final String CONTENT_TYPE = "Content-Type";

  public static final String INSTANCEURL = "Instance Url";

  public static final String API_CORE_SYSTEM_APPLICATION = "/api/core/system/application";

  public static final String API_CORE_SYSTEM_QUESTIONNAIRE = "/api/core/system/questionnaire";

  public static final String BAD_REQUEST = "Bad Request";

  public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";

  public static final Gson GSON = new Gson();

  public static final String EMPTY_JSON = "{}";

  public static final String CONTENTAPI = "contentapi";

  public static final String THOUSAND = "1000";

  private Constant(){

  }
}
