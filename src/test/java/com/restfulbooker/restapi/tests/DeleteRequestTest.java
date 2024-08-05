package com.restfulbooker.restapi.tests;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.restfulbooker.restapi.annotations.FrameworkAnnotation;
import com.restfulbooker.restapi.pojo.Booking;
import com.restfulbooker.restapi.pojo.BookingBuilder;
import com.restfulbooker.restapi.pojo.BookingDates;
import com.restfulbooker.restapi.pojo.BookingResponse;
import com.restfulbooker.restapi.reports.ExtentLogger;
import com.restfulbooker.restapi.requestbuilder.RequestBuilder;
import com.restfulbooker.restapi.utils.RandomUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteRequestTest {
	
	@Test(description = "Validate deletion Booking detail using DELETE request")
	@FrameworkAnnotation(author = "Poonam", category = "Smoke")
	public void deleteBookingDetail() {
		
		BookingDates bookingDates =new BookingBuilder().builder().setCheckin("2024-08-06")
				.setCheckout("2024-08-10")
				.buildBokingDates();
				
				Booking booking =new BookingBuilder().builder().setFirstName(RandomUtils.getFirstName())
				.setLastName(RandomUtils.getLastName())
				.setTotalPrice(RandomUtils.getTotalPrice())
				.setDepositPaid(true)
				.setBookingDates(bookingDates)
				.setAdditionalNeeds(RandomUtils.getAdditionalDetails())
				.build();
				
				RequestSpecification requestSpecification =RequestBuilder.buildRequestForPostCall().body(booking);
				
				ExtentLogger.logRequest(requestSpecification);
				
				Response response =requestSpecification.when().post("/booking");
				
				BookingResponse bookingResponse =response.as(BookingResponse.class);
				
				int bookingId = bookingResponse.getBookingid();
				
				Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
				
				//Delete booking details using bookingId
				
				Response responseForDelete =RequestBuilder.buildRequestForPostCall()
				.header("Cookie","token=abc123")
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.pathParam("id", bookingId)
				.when()
				.delete("/booking/{id}");
				
				Assertions.assertThat(responseForDelete.getStatusCode()).isEqualTo(201);
				
	
	}

}
