package banking;

import java.util.Random;

import java.util.Scanner;

public class Paytm {
	public static void main(String[]args){
		Paytm p=new Paytm();
		p.recharge();//method calling
	}
void recharge(){//method definition
	Scanner sc=new Scanner(System.in);
	System.out.println("enter mobile number");
	long mobileNo=sc.nextLong();
	System.out.println("enter recharge amount");
	double rechargeAmount=sc.nextDouble();
	Random vin = new Random();
	int reference=vin.nextInt(999999);
	System.out.println("if u want to recharge press 1 else any number");
	int ch=sc.nextInt();
	if(ch==1)
	{
		System.out.println("1.HDFC\n2.ICICI");
	int ch1=sc.nextInt();
	if(ch1==1)
	{
		int status=hdfcPayment(rechargeAmount);
		if(status==1){
			System.out.println("transaction success "+reference);		
		}
		else{
			System.out.println("transaction fail");
	}
}
		else if(ch==2){
			int status=iciciPayment(rechargeAmount);
			if(status==1){
				System.out.println("transaction success "+reference);		
			}
			else{
				System.out.println("transaction fail");
		}	
}
else{
	System.out.println("wrong choice");
}
}
else{
System.exit(0);
}
}
int hdfcPayment(double amount){
	System.out.println("********hdfc payment********");
	int status=0;
	double balance=1000;
	if(balance > amount){
	status=1;
	System.out.println("current Balance= "+(balance-amount));
	}
	else
	{
		status=0;
	}
	return status;
}
	int iciciPayment(double amount){
		System.out.println("********icici payment********");
		int status=0;
		double balance=1000;
		if(balance > amount){
		status=1;
		System.out.println("current Balance= "+(balance-amount));
		}
		else
		{
			status=0;
		}
		return status;
} 
}

