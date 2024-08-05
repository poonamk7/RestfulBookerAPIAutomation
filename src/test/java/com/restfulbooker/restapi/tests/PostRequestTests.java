package com.restfulbooker.restapi.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restfulbooker.restapi.annotations.FrameworkAnnotation;
import com.restfulbooker.restapi.constants.FrameworkConstants;
import com.restfulbooker.restapi.pojo.Booking;
import com.restfulbooker.restapi.pojo.BookingBuilder;
import com.restfulbooker.restapi.pojo.BookingDates;
import com.restfulbooker.restapi.reports.ExtentLogger;
import com.restfulbooker.restapi.requestbuilder.RequestBuilder;
import com.restfulbooker.restapi.utils.ApiUtils;
import com.restfulbooker.restapi.utils.RandomUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestTests {
	
	@Test(description = "Validate creation of Booking detail on srver using POST request")
	@FrameworkAnnotation(author = "Poonam", category = "Smoke")
	public void createBookingTest() {
		
		//arrange
		BookingDates bookingDates =new BookingBuilder().builder()
									.setCheckin("2024-08-05")
									.setCheckout("2024-08-10")
									.buildBokingDates();
		
		Booking booking =new BookingBuilder().builder()
									.setFirstName(RandomUtils.getFirstName())
									.setLastName(RandomUtils.getLastName())
									.setTotalPrice(RandomUtils.getTotalPrice())
									.setDepositPaid(true)
									.setBookingDates(bookingDates)
									.setAdditionalNeeds(RandomUtils.getAdditionalDetails())
									.build();
		
		//action
		RequestSpecification requestSpecification =RequestBuilder.buildRequestForPostCall()
													.body(booking);
		
		ExtentLogger.logRequest(requestSpecification);
		
		Response response = requestSpecification.post("/booking");
		
		ExtentLogger.logResponse(response.asPrettyString());
		
		ApiUtils.writeResponseInJson(FrameworkConstants.getInstance().getResponseJsonFolderPath()+"ResponseFile1.json", response);
		
		//assert
		assertThat(response.getStatusCode()).isEqualTo(200);
		
		response.then().body(ApiUtils.getResponseJsonSchemaValidated());
	
	}
	
	
	@Test(description = "Validation creation of Booking detail using external file with POST request")
	@FrameworkAnnotation(author = "Poonam", category = "Sanity")
	public void createBookingWithExternalFile() {
		
		String requestBody =ApiUtils.readFileAndGetRequest(FrameworkConstants.getInstance().getRequestJsonFolderPath()+"createbooking.json");
		RequestSpecification requestSpecification  =RequestBuilder.buildRequestForPostCall().body(requestBody);
		ExtentLogger.logRequest(requestSpecification);
		
		Response response =requestSpecification.post("/booking");
		
		ExtentLogger.logResponse(response.asPrettyString());
		
		ApiUtils.writeResponseInJson(FrameworkConstants.getInstance().getResponseJsonFolderPath()+"responsefile.json", response);
		
	}
	
	@Test(dataProvider = "dataProvider", description = "Validate creation of multiple Booking details using DataProvide with POST request")
	@FrameworkAnnotation(author = "Poonam", category = "Smoke")
	public void postRequestWithMultiplData(Map<String, Object> data) {
		
		BookingDates bookingDates =new BookingBuilder().builder()
										.setCheckin(String.valueOf(data.get("checkin")))
										.setCheckout(String.valueOf(data.get("checkout")))
										.buildBokingDates();
		
		Booking booking = new BookingBuilder().builder().setFirstName(String.valueOf(data.get("firstname")))
		.setLastName(String.valueOf(data.get("lastname")))
		.setTotalPrice((int) data.get("totalPrice"))
		.setDepositPaid((boolean)data.get("depositpaid"))
		.setBookingDates(bookingDates)
		.setAdditionalNeeds(String.valueOf(data.get("additionalNeeds")))
		.build();
		
		RequestSpecification requestSpecification =RequestBuilder.buildRequestForPostCall()
		.body(booking);
		
		ExtentLogger.logRequest(requestSpecification);
		
		Response response =requestSpecification.post("/booking");
		
		ExtentLogger.logResponse(response.asPrettyString());
		
		assertThat(response.getStatusCode()).isEqualTo(200);
	
	}
	
	@DataProvider(name = "dataProvider")
	public Object[][] getData() {
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("checkin", "2024-08-01");
		map1.put("checkout", "2024-08-02");
		map1.put("firstname", RandomUtils.getFirstName());
		map1.put("lastname", RandomUtils.getLastName());
		map1.put("totalPrice", RandomUtils.getTotalPrice());
		map1.put("depositpaid", true);
		map1.put("additionalNeeds", RandomUtils.getAdditionalDetails());
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("checkin", "2024-08-03");
		map2.put("checkout", "2024-08-04");
		map2.put("firstname", RandomUtils.getFirstName());
		map2.put("lastname", RandomUtils.getLastName());
		map2.put("totalPrice", RandomUtils.getTotalPrice());
		map2.put("depositpaid", true);
		map2.put("additionalNeeds", RandomUtils.getAdditionalDetails());
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		map3.put("checkin", "2024-08-05");
		map3.put("checkout", "2024-08-06");
		map3.put("firstname", RandomUtils.getFirstName());
		map3.put("lastname", RandomUtils.getLastName());
		map3.put("totalPrice", RandomUtils.getTotalPrice());
		map3.put("depositpaid", true);
		map3.put("additionalNeeds", RandomUtils.getAdditionalDetails());
		
		Object[][] data = new Object[3][1];
		
		data[0][0] = map1;
		data[1][0] = map2;
		data[2][0] = map3;
		
		return data;
	
	}

}
