package banking7;

public class SpecialAccount extends NormalAccount {
	
	int evenCnt = 0;
	
	public SpecialAccount(String account, String name, int balance, int interest) {
		super(account, name, balance, interest);
	}
	
	@Override
	public void interestCal(int diposit) {
		evenCnt++;
		if(evenCnt % 2 == 0) {
			balance = balance + (balance * interest/100) + diposit + 500;
		}
		else {
			balance = balance + (balance * interest/100) + diposit;
		}
	}
	
	@Override
	public void showAllAccount() {
		super.showAllAccount();
		System.out.println("현재 " + evenCnt + "회차 입니다.");
	}

}
