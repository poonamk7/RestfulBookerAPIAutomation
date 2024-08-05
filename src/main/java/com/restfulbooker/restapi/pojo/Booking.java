package com.restfulbooker.restapi.pojo;

import lombok.Getter;

@Getter
public class Booking {
	
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;
	
	public Booking() {
		
	}

	public Booking(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDates bookingdates,
			String additionalneeds) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.totalprice = totalprice;
		this.depositpaid = depositpaid;
		this.bookingdates = bookingdates;
		this.additionalneeds = additionalneeds;
	}
	
	

}
