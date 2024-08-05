package com.restfulbooker.restapi.pojo;

import lombok.Getter;

@Getter
public class BookingDates {
	
	private String checkin;
	private String checkout;
	
	public BookingDates() {
		
	}

	public BookingDates(String checkin, String checkout) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	

}
