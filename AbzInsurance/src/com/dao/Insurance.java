package com.dao;

public interface Insurance {
	void login();

	void addCustomer();

	void viewAllCustomers();

	void viewParticularCustomer(int userId);

	void deleteCustomer();

	void changePassword();

	void viewAllInsurance();

	void viewParticularInsurance(int allInsuranceId);

	void amountRefundable(int userId);

	void changeMobileNumber();

	void changeAddress();

	void insurancePolicy(int userId);

	void viewActiveInsurances();
	
	void logout();
}
