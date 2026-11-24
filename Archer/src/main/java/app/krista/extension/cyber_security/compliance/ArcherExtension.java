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

package app.krista.extension.cyber_security.compliance;

import app.krista.extension.cyber_security.compliance.impl.util.Constant;
import app.krista.extension.executor.Invoker;
import app.krista.extension.impl.anno.*;

import javax.inject.Inject;
import java.util.Map;

@Java(version = Java.Version.JAVA_21)
@Field.Text(name = Constant.INSTANCE_NAME)
@Field.Text(name = Constant.USERNAME)
@Field.Text(name = Constant.PASSWORD)
@Field.Text(name = Constant.USER_DOMAIN, required = false)
@Field.Text(name = Constant.INSTANCEURL)
@Extension(version = "2.0.4", name="Archer")
@StaticResource(path = "docs", file = "docs")
public class ArcherExtension {

    Invoker invoker;

    @Inject
    public ArcherExtension(Invoker invoker) {
        this.invoker = invoker;
    }

    /**
     * Validates the connection attributes by attempting to authenticate with the Archer API.
     * <p>
     * This method is invoked when the user clicks "Validate Attributes" in the extension setup.
     * It tests the provided credentials by attempting to create a session token.
     * </p>
     *
     * @param connectionAttributes the map containing Instance URL, Username, Password,
     *                             Instance Name, and optionally User Domain
     * @throws IllegalArgumentException if authentication fails due to invalid credentials
     */
    @InvokerRequest(InvokerRequest.Type.VALIDATE_ATTRIBUTES)
    public void validateConnection(Map<String, Object> connectionAttributes) {
        testAndValidAttribute(connectionAttributes);
    }

    /**
     * Provides custom tabs for the extension configuration UI.
     *
     * @return a map containing tab names and their corresponding static resource paths
     */
    @InvokerRequest(InvokerRequest.Type.CUSTOM_TABS)
    public Map<String, String> customTabs() {
        return Map.of("Documentation", "static/docs");
    }

    /**
     * Tests the connection to the Archer API using the stored invoker attributes.
     * <p>
     * This method is invoked when the user clicks "Test Connection" in the extension setup.
     * It uses the previously saved credentials to verify connectivity.
     * </p>
     *
     * @throws IllegalArgumentException if authentication fails due to invalid credentials
     */
    @InvokerRequest(InvokerRequest.Type.TEST_CONNECTION)
    public void testConnection() {
        testAndValidAttribute(invoker.getAttributes());
    }


    /**
     * Tests and validates the provided credentials by attempting to create a session token.
     *
     * @param credentials the map containing authentication credentials
     * @throws IllegalArgumentException if authentication fails
     */
    private void testAndValidAttribute(Map<String, Object> credentials) {
        RestClient restClient = new RestClient(credentials);
        restClient.createSessionToken(credentials);
    }
}
