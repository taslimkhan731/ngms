package com.stl.ngms.setting.model;


public class ApplicationModel {
	
	private int registration_id;
	private String  prefix;
	private String first_name;
	private String last_name;
	private String  street;
	private String streetline;
	private int country_id;
	private String state;
	private int city_id;
	private String	 postal_code;
	private String  phone;
	private String  email;
	private String suggestions;
	private String status;
	
	
	public int getRegistration_id() {
		return registration_id;
	}
	public void setRegistration_id(int registration_id) {
		this.registration_id = registration_id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreetline() {
		return streetline;
	}
	public void setStreetline(String streetline) {
		this.streetline = streetline;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	
	public String getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "ApplicationModel [registration_id=" + registration_id
				+ ", prefix=" + prefix + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", street=" + street
				+ ", streetline=" + streetline + ", country_id=" + country_id
				+ ", state=" + state + ", city_id=" + city_id
				+ ", postal_code=" + postal_code + ", phone=" + phone
				+ ", email=" + email + ", suggestions=" + suggestions
				+ ", status=" + status + "]";
	}
	
	
	
}
