package banking7;

import java.util.Objects;

public class SpecialAccount extends NormalAccount {
	
	int depositCnt = 0;
	
	public SpecialAccount(String account, String name, int balance, int interest) {
		super(account, name, balance, interest);
	}
	
	@Override
	public void interestCal(int diposit) {
		depositCnt++;
		if(depositCnt % 2 == 0) {
			balance = balance + (balance * interest/100) + diposit + 500;
		}
		else {
			balance = balance + (balance * interest/100) + diposit;
		}
	}
	
	@Override
	public void showAllAccount() {
		super.showAllAccount();
		System.out.println("현재 " + depositCnt + "회차 입니다.");
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
