package banking2;

public class NormalAccount extends Account {
	
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
		System.out.println("-------------");
		super.showAllAccount();
		System.out.println("기본이자>" + interest + "%");
		System.out.println("-------------");
	}
}
