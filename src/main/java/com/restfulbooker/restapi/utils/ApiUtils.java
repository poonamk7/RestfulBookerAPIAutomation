package com.restfulbooker.restapi.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.restfulbooker.restapi.constants.FrameworkConstants;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import lombok.SneakyThrows;

public final class ApiUtils {
	
	private ApiUtils() {
		
	}
	
	@SneakyThrows
	public static String readFileAndGetRequest(String filePath) {
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}
	
	@SneakyThrows
	public static void writeResponseInJson(String filePath, Response response) {
		Files.write(Paths.get(filePath), response.asByteArray());
	}
	
	public static JsonSchemaValidator getResponseJsonSchemaValidated() {
		return JsonSchemaValidator.matchesJsonSchema(new File(FrameworkConstants.getInstance().getJsonSchemaFilePath()));
	}

}
