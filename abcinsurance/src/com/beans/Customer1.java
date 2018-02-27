package com.beans;

import java.io.Serializable;

public class Customer1 implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	private int termFactor;
	private String typeOfConstruction;
	private String loginId;
	private String customerName;
	private String dateOfBirth;
	private long phoneNumber;
	private String email;
	private String password;
	private String gender;
	private String Address;
	private String vehice;
	private String house;
	private String vehicleTypeFac;
	private int smokersForHouse;
	private int customerId;
	public int getTermFactor() {
		return termFactor;
	}
	public void setTermFactor(int termFactor) {
		this.termFactor = termFactor;
	}
	public String getTypeOfConstruction() {
		return typeOfConstruction;
	}
	public void setTypeOfConstruction(String typeOfConstruction) {
		this.typeOfConstruction = typeOfConstruction;
	}
	public int getSmokersForHouse() {
		return smokersForHouse;
	}
	public void setSmokersForHouse(int smokersForHouse) {
		this.smokersForHouse = smokersForHouse;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getVehice() {
		return vehice;
	}
	public void setVehice(String vehice) {
		this.vehice = vehice;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getVehicleTypeFac() {
		return vehicleTypeFac;
	}
	public void setVehicleTypeFac(String vehicleTypeFac) {
		this.vehicleTypeFac = vehicleTypeFac;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Customer1 [termFactor=" + termFactor + ", typeOfConstruction=" + typeOfConstruction + ", loginId="
				+ loginId + ", customerName=" + customerName + ", dateOfBirth=" + dateOfBirth + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", password=" + password + ", gender=" + gender + ", Address="
				+ Address + ", vehice=" + vehice + ", house=" + house + ", vehicleTypeFac=" + vehicleTypeFac
				+ ", smokersForHouse=" + smokersForHouse + ", customerId=" + customerId + "]";
	}

	
	
	}
	
	
