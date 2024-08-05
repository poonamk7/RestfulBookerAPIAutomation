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

public class PatchRequestTest {
	
	@Test(description = "Validate partial upadte of Booking Detail using PATCH request")
	@FrameworkAnnotation(author = "Poonam", category = "Regression")
	public void partialUpdateBookingDetail() {
		
		BookingDates bookingDates =new BookingBuilder().builder().setCheckin("2024-08-05")
				.setCheckout("2024-08-07")
				.buildBokingDates();
				
				Booking booking =new BookingBuilder().builder().setFirstName(RandomUtils.getFirstName())
				.setLastName(RandomUtils.getLastName())
				.setTotalPrice(RandomUtils.getTotalPrice())
				.setDepositPaid(false)
				.setBookingDates(bookingDates)
				.setAdditionalNeeds(RandomUtils.getAdditionalDetails())
				.build();
				
				RequestSpecification requestSpecification =RequestBuilder.buildRequestForPostCall().body(booking);
				
				ExtentLogger.logRequest(requestSpecification);
				
				Response response =requestSpecification.when().post("/booking");
				
				BookingResponse bookingResponse =response.as(BookingResponse.class);
				
				int bookingId = bookingResponse.getBookingid();
				String checkin = bookingResponse.getBooking().getBookingdates().getCheckin();
				String checkout = bookingResponse.getBooking().getBookingdates().getCheckout();
				int totalPrice = bookingResponse.getBooking().getTotalprice();
				String additionalneeds = bookingResponse.getBooking().getAdditionalneeds();
				
				Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
				
				//Partially update booking details using bookingId
				
				BookingDates bookingDatesForUpadte =new BookingBuilder().builder().setCheckin(checkin)
						.setCheckout(checkout)
						.buildBokingDates();
				
				Booking bookingForPartialUpdate =new BookingBuilder().builder().setFirstName(RandomUtils.getFirstName())
						.setLastName(RandomUtils.getLastName())
						.setTotalPrice(totalPrice)
						.setDepositPaid(true)
						.setBookingDates(bookingDates)
						.setAdditionalNeeds(additionalneeds)
						.build();
				
				Response responseForPartialUpdate =RequestBuilder.buildRequestForPostCall()
				.header("Cookie","token=abc123")
				.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
				.pathParam("id", bookingId)
				.body(bookingForPartialUpdate)
				.when()
				.patch("/booking/{id}");
				
				Assertions.assertThat(responseForPartialUpdate.getStatusCode()).isEqualTo(200);
				
				ExtentLogger.logResponse(responseForPartialUpdate.asPrettyString());
		
	}

}
