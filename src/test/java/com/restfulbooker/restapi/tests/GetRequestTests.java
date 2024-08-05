package com.restfulbooker.restapi.tests;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

import com.restfulbooker.restapi.annotations.FrameworkAnnotation;
import com.restfulbooker.restapi.pojo.Booking;
import com.restfulbooker.restapi.pojo.BookingBuilder;
import com.restfulbooker.restapi.pojo.BookingDates;
import com.restfulbooker.restapi.pojo.BookingResponse;
import com.restfulbooker.restapi.reports.ExtentLogger;
import com.restfulbooker.restapi.requestbuilder.RequestBuilder;
import com.restfulbooker.restapi.utils.RandomUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequestTests {
	
	@Test(description = "Validate all the Booking details are retrieved using GET request")
	@FrameworkAnnotation(author = "Poonam", category = "Functional")
	public void getBookingsDetail() {
		
		Response response =RequestBuilder.buildRequestForGetCall()
		.when()
		.get("/booking");
		
		ExtentLogger.logResponse(response.asPrettyString());
		
		assertThat(response.getStatusCode()).isEqualTo(200);
	
	}
	
	@Test(description = "Validate specific Booking detail based on BookingId using GET request")
	@FrameworkAnnotation(author = "Poonam", category = "Functional")
	public void getBookingDetail() {
		
		BookingDates bookingDates =new BookingBuilder()
									.builder()
									.setCheckin("2024-08-01")
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
		
		Response response =RequestBuilder.buildRequestForPostCall()
									.body(booking)
									.when()
									.post("/booking");
		
		BookingResponse bookingResponse =response.as(BookingResponse.class);
		
		int bookingId =bookingResponse.getBookingid();
		
		//Created Booking and fetched bookingId from response
		
		Response getResponse =RequestBuilder.buildRequestForGetCall()
									.pathParam("id", bookingId)
									.contentType(ContentType.JSON)
									.when()
									.get("/booking/{id}");
		
		ExtentLogger.logResponse(getResponse.asPrettyString());
		
		assertThat(getResponse.getStatusCode()).isEqualTo(200);
		
	}

}
