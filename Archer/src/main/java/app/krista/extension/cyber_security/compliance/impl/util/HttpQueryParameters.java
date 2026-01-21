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

public class HttpQueryParameters {

    public static final String SKIP_$= "?$skip=";

    public static final String TOP_$ = "?$top=";

    public static final String AMPERSAND = "&";

    public static final String SKIP = "$skip=";

    private HttpQueryParameters(){
    }
 /**
 * Builds an OData query string with $top and $skip parameters.
 *
 * @param top The maximum number of items to return.
 * @param skip The number of items to skip before returning results.
 * @return The OData query string with $top and $skip parameters appended.

 * This method will append $top=topValue if top is not null.
 * It will append $skip=skipValue if skip is not null.
 * If both top and skip values are provided, it will append them together with & between.

 * Examples:

 * sortingAndOrderBy(null, "10")
 * Returns: "?$skip=10"

 * sortingAndOrderBy("5", null)
 * Returns: "?$top=5"

 * sortingAndOrderBy("5", "10")
 * Returns: "?$top=5&$skip=10"
 */
   public static String sortingAndOrderBy(String top, String skip) {
        StringBuilder queryBuilder = new StringBuilder();

        if(top ==null && skip != null){
          return  queryBuilder.append(SKIP_$).append(skip).toString();
        }

        if (top != null) {
            queryBuilder.append(TOP_$).append(top);
        }

        if (skip != null) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(AMPERSAND);
            }
            queryBuilder.append(SKIP).append(skip);
        }

        return queryBuilder.toString();
    }


}
