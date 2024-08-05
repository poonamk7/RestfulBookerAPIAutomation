package com.restfulbooker.restapi.pojo;

//Implemented Builder Design Pattern to maintain Immutability of Booking object and set values trough Builder class

public class BookingBuilder {

	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String checkin;
	private String checkout;
	private String additionalneeds;

	public BookingBuilder setFirstName(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public BookingBuilder setLastName(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public BookingBuilder setDepositPaid(boolean depositpaid) {
		this.depositpaid = depositpaid;
		return this;
	}

	public BookingBuilder setTotalPrice(int totalprice) {
		this.totalprice = totalprice;
		return this;
	}

	public BookingBuilder setBookingDates(BookingDates bookingdates) {
		this.bookingdates = bookingdates;
		return this;
	}

	public BookingBuilder setCheckin(String checkin) {
		this.checkin = checkin;
		return this;
	}

	public BookingBuilder setCheckout(String checkout) {
		this.checkout = checkout;
		return this;
	}

	public BookingBuilder setAdditionalNeeds(String additionalneeds) {
		this.additionalneeds = additionalneeds;
		return this;
	}

	public BookingBuilder builder() {
		return this;
	}

	public Booking build() {
		return new Booking(this.firstname, this.lastname, this.totalprice, this.depositpaid, this.bookingdates,
				this.additionalneeds);
	}

	public BookingDates buildBokingDates() {
		return new BookingDates(this.checkin, this.checkout);
	}

}
