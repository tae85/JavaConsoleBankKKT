package banking4;

public class BankingSystemMain {
	
	static Account[] myAccount;
	static int numOfAccount;
	
	public static void main(String[] args) {
		final int MAKE = 1;
		final int DEPOSIT = 2;
		final int WITHDRAW = 3;
		final int INQUIRE = 4;
		final int DELETE = 5;
		final int EXIT = 6;
		
		myAccount = new Account[50];
		numOfAccount = 0;
		AccountManager manager = new AccountManager();
		
		// 프로그램 실행 함수
		while(true) {
			
			ICustomDefine.menuShow();
			int choice = manager.choiceMenu();
			
			switch (choice) {
			case MAKE:
				manager.makeAccount();
				break;
			case DEPOSIT:
				manager.depositMoney();
				break;
			case WITHDRAW:
				manager.withdrawMoney();
				break;
			case INQUIRE:
				manager.showAccInfo();
				break;
			case DELETE:
				manager.deleteAccount();
				break;
			case EXIT:
				//manager.saveAccountInfo();
				System.out.println("프로그램 종료");
				return;
			}
		}
	}
	
	

}


