package banking7;

import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable {
	public String account;
	public String name;
	public int balance;
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}

	public void interestCal(int diposit) {}
	
	public void interestCal(int diposit, String grade) {}
	
	public void showAllAccount() {
		System.out.println("계좌번호>" + account);
		System.out.print("이름>" + name);
		System.out.println("  잔액>" + balance);
	}

	@Override
	public int hashCode() {
		int returnCode = Objects.hash(this.account);
		return returnCode;
	}

	@Override
	public boolean equals(Object obj) {
		Account other = (Account) obj;
		if (other.account.equals(this.account)) {
			return true;
		}
		else {
			return false;
		}
	}

}






















