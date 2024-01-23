package banking6;

public class BankingSystemMain {
	
	public static void main(String[] args) {
		final int MAKE = 1;
		final int DEPOSIT = 2;
		final int WITHDRAW = 3;
		final int INQUIRE = 4;
		final int DELETE = 5;
		final int SAVEOPTION = 6;
		final int EXIT = 7;
		
		AccountManager manager = new AccountManager();
		manager.readAccountInfo();
		
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
			case SAVEOPTION:
				manager.saveOption();
				break;
			case EXIT:
				manager.saveAccountInfo();
				System.out.println("프로그램 종료");
				return;
			}
		}
	}
	
	

}


