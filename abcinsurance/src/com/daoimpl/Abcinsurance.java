package com.daoimpl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import com.beans.Customer1;
import com.dao.Insurance;

public class Abcinsurance implements Insurance {

	@Override
	public void login() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("/home/trainee/Desktop/filesproject/abcinsurance/src/admindetails.properties");
			Properties p = new Properties();
			p.load(fis);

			if (username.equals(p.getProperty("username")) && password.equals(p.getProperty("password"))) {

				int ch1 = 1;
				while (ch1 == 1) {
					try {
						System.out.println("\n1.Registration \n 2. View Customer \n 3.Search Customer \n 4.Logout");
					} catch (InputMismatchException e) {
						System.out.println("enter only numbers");
						login();
					}
					System.out.println("Enter your choice");
					int ch = sc.nextInt();
					if (ch == 1) {
						registration();
					} else if (ch == 2) {
						getCustomerDetais();
					} else if (ch == 3) {
						getParticularCustomer();
					} else if (ch == 4) {
						login();
					} else {
						System.out.println("Wrong choice");

					}

					System.out.println("Do you want to continue press 1 else any number");
					ch1 = sc.nextInt();
				}
			}

			else {
				int j = 0;
				FileInputStream fis1 = null;
				ObjectInputStream ois1 = null;

				try {
					fis1 = new FileInputStream("/home/trainee/Desktop/filesproject/abcinsurance/customerDetails.ser");
					ois1 = new ObjectInputStream(fis1);

					ArrayList<Customer1> al1 = (ArrayList<Customer1>) ois1.readObject();

					Iterator<Customer1> i = al1.iterator();

					System.out.println("-----------------------------------------");
					while (i.hasNext()) {

						Customer1 b = i.next();
						System.out.println(b);
						if (b.getEmail().equals(username) && b.getPassword().equals(password)) {

							System.out.println("Welcome To Customer");
							System.out.println("1.Home Insurance \n2.Vehicle Insurance\n3.ChangeCustomerDetails");
							System.out.println("Please select option");
							int ch = sc.nextInt();
							if (ch == 1) {
								homeInsurance();
							} else if (ch == 2) {
								premiumCalculationForVehicle();
							} else if (ch == 3) {
								customerService(username);
							} else {
								System.out.println("Wrong choice");
							}
							j = 0;
						} else {
							j++;
						}

					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis1.close();
						ois1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (j != 0) {
					System.err.println("Invalid username and password");
					login();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void getCustomerDetais() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {
			fis = new FileInputStream("customerDetails.ser");
			ois = new ObjectInputStream(fis);

			ArrayList<Customer1> al = (ArrayList<Customer1>) ois.readObject();

			Iterator<Customer1> i = al.iterator();

			System.out.println("customer Id\tcustomer Name\tAddress");
			System.out.println("-----------------------------------------");
			while (i.hasNext()) {

				Customer1 b = i.next();
				System.out.println(b.getCustomerId() + "\t" + b.getCustomerName() + "\t" + b.getAddress());

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void registration() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		File f = null;
		try {
			f = new File("customerDetails.ser");

			ArrayList<Customer1> al = new ArrayList<Customer1>();

			if (!f.exists()) {
				f.createNewFile();
				fos = new FileOutputStream(f);
				oos = new ObjectOutputStream(fos);
				Scanner sc = new Scanner(System.in);
				Customer1 b = new Customer1();
				System.out.println("Enter customer Name");
				b.setCustomerName(sc.next());
				System.out.println("Enter phone number");
				b.setPhoneNumber(sc.nextLong());
				System.out.println("Enter Address");
				b.setAddress(sc.next());
				System.out.println("Enter date of birth");
				b.setDateOfBirth((sc.next()));
				System.out.println("Enter gender");
				try {
					System.out.println("1.Male\n2.Female");
					int a = sc.nextInt();
					if (a == 1) {
						b.setGender("Male");
					} else if (a == 2) {
						b.setGender("Female");
					} else {
						System.out.println("wrong choice.you should enter only 1 or 2");
					}
				} catch (InputMismatchException e) {
					System.out.println("enter only digits");
					registration();
				}
				System.out.println("Enter email");
				b.setEmail(sc.next());

				String pwd = b.getCustomerName().substring(1, 4) + "" + generateRandom(4);
				b.setPassword(pwd);

				al.add(b);

				oos.writeObject(al);

				System.out.println("Registertion Success !");
				System.out.println("Email :" + b.getEmail());
				System.out.println("Password :" + b.getPassword());

			}

			else {

				ArrayList<Customer1> al2 = new ArrayList<Customer1>();
				File f1 = new File("customerDetails.ser");
				FileInputStream fis1 = null;
				ObjectInputStream ois1 = null;

				FileOutputStream fos1 = null;
				ObjectOutputStream oos1 = null;
				File temp = null;
				try {
					temp = new File("Temp.ser");
					fis1 = new FileInputStream(f1);
					ois1 = new ObjectInputStream(fis1);

					fos1 = new FileOutputStream(temp);
					oos1 = new ObjectOutputStream(fos1);

					ArrayList<Customer1> al1 = null;
					try {
						al1 = (ArrayList<Customer1>) ois1.readObject();
						System.out.println("Al1  =" + al1);
						al2.addAll(al1);

					} catch (EOFException e) {

					}
					Scanner sc = new Scanner(System.in);
					Customer1 b = new Customer1();
					System.out.println("Enter customer Name");
					b.setCustomerName(sc.next());
					System.out.println("Enter phone number");
					long l = sc.nextLong();
					if (l >= 1000000000 && l <= 9999999999l)
						b.setPhoneNumber(l);
					else
						System.out.println("phone number should contain 10 digits");
					System.out.println("Enter Address");
					b.setAddress(sc.next());

					System.out.println("Enter date of birth");
					b.setDateOfBirth((sc.next()));
					System.out.println("Enter gender");
					try {
						System.out.println("1.Male\n2.Female");
						int a = sc.nextInt();
						if (a == 1) {
							b.setGender("Male");
						} else if (a == 2) {
							b.setGender("Female");
						} else {
							System.out.println("wrong choice.you should enter only 1 or 2");
						}
					} catch (InputMismatchException e) {
						System.out.println("enter only digits");
						registration();
					}

					System.out.println("Enter email");
					b.setEmail(sc.next());

					String pwd = b.getCustomerName().subSequence(1, 4) + "" + generateRandom(4);
					b.setPassword(pwd);

					al2.add(b);

					System.out.println("AL222= " + al2);

					oos1.writeObject(al2);

					System.out.println("Registertion Success !");
					System.out.println("Email :" + b.getEmail());
					System.out.println("Password :" + b.getPassword());

					f1.delete();
					temp.renameTo(f1);

				}

				catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						fis1.close();
						ois1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void getParticularCustomer() {
		System.out.println("Enter customer name to search");
		Scanner sc = new Scanner(System.in);
		String bname = sc.next();

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		int j = 0;
		try {
			fis = new FileInputStream("customerDetails.ser");
			ois = new ObjectInputStream(fis);

			ArrayList<Customer1> al = (ArrayList<Customer1>) ois.readObject();

			Iterator<Customer1> i = al.iterator();

			System.out.println("Customer Name\t\t\t\tAddress");
			System.out.println("-----------------------------------------");
			while (i.hasNext()) {

				Customer1 b = i.next();
				if (b.getCustomerName().equalsIgnoreCase(bname)) {
					System.out.println("Customer Name :" + b.getCustomerName());
					System.out.println("Customer Address :" + b.getAddress());

					j = 0;
					break;

				} else {
					j++;
				}
			}
			if (j != 0) {
				System.out.println("Customer Details Not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void premiumCalculationForVehicle() {
		double load = 0;
		double discount = 0;
		double loadOnPremium = 0;
		double discountOnPremium = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the vehicle type");
		System.out.println("1.commercial\n2.personal");
		int vehicle = sc.nextInt();
		System.out.println("Enter the price of vehicle");
		double price = sc.nextDouble();
		System.out.println("Enter term factor");
		try {
			System.out.println("1. 1 year\n2. 3 years");
			int termFactor = sc.nextInt();

			double basePremiumOfVehicle = price * (0.2);
			double totalPremium = basePremiumOfVehicle + loadOnPremium - discountOnPremium;
			if (vehicle == 1) {
				if (termFactor == 1) {
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					System.out.println("BasePremiumOfVehicle= " + basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= " + discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					totalPremium = basePremiumOfVehicle + (price * (0.2) * (0.15)) + loadOnPremium - discountOnPremium;
					System.out.println("TotalPremium= " + totalPremium);

				} else if (termFactor == 2) {
					load = 0.4;
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					System.out.println("BasePremiumOfVehicle= " + basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= " + discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					totalPremium = basePremiumOfVehicle + (price * (0.2) * (0.15) + loadOnPremium - discountOnPremium);
					System.out.println("TotalPremium= " + totalPremium);

				} else {
					System.out.println("Wrong choice.Please enter 1 or 2");
				}

			}

			else if (vehicle == 2) {
				if (termFactor == 1) {
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					basePremiumOfVehicle = price * (0.2);
					System.out.println("BasePremiumOfVehicle= " + basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= " + discountOnPremium);
					System.out.println("LoadOnPremium= " + loadOnPremium);
					System.out.println("TotalPremium= " + totalPremium);
				} else if (termFactor == 2) {
					load = 0.4;
					basePremiumOfVehicle = price * (0.2);
					discountOnPremium = basePremiumOfVehicle * (discount / 100);
					loadOnPremium = basePremiumOfVehicle * (load / 100);
					System.out.println("BasePremiumOfVehicle= " + basePremiumOfVehicle);
					System.out.println("DiscountOnPremium= " + discountOnPremium);

					System.out.println("LoadOnPremium= " + loadOnPremium);
					System.out.println("TotalPremium= " + totalPremium);

				} else {
					System.out.println("Wrong choice.Please enter 1 or 2");
				}

			} else {
				System.out.println("There is no such vehicle.please enter 1 or 2");
			}

		} catch (InputMismatchException e) {
			System.out.println("Enter only digits");
			premiumCalculationForVehicle();
		}
	}

	public void homeInsurance() {
		double homePremium;
		boolean smokers;
		Scanner sc = new Scanner(System.in);
		System.out.println("The Cost of house");
		int cost = sc.nextInt();
		System.out.println("The Insuarance Amount You need to pay");
		double insuranceCoverage;// =sc.nextInt();
		insuranceCoverage = cost * 0.7;
		System.out.println(insuranceCoverage);
		double basicPremium = insuranceCoverage * 0.5;// =sc.nextInt();
		System.out.println("Enter how old your home is");
		int noOfYears = sc.nextInt();
		if (noOfYears > 5) {
			System.out.println("Enter the House Type");
			System.out.println("\n1.Brick House\n2Frame House");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:
				System.out.println("Is there any smoker in home");
				System.out.println("\n1.Yes \n2 No");
				int i = sc.nextInt();
				switch (i) {
				case 1:
					homePremium = basicPremium + (basicPremium * 0.15) + (basicPremium * 0.05) - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months = insuranceCoverage / homePremium;
					System.out.println("Mothns required to cover premium" + months);

					break;

				case 2:
					homePremium = basicPremium + (basicPremium * 0.15) - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months1 = insuranceCoverage / homePremium;
					System.out.println("Months required to cover premium" + months1);
					break;

				}
				break;
			case 2:
				System.out.println("Is there any smoker in home");
				System.out.println("\n1.Yes \n2 No");
				int b = sc.nextInt();
				switch (b) {
				case 1:
					homePremium = basicPremium + (basicPremium * 0.15) + (basicPremium * 0.05) - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months = insuranceCoverage / homePremium;
					System.out.println("Months required to cover premium" + months);

					break;

				case 2:
					homePremium = basicPremium + (basicPremium * 0.15) - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months1 = insuranceCoverage / homePremium;
					System.out.println("Months required to cover premium" + months1);
					break;

				}
				break;
			}
		}

		else {
			System.out.println("Enter the House condition");
			System.out.println("\n1.Brick House\n2Frame House");
			int ch = sc.nextInt();
			switch (ch) {
			case 1:

				System.out.println("Is there any smoker in home");
				System.out.println("\n1.Yes \n2 No");
				int j = sc.nextInt();
				switch (j) {
				case 1:
					homePremium = basicPremium + (basicPremium * 0.05) - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months = insuranceCoverage / homePremium;
					System.out.println("Mothns required to cover premium" + months);
					break;

				case 2:
					homePremium = basicPremium - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months1 = insuranceCoverage / homePremium;
					System.out.println("Mothns required to cover premium" + months1);

					break;
				}
				break;

			case 2:
				System.out.println("Is there any smoker in home");
				System.out.println("\n1.Yes \n2 No");
				int k = sc.nextInt();
				switch (k) {
				case 1:
					homePremium = basicPremium + (basicPremium * 0.05) - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months = insuranceCoverage / homePremium;
					System.out.println("Months required to cover premium" + months);
					break;

				case 2:
					homePremium = basicPremium - (basicPremium - 0.3);
					System.out.println("Monthly Premium=" + homePremium);
					double months1 = insuranceCoverage / homePremium;
					System.out.println("Months required to cover premium" + months1);

					break;

				}
				break;

			}

		}

	}

	@Override
	public void customerService(String email) {
		int ch1 = 1;
		System.out.println("Do you want to change details, if yes press 1 else any number");
		int ch = 1, ch2 = 0;
		Scanner sc = new Scanner(System.in);
		while (ch == 1) {
			System.out.println("select your choice");
			System.out.println("1.Change Address \n 2.Change mobile no\n 3.Change password ");
			File f1 = new File("/home/trainee/Desktop/filesproject/abcinsurance/customerDetails.ser");
			FileInputStream fis1 = null;
			ObjectInputStream ois1 = null;

			FileOutputStream fos1 = null;
			ObjectOutputStream oos1 = null;
			File temp = null;
			Customer1 c1 = null;
			ArrayList<Customer1> al = null;
			try {
				temp = new File("Temp.ser");
				fis1 = new FileInputStream(f1);
				ois1 = new ObjectInputStream(fis1);

				fos1 = new FileOutputStream(temp);
				oos1 = new ObjectOutputStream(fos1);

				ch2 = sc.nextInt();
				ArrayList<Customer1> al1 = null;

				switch (ch2) {
				case 1:
					System.out.println("Enter new address");
					String newAddress = sc.next();

					try {
						al1 = (ArrayList<Customer1>) ois1.readObject();

						Iterator<Customer1> i = al1.iterator();
						while (i.hasNext()) {
							c1 = i.next();
							if (c1.getEmail().equals(email)) {
								c1.setAddress(newAddress);
								al1.add(c1);
							}

							al1.add(c1);
						}
					} catch (Exception e) {

					}
					oos1.writeObject(al1);

					f1.delete();
					temp.renameTo(f1);

					break;
				case 2:
					System.out.println("Enter new mobilenumber");
					long newMobile = sc.nextLong();
					try {
						try {
							al1 = (ArrayList<Customer1>) ois1.readObject();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						Iterator<Customer1> i = al1.iterator();
						while (i.hasNext()) {
							c1 = i.next();
							if (c1.getEmail().equals(email)) {
								c1.setPhoneNumber(newMobile);

								al1.add(c1);
							}
							al1.add(c1);
						}
					} catch (EOFException e) {

					}
					oos1.writeObject(al1);

					f1.delete();
					temp.renameTo(f1);
					break;
				case 3:
					System.out.println("Enter the existing password");

					String opwd = sc.next();
					File f = new File("/home/trainee/Desktop/filesproject/abcinsurance/customerDetails.ser");
					FileInputStream fis = null;
					ObjectInputStream ois = null;

					File temp1 = null;
					FileOutputStream fos = null;
					ObjectOutputStream oos = null;

					try {
						fis = new FileInputStream(f);
						ois = new ObjectInputStream(fis);

						temp = new File("temp.ser");
						fos = new FileOutputStream(temp1);
						oos = new ObjectOutputStream(fos);

						al1 = (ArrayList<Customer1>) ois.readObject();

						Iterator<Customer1> i = al1.iterator();

						while (i.hasNext()) {
							c1 = i.next();

							if (c1.getEmail().equals(email)) {

								String filepassword = c1.getPassword();

								if (filepassword.equals(opwd)) {

									System.out.println("Enter new password");
									String newpassword = sc.next();
									System.out.println("Retype password");
									String retypePassword = sc.next();

									if (newpassword.equals(retypePassword)) {
										c1.setPassword(retypePassword);
									}
								}

								al1.add(c1);

							}

							al1.add(c1);
							break;

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						oos.writeObject(al1);
						f.delete();
						temp.renameTo(f);

					} catch (IOException e) {

						e.printStackTrace();
					}
				default:
					System.out.println("Invalid choice");
				}
			} catch (IOException e) {

				e.printStackTrace();
			}

			finally {
				try {
					fis1.close();
					ois1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static long generateRandom(int length) {
		Random random = new Random();
		char[] digits = new char[length];
		digits[0] = (char) (random.nextInt(9) + '1');
		for (int i = 1; i < length; i++) {
			digits[i] = (char) (random.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}
}
