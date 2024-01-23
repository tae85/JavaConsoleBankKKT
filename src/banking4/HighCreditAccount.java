package banking4;

import java.util.Objects;
import java.util.Scanner;

public class HighCreditAccount extends Account {
	
	String grade;
	
	public HighCreditAccount(String account, String name, int balance, 
			String grade, int interest) {
		super(account, name, balance);
		this.grade = grade;
		this.interest = interest;
	}
	
	@Override
	public void interestCal(int diposit) {
		switch(grade) {
		case "A" :
			addInterest = 2;
			break;
		case "B" :
			addInterest = 4;
			break;
		case "C" :
			addInterest = 7;
			break;
		default :
			addInterest = 0;
			break;
		}
		balance = balance + (balance * interest/100) + 
				(balance * addInterest/100) + diposit;
	}
	
	@Override
	public void showAllAccount() {
		System.out.println("-------------");
		super.showAllAccount();
		System.out.println("기본이자>" + interest + "%");
		System.out.println("신용등급>" + grade);
		System.out.println("-------------");
	}

	@Override
	public int hashCode() {
		int returnCode = Objects.hash(super.account);
		return returnCode;
	}

	@Override
	public boolean equals(Object obj) {
		Account other = (Account) obj;
		if (other.account.equals(super.account)) {
			return true;
		}
		else {
			return false;
		}
	}
}
