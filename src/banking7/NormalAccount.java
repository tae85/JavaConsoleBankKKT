package banking7;

import java.util.Objects;

public class NormalAccount extends Account {
	
	public NormalAccount() {
		
	}
	
	public NormalAccount(String account, String name, int balance, int interest) {
		super(account, name, balance);
		this.interest = interest;
	}
	
	@Override
	public void interestCal(int diposit) {
		balance = balance + (balance * interest/100) + diposit;
	}
	
	@Override
	public void showAllAccount() {
		super.showAllAccount();
		System.out.println("기본이자>" + interest + "%");
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
