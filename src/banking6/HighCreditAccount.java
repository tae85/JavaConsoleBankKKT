package banking6;

import java.util.Objects;

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
		ICustomDefine.creditGrade(grade);
		balance = balance + (balance * interest/100) + 
				(balance * addInterest/100) + diposit;
	}
	
	@Override
	public void showAllAccount() {
		System.out.println("--------------------");
		super.showAllAccount();
		System.out.print("기본이자>" + interest + "%");
		System.out.println("  신용등급>" + grade);
		System.out.println("--------------------");
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
