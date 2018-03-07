
 package com.dao;

public interface Queries {
	String LOGIN = "select u.email,u.password,r.role_name,p.previlages_id,p.previlages_name,u.user_id from  users u,roles r,"
			+ "previlages p,user_previlages up where u.role_id=r.role_id and up.previlages_id=p.previlages_id and "
			+ "up.role_id=r.role_id and u.email=? and u.password=?";

	String USERDETAILS = "SELECT * FROM users where role_id!=1";

	String VIEWPARTICULARDETAILS = "select * from users where user_id=?";

	String INSERT = "insert into insurance.users(first_name,email,password,role_id,mobile_number,address) values(?,?,?,?,?,?)";

	String PASSWORD = "update insurance.users set password=? where password=?";

	String MOBILENUMBER = "update insurance.users set mobile_number=? where mobile_number=?";

	String ADDRESS = "update insurance.users set address=? where address=?";

	String VIEWALL = "select * from users";

	String SELCTINSURANCES = "SELECT * FROM `insurance`.`abz_insurance` ";

	String INSURANCE = "select type_id,type_name from insurance_type where insurance_id=?";

	String TOTALCOST = "insert into insurance.all_insurance(insurance_id,type_id,years,cost,date,status,user_id)"
			+ " values(?,?,?,?,curdate(),'active',?)";

	String VIEWPARTICULARINSURANCE = "SELECT u.user_id,u.first_name,i.type_name,ab.insurance_type,"
			+ "a.years,a.cost,a.date,u.mobile_number,u.address,a.status FROM users u,insurance_type i,"
			+ "all_insurance a,abz_insurance ab where u.user_id=a.user_id and "
			+ "ab.insurance_id=a.insurance_id and i.type_id=a.type_id and u.user_id=?";

	String VIEWALLINSURANCE = "SELECT u.first_name,i.type_name,ab.insurance_type,"
			+ "a.years,a.cost,a.date,u.mobile_number,u.address,a.status FROM users u,insurance_type i,"
			+ "all_insurance a,abz_insurance ab where u.user_id=a.user_id and "
			+ "ab.insurance_id=a.insurance_id and i.type_id=a.type_id";

	String ACTIVEPOLICIES = "SELECT distinct al.user_id,al.all_insurance_id,al.years,al.cost,al.date,al.status,a.insurance_type,"
			+ "i.type_name FROM all_insurance al,abz_insurance a,insurance_type i where a.insurance_id=al.insurance_id"
			+ " and al.type_id=i.type_id and al.status='active'";

	String YEARS = "select * from all_insurance where all_insurance_id=?";

	String DELETE = "delete from users where user_id=? and role_id!=1";

	String ACTIVEINSURANCES = "SELECT distinct al.all_insurance_id,al.years,al.cost,al.date,al.status,"
			+ "a.insurance_type,i.type_name FROM all_insurance al,abz_insurance a,insurance_type i "
			+ "where a.insurance_id=al.insurance_id and al.type_id=i.type_id and status='active'";
}
