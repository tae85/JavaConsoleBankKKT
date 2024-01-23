package banking3;

import java.util.Scanner;

public abstract class Account {
	public String account;
	public String name;
	public int balance;
	public int interest, addInterest;
	
	public Account() {}
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}

	public void interestCal(int diposit) {
		balance = balance + (balance * interest/100) + diposit;
	}
	
	public void interestCal(int diposit, String grade) {
		if(grade == "A") {
			addInterest = 2;
		}
		else if (grade == "B") {
			addInterest = 4;
		}
		else if (grade == "C") {
			addInterest = 7;
		}
		balance = balance + (balance * interest/100) + 
				(balance * addInterest/100) + diposit;
	}
	
	public void showAllAccount() {
		System.out.println("계좌번호>" + account);
		System.out.println("이름>" + name);
		System.out.println("잔액>" + balance);
	}
}






















