package com.daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.beans.UserBean;
import com.controller.InsuranceProject;
import com.dao.Insurance;
import com.dao.Queries;

public class InsuranceImpl implements Insurance {
	Connection con = DbConnection.getMySqlConnection();
	PreparedStatement ps = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	
	@Override
	public void login() {
		UserBean ub = new UserBean();
		System.out.println("Enter Email");
		Scanner sc = new Scanner(System.in);
		ub.setEmail(sc.next());
		System.out.println("Enter Password");
		ub.setPassword(sc.next());
		String dbEmail = "";
		String dbPassword = "";
		String dbrole = "";
		int dbuserId = 0;
		try {
			ps = con.prepareStatement(Queries.LOGIN);
			ps.setString(1, ub.getEmail());
			ps.setString(2, ub.getPassword());
			rs = ps.executeQuery();
			while (rs.next()) {
				dbuserId = rs.getInt(6);
				dbEmail = rs.getString(1);
				dbPassword = rs.getString(2);
				dbrole = rs.getString(3);

			}
			if (dbEmail.equals(ub.getEmail())
					&& dbPassword.equals(ub.getPassword())
					&& dbrole.equals("admin")) {
				System.out.println("Welcome To Admin");
				rs.beforeFirst();
				while (rs.next()) {
					System.out.println(rs.getInt(4) + " " + rs.getString(5));
				}
				int ch1 = 1;
				while (ch1 == 1) {
					System.out.println("Enter your choice");
					int ch = sc.nextInt();
					if (ch == 1) {
						addCustomer();
					} else if (ch == 2) {
						viewAllCustomers();
					} else if (ch == 3) {
						System.out.println("Enter UserId");
						int userId = sc.nextInt();
						viewParticularCustomer(userId);
					} else if (ch == 4) {
						deleteCustomer();
					} else if (ch == 6) {
						viewAllInsurance();
					} else if (ch == 7) {
						System.out.println("Enter UserId");
						int userId = sc.nextInt();
						viewParticularInsurance(userId);
					} else if (ch == 12) {
						viewActiveInsurances();
					} else if (ch == 13) {
						logout();
					}
					System.out
							.println("Do You Want To Continue Press 1 Else Any Number");
					ch1 = sc.nextInt();
					if (ch1 == 1) {
						System.out.println("Enter Your Choice");
						System.out
								.println(" 1.AddAccounts \n 2.ViewAllAccounts \n 3.ViewParticularAccount \n 4.DeleteAccount \n 6.ViewAllInsurance \n 7.ViewParticularInsurance \n 12.ViewActiveInsurances \n 13.Logout");
					} else {
						InsuranceProject.menu();
					}
				}
			}

			else if (dbEmail.equals(ub.getEmail())
					&& dbPassword.equals(ub.getPassword())
					&& dbrole.equals("user")) {
				System.out.println("Welcome To User");
				rs.beforeFirst();
				while (rs.next()) {
					System.out.println(rs.getInt(4) + " " + rs.getString(5));
				}
				int ch1 = 1;
				while (ch1 == 1) {
					System.out.println("Enter your choice");
					int ch = sc.nextInt();
					if (ch == 5) {
						changePassword();
					} else if (ch == 8) {
						amountRefundable(dbuserId);
					} else if (ch == 9) {
						changeMobileNumber();

					} else if (ch == 10) {
						changeAddress();
					} else if (ch == 11) {
						insurancePolicy(dbuserId);
					} else if (ch == 13) {
						logout();
					}
					System.out
							.println("If you want to continue press 1 else any number");
					ch1 = sc.nextInt();
					if (ch1 == 1) {
						System.out.println("Enter your choice");
						System.out
								.println(" 5.ChangePassword\n 8.AmountRefundable \n 9.ChangeMobileNumber \n 10.ChangeAddress \n 11.InsurancePolicy \n 13.Logout");
					} else {
						InsuranceProject.menu();
					}
				}
			} else {
				System.out.println("Invalid Email And Password");
				InsuranceProject.menu();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addCustomer() {
		Connection con = DbConnection.getMySqlConnection();
		PreparedStatement ps = null;
		UserBean b = new UserBean();
		System.out.println("Enter the FirstName");

		b.setFirstName(sc.next());
		while (!b.getFirstName().matches("[A-Z][a-zA-Z]*")) {
			System.err.println("Enter Only characters");
			b.setFirstName(sc.next());
		}
		b.setFirstName(b.getFirstName());
		System.out.println("Enter the Email");
		b.setEmail(sc.next());
		System.out.println("Enter the Password");
		b.setPassword(sc.next());
		System.out.println("Enter the MobileNumber");
		String mobileNo = sc.next();

		while (!mobileNo.matches("^1?(\\d{10})")) {
			System.err
					.println(" Mobile Number Accepts Only numbers and length should be 10 digits");
			mobileNo = sc.next();

		}
		b.setMobileNumber(Long.parseLong(mobileNo));

		System.out.println("enter the Address");
		b.setAddress(sc.next());
		int role = 2;
		b.setRoleId(role);
		try {
			ps = con.prepareStatement(Queries.INSERT);
			ps.setString(1, b.getFirstName());
			ps.setString(2, b.getEmail());
			ps.setString(3, b.getPassword());
			ps.setInt(4, b.getRoleId());
			ps.setLong(5, b.getMobileNumber());
			ps.setString(6, b.getAddress());
			int bb = ps.executeUpdate();
			if (bb == 1) {
				System.out.println("Registration Completed");

			} else {
				System.out.println("Registration Failed");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewAllCustomers() {
		try {
			ps = con.prepareStatement(Queries.VIEWALL);
			rs = ps.executeQuery();
			System.out.println("User Id" + "\tUser Name" + "\tEmailId"
					+ "\tMobile Number");
			System.out
					.println("-----------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3) + "\t" + rs.getLong(6));
				System.out.println("");

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void viewParticularCustomer(int userId) {
		UserBean ub = new UserBean();
		ub.setUserId(userId);

		try {
			ps = con.prepareStatement(Queries.VIEWPARTICULARDETAILS);
			ps.setInt(1, ub.getUserId());
			rs = ps.executeQuery();

			if (userId == ub.getUserId()) {
				while (rs.next()) {
					System.out.println("***********UserDetails***********");
					System.out.println("UserId: " + rs.getInt("user_id"));
					System.out.println("FirstName: " + rs.getString(2));
					System.out.println("EmailId: " + rs.getString(3));
					System.out.println("MobileNumber: " + rs.getLong(6));
					System.out.println("Address: " + rs.getString(7));
					break;
				}

			} else {
				System.out.println("entered UserId doesn't exists");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteCustomer() {
		try {
			ps = con.prepareStatement(Queries.USERDETAILS);
			rs = ps.executeQuery();
			System.out.println("User Id" + "\tUser Name" + "\tEmailId"
					+ "\tMobile Number" + "\tAddress");
			System.out
					.println("-----------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3) + "\t" + rs.getLong(6) + "\t"
						+ rs.getString(7));
				System.out.println("");

			}
			System.out.println();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		System.out.println("Enter UserId");
		int c = sc.nextInt();
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(Queries.DELETE);
			ps.setInt(1, c);
			int d = ps.executeUpdate();
			if (d == 1) {
				System.out.println("do u want to delete"
						+ "permanently press 1 else any number");
				int x = sc.nextInt();
				if (x == 1) {
					con.commit();
					System.out.println("Customer Deleted");
				} else {
					con.rollback();
				}
			}

			else {
				System.out.println("No Record Existed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void changePassword() {
		Connection con = DbConnection.getMySqlConnection();
		PreparedStatement ps = null;
		System.out.println("Enter The Existing Password");
		UserBean b = new UserBean();
		b.setOldPassword(sc.next());
		System.out.println("Enter The New Password");
		b.setPassword(sc.next());
		try {
			// con.setAutoCommit(false);
			ps = con.prepareStatement(Queries.PASSWORD);
			ps.setString(1, b.getPassword());
			ps.setString(2, b.getOldPassword());
			int bb = ps.executeUpdate();
			if (bb == 1) {
				System.out.println("Password Updated");
				InsuranceProject.menu();
				/*
				 * System.out.println("Do you want to Update Password"+
				 * "permanently press 1 else any number"); int x=sc.nextInt();
				 * if (x==1) { con.commit();
				 * System.out.println("Password Updated"); } else
				 * {con.rollback();}
				 */
			} else {
				System.out
						.println("Password Not Updated.You Entered Wrong Existing Password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void viewAllInsurance() {
		try {
			ps = con.prepareStatement(Queries.VIEWALLINSURANCE);
			rs = ps.executeQuery();
			System.out.println("**********Insurance Details***********");
			System.out.println("Name " + "\t\tTypeName " + "\tMobile Number "
					+ "\tAddress " + "\tInsuranceType " + "\t\tYears "
					+ "\tCost " + "\tDate "+ "\tStatus ");
			System.out
					.println("---------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {

				System.out.println(rs.getString(1) + "\t\t" + rs.getString(2)
						+ "\t" + rs.getLong(7) + "\t" + rs.getString(8) + "\t"
						+ rs.getString(3) + "\t" + rs.getInt(4) + "\t"
						+ rs.getDouble(5) + "\t" + rs.getDate(6)+ rs.getString(9));
				System.out.println("");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewParticularInsurance(int userId) {
		UserBean ub = new UserBean();
		ub.setUserId(userId);

		try {
			ps = con.prepareStatement(Queries.VIEWPARTICULARINSURANCE);
			ps.setInt(1, ub.getUserId());
			rs = ps.executeQuery();
			System.out.println("**********Insurance Details***********");
			if (userId == ub.getUserId()) {
				while (rs.next()) {

					System.out.println("Name: " + rs.getString(2));
					System.out.println("TypeName: " + rs.getString(3));
					System.out.println("Mobile Number: " + rs.getLong(8));
					System.out.println("Address: " + rs.getString(9));
					System.out.println("InsuranceType: " + rs.getString(4));
					System.out.println("Years: " + rs.getInt(5));
					System.out.println("Cost: " + rs.getDouble(6));
					System.out.println("Date:" + rs.getDate(7));
					System.out.println("Status: " + rs.getString(10));
				}

			} else {
				System.out.println("Entered UserId Doesn't Exists");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void amountRefundable(int userID) {
		UserBean ub = new UserBean();
		ub.setUserId(userID);
		try {

			ps = con.prepareStatement(Queries.ACTIVEPOLICIES);
			ps.setInt(1, ub.getUserId()); 
			rs = ps.executeQuery();
			System.out.println("All Insurance Id" + "\tYears" + "\tCost"
					+ "\tDate" + "\tStatus" + "\tInsurance Type"
					+ "\tType Name");
			System.out
					.println("----------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(2) + "\t" + rs.getInt(3) + "\t"
						+ rs.getDouble(4) + "\t" + rs.getDate(5) + "\t"
						+ rs.getString(6) + "\t" + rs.getString(7) + "\t"
						+ rs.getString(8));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		double refund;
		System.out.println("Enter The AllInsuranceId ");
		System.out.println();
		int a = sc.nextInt();
		try {
			ps = con.prepareStatement(Queries.YEARS,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ps.setInt(1, a);

			rs = ps.executeQuery();
			while (rs.next()) {
				int c = rs.getInt(4);
				if (c == 1) {
					System.out.println(c);
					refund = rs.getDouble(5) * 0.75;
					System.out.println("Refund Amount Is: " + refund);
					rs.updateString(7, "deactive");
					rs.updateRow();
				} else if (c == 2) {
					System.out.println(c);
					refund = rs.getDouble(5) * 0.5;
					System.out.println("Refund Amount Is: " + refund);
					rs.updateString(7, "deactive");
					rs.updateRow();
				} else {
					System.out.println("No Refund Amount");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void changeMobileNumber() {
		Connection con = DbConnection.getMySqlConnection();
		PreparedStatement ps = null;
		System.out.println("Enter The Existing MobileNumber");
		UserBean b = new UserBean();
		b.setOldMobileNumber(sc.nextLong());
		System.out.println("Enter The New MobileNumber");
		long mobileNo=sc.nextLong();
		
		
		if (mobileNo >= 1000000000 && mobileNo <= 9999999999l){
			b.setMobileNumber(mobileNo);
		}
		else{
			System.out.println("phone number should contain 10 digits");
			changeMobileNumber();
		}
		try {
			// con.setAutoCommit(false);
			ps = con.prepareStatement(Queries.MOBILENUMBER);
			ps.setLong(1, b.getMobileNumber());
			ps.setLong(2, b.getOldMobileNumber());
			int bb = ps.executeUpdate();
			if (bb == 1) {
				System.out.println("MobileNumber Updated");
				/*
				 * System.out.println("Do you want to Update MobileNumber"+
				 * "permanently press 1 else any number"); int x=sc.nextInt();
				 * if (x==1) { con.commit();
				 * System.out.println("MobileNumber Updated"); } else
				 * {con.rollback();}
				 */
			} else {
				System.out
						.println("MobileNumber Not Updated.You Entered Wrong Existing MobileNumber");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void changeAddress() {
		Connection con = DbConnection.getMySqlConnection();
		PreparedStatement ps = null;
		System.out.println("Enter The existing Address");
		UserBean b = new UserBean();
		b.setOldAddress(sc.next());
		System.out.println("Enter The New Address");
		b.setAddress(sc.next());
		try {
			// con.setAutoCommit(false);
			ps = con.prepareStatement(Queries.ADDRESS);
			ps.setString(1, b.getAddress());
			ps.setString(2, b.getOldAddress());
			int bb = ps.executeUpdate();
			if (bb == 1) {
				System.out.println("Address updated");
				/*
				 * System.out.println("Do you want to Update Address"+
				 * "permanently press 1 else any number"); int x=sc.nextInt();
				 * if (x==1) { con.commit();
				 * System.out.println("Address updated"); } else
				 * {con.rollback(); }
				 */

			} else {
				System.out
						.println("Address not updated.You Entered Wrong Existing Address");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insurancePolicy(int userId) {

		try {
			ps = con.prepareStatement(Queries.SELCTINSURANCES);

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			System.out.println("Enter your choice");

			int insuranceId = sc.nextInt();

			if (insuranceId == 1) {
				premiumCalculationForVehicle(insuranceId, userId);
			} else if (insuranceId == 2) {
				premiumCalculationForHome(insuranceId, userId);
			} else {
				System.out
						.println("There Is No Such Insurance.Select Between 1 and 2");
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter Only Digits");
		}
	}

	public void premiumCalculationForVehicle(int insuranceId, int userId) {
		double load = 0;
		double discount = 0;
		double loadOnPremium = 0;
		double discountOnPremium = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the vehicle type");
		try {
			ps = con.prepareStatement(Queries.INSURANCE);
			ps.setInt(1, insuranceId);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t " + rs.getString(2));

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
 
		int vehicle = sc.nextInt();
		System.out.println("Enter the price of vehicle");
		double price = sc.nextDouble();
		System.out.println("Enter term factor");
		try {
			System.out.println("1. 1 year\n2. 3 years");
			int termFactor = sc.nextInt();

			double basePremiumOfVehicle = price * (0.2);
			double totalPremium = basePremiumOfVehicle + loadOnPremium
					- discountOnPremium;
			if (vehicle == 1) {
				if (termFactor == 1) {
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					System.out.println("BasePremiumOfVehicle= "
							+ basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					totalPremium = basePremiumOfVehicle
							+ (price * (0.2) * (0.15)) + loadOnPremium
							- discountOnPremium;
					System.out.println("TotalPremium= " + totalPremium);
					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, vehicle);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);

						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Failed To Add Insurance");
						}

					} catch (SQLException e) {

						e.printStackTrace();
					}

				} else if (termFactor == 2) {
					load = 0.4;
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					System.out.println("BasePremiumOfVehicle= "
							+ basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					totalPremium = basePremiumOfVehicle
							+ (price * (0.2) * (0.15) + loadOnPremium - discountOnPremium);
					System.out.println("TotalPremium= " + totalPremium);

					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, vehicle);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);

						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Fail To Add Insurance");
						}

					} catch (SQLException e) {

						e.printStackTrace();
					}

				} else {
					System.out.println("Wrong choice.Please enter 1 or 2");
				}

			}

			else if (vehicle == 2) {
				if (termFactor == 1) {
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					basePremiumOfVehicle = price * (0.2);
					System.out.println("BasePremiumOfVehicle= "
							+ basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					System.out.println("TotalPremium= " + totalPremium);

					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, vehicle);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);
						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Failed To Add Insurance");
						}

					} catch (SQLException e) {

						e.printStackTrace();
					}
				} else if (termFactor == 2) {
					load = 0.4;
					basePremiumOfVehicle = price * (0.2);
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					System.out.println("BasePremiumOfVehicle= "
							+ basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);

					System.out.println("LoadOnPremium= " + loadOnPremium);
					System.out.println("TotalPremium= " + totalPremium);
					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, vehicle);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);
						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Failed To Add Insurance");
						}
					} catch (SQLException e) {

						e.printStackTrace();
					}

				} else {
					System.out.println("Wrong choice.Please enter 1 or 2");
				}

			} else {
				System.out
						.println("There is no such vehicle.please enter 1 or 2");
			}

		} catch (InputMismatchException e) {
			System.out.println("Enter only digits");
			premiumCalculationForVehicle(insuranceId, userId);
		}
	}

	public void premiumCalculationForHome(int insuranceId, int userId) {
		double load = 0;
		double discount = 0;
		double loadOnPremium = 0;
		double discountOnPremium = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the home type");
		try {
			ps = con.prepareStatement(Queries.INSURANCE);
			ps.setInt(1, insuranceId);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t " + rs.getString(2));

			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		int home = sc.nextInt();
		System.out.println("Enter the cost of house");
		double price = sc.nextDouble();
		System.out.println("Enter term factor");
		try {
			System.out.println("1. 1 year\n2. 3 years");
			int termFactor = sc.nextInt();

			double basePremiumOfHouse = price * (0.2);
			double totalPremium = basePremiumOfHouse + loadOnPremium
					- discountOnPremium;
			if (home == 3) {
				if (termFactor == 1) {
					discountOnPremium = basePremiumOfHouse * (discount / 100);
					loadOnPremium = basePremiumOfHouse * (load / 100);
					System.out.println("BasePremiumOfHouse= "
							+ basePremiumOfHouse);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					totalPremium = basePremiumOfHouse
							+ (price * (0.2) * (0.15)) + loadOnPremium
							- discountOnPremium;
					System.out.println("TotalPremium= " + totalPremium);
					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, home);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);
						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Fail To Add Insurance");
						}

					} catch (SQLException e) {

						e.printStackTrace();
					}

				}

				else if (termFactor == 2) {
					load = 0.4;
					discountOnPremium = basePremiumOfHouse * (discount / 100);
					loadOnPremium = basePremiumOfHouse * (load / 100);
					System.out.println("premiumCalculationForHome()= "
							+ basePremiumOfHouse);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					totalPremium = basePremiumOfHouse
							+ (price * (0.2) * (0.15) + loadOnPremium - discountOnPremium);
					System.out.println("TotalPremium= " + totalPremium);
					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, home);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);
						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Failed To Add Insurance");
						}
					} catch (SQLException e) {

						e.printStackTrace();
					}

				} else {
					System.out.println("Wrong choice.Please enter 1 or 2");
				}

			}

			else if (home == 4) {
				if (termFactor == 1) {
					discountOnPremium = basePremiumOfHouse * (discount / 100);
					loadOnPremium = basePremiumOfHouse * (load / 100);
					basePremiumOfHouse = price * (0.2);
					System.out.println("premiumCalculationForHome()= "
							+ basePremiumOfHouse);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					System.out.println("TotalPremium= " + totalPremium);
					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, home);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);
						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Failed To Add Insurance");
						}
					} catch (SQLException e) {

						e.printStackTrace();
					}

				} else if (termFactor == 2) {
					load = 0.4;
					basePremiumOfHouse = price * (0.2);
					discountOnPremium = basePremiumOfHouse * (discount / 100);
					loadOnPremium = basePremiumOfHouse * (load / 100);
					System.out.println("premiumCalculationForHome()= "
							+ basePremiumOfHouse);
					System.out.println("DiscountOnPremium= "
							+ discountOnPremium);

					System.out.println("LoadOnPremium= " + loadOnPremium);
					System.out.println("TotalPremium= " + totalPremium);
					try {
						ps = con.prepareStatement(Queries.TOTALCOST);
						UserBean b = new UserBean();
						b.setCost(totalPremium);
						ps.setInt(1, insuranceId);
						ps.setInt(2, home);
						ps.setInt(3, termFactor);

						ps.setDouble(4, b.getCost());
						ps.setInt(5, userId);
						int i = ps.executeUpdate();

						if (i == 1) {
							System.out.println("Insurance Added");
						} else {
							System.out.println("Failed To Add Insurance");
						}
					} catch (SQLException e) {

						e.printStackTrace();
					}

				} else {
					System.out.println("Wrong choice.Please enter 1 or 2");
				}

			} else {
				System.out.println("There is no such Home.please enter 3 or 4");
			}

		} catch (InputMismatchException e) {
			System.out.println("Enter only digits");
			premiumCalculationForHome(insuranceId, userId);
		}

	}

	@Override
	public void viewActiveInsurances() {
		try {

			ps = con.prepareStatement(Queries.ACTIVEINSURANCES);
			rs = ps.executeQuery();
			System.out.println("All_insurance_id" + "\tyears" + "\tCost"
					+ "\tDate" + "\tStatus" + "\tinsurance_type"
					+ "\tType_name");
			System.out
					.println("----------------------------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + "\t" + rs.getInt(2) + "\t"
						+ rs.getDouble(3) + "\t" + rs.getDate(4) + "\t"
						+ rs.getString(5) + "\t" + rs.getString(6) + "\t"
						+ rs.getString(7));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void logout() {
		login();

	}
}
