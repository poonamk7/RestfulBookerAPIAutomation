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

public class PutRequestTest {
	
	@Test(description = "Validate updation of Booking detail using PUT request")
	@FrameworkAnnotation(author = "Poonam", category = "Functional")
	public void updateBookingDetail() {
		
		BookingDates bookingDates =new BookingBuilder().builder().setCheckin("2024-08-01")
		.setCheckout("2024-08-03")
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
		String firstName = bookingResponse.getBooking().getFirstname();
		String lastName = bookingResponse.getBooking().getLastname();
		int totalPrice = bookingResponse.getBooking().getTotalprice();
		
		Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
		
		//Update booking details using bookingId
		
		BookingDates bookingDatesForUpadte =new BookingBuilder().builder().setCheckin("2024-08-08")
				.setCheckout("2024-08-09")
				.buildBokingDates();
		
		Booking bookingForUpdate =new BookingBuilder().builder().setFirstName(firstName)
				.setLastName(lastName)
				.setTotalPrice(totalPrice)
				.setDepositPaid(true)
				.setBookingDates(bookingDates)
				.setAdditionalNeeds(RandomUtils.getAdditionalDetails())
				.build();
		
		Response responseForUpdate =RequestBuilder.buildRequestForPostCall()
		.header("Cookie","token=abc123")
		.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
		.pathParam("id", bookingId)
		.body(bookingForUpdate)
		.when()
		.put("/booking/{id}");
		
		Assertions.assertThat(responseForUpdate.getStatusCode()).isEqualTo(200);
		
		ExtentLogger.logResponse(responseForUpdate.asPrettyString());

	}

}
