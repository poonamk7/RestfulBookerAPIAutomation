package com.restfulbooker.restapi.pojo;

import lombok.Getter;

@Getter
public class BookingResponse {

	private int bookingid;
	private Booking booking;

	public BookingResponse() {

	}

	public BookingResponse(int bookingid, Booking booking) {
		super();
		this.bookingid = bookingid;
		this.booking = booking;
	}

}
