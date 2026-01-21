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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import app.krista.extension.cyber_security.compliance.RestClient;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ContentDaoImplTest {

  ContentDaoImpl contentDaoImpl;

  RestClient restClient;


  @BeforeEach
  void setUp() {
    contentDaoImpl = new ContentDaoImpl();
    restClient = Mockito.mock(RestClient.class);
  }

  @Test
  void getAllRecordOfTheApplication() throws URISyntaxException, IOException {

    File file = resolveFileFromResources("AllRecordOfApplication.JSON");
    FileInputStream fileInputStream = new FileInputStream(file);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    String allApplication = bufferedReader.readLine();
    Response mockResponse = new Response.Builder()
        .request(new Request.Builder().url("https://example.com/users/12345").build())
        .protocol(Protocol.HTTP_1_1)
        .code(200)
        .message("OK")
        .body(ResponseBody.create(MediaType.parse("application/json"), allApplication)).build();

    when(restClient.get("/contentapi/"+"Avadh_Second?$top=1000")).thenReturn(mockResponse);

    Assertions.assertNotNull(contentDaoImpl.getRecordsOfTheApplication("Avadh_Second", restClient, null, null));
  }

  @Test
  void getIndividualRecordDetail() throws URISyntaxException, IOException {

    File file = resolveFileFromResources("IndividualRecord.JSON");
    FileInputStream fileInputStream = new FileInputStream(file);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
    String allApplication = bufferedReader.readLine();
    Response mockResponse = new Response.Builder()
        .request(new Request.Builder().url("https://example.com/users/12345").build())
        .protocol(Protocol.HTTP_1_1)
        .code(200)
        .message("OK")
        .body(ResponseBody.create(MediaType.parse("application/json"), allApplication)).build();

    when(restClient.get("/contentapi/"+"Avadh_Second"+"(" + 308626 + ")")).thenReturn(mockResponse);

    Assertions.assertNotNull( contentDaoImpl.getIndividualRecordDetail(restClient,"Avadh_Second",308626));

  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(
        Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }
}
