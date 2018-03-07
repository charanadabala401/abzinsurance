package com.beans;

import java.sql.Date;
import java.time.Year;

public class UserBean {
	private int userId;
	private String firstName;
	private String email;
	private String password;
	private String roleName;
	private int roleId;
	private int previlageId;
	private String previlageName;
	private int insuranceId;
	private String insuranceName;
	private int typeId;
	private String typeName;
	private int years;
	private double cost;
	private Date date;
	private String oldPassword;
	private long oldMobileNumber;
	private Long mobileNumber;
	private String address;
	private String oldAddress;
	private int allInsuranceId;
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAllInsuranceId() {
		return allInsuranceId;
	}

	public void setAllInsuranceId(int allInsuranceId) {
		this.allInsuranceId = allInsuranceId;
	}

	public long getOldMobileNumber() {
		return oldMobileNumber;
	}

	public void setOldMobileNumber(long oldMobileNumber) {
		this.oldMobileNumber = oldMobileNumber;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOldAddress() {
		return oldAddress;
	}

	public void setOldAddress(String oldAddress) {
		this.oldAddress = oldAddress;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public int getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}

	public String getInsuranceName() {
		return insuranceName;
	}

	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPrevilageId() {
		return previlageId;
	}

	public void setPrevilageId(int previlageId) {
		this.previlageId = previlageId;
	}

	public String getPrevilageName() {
		return previlageName;
	}

	public void setPrevilageName(String previlageName) {
		this.previlageName = previlageName;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", firstName=" + firstName
				+ ", email=" + email + ", password=" + password + ", roleName="
				+ roleName + ", roleId=" + roleId + ", previlageId="
				+ previlageId + ", previlageName=" + previlageName
				+ ", insuranceId=" + insuranceId + ", insuranceName="
				+ insuranceName + ", typeId=" + typeId + ", typeName="
				+ typeName + ", years=" + years + ", cost=" + cost + ", date="
				+ date + ", oldPassword=" + oldPassword + ", oldMobileNumber="
				+ oldMobileNumber + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + ", oldAddress=" + oldAddress
				+ ", allInsuranceId=" + allInsuranceId + ", status=" + status
				+ "]";
	}

}
