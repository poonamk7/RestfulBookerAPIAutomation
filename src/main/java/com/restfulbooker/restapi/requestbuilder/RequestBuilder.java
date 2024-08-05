package com.restfulbooker.restapi.requestbuilder;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import com.restfulbooker.restapi.enums.PropertiesType;
import com.restfulbooker.restapi.utils.PropertyUtils;

public final class RequestBuilder {
	
	private RequestBuilder() {
		
	}
	
	public static RequestSpecification buildRequestForGetCall() {
		
		return given().baseUri(PropertyUtils.getValue(PropertiesType.BASEURL))
				.contentType(ContentType.JSON)
				.log()
				.all();
	}
	
	public static RequestSpecification buildRequestForPostCall() {
		return given().baseUri(PropertyUtils.getValue(PropertiesType.BASEURL))
				.contentType(ContentType.JSON)
				.header("Accept", "application/json")
				.log()
				.all();
	}

}
