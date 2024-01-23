package banking2;

import java.util.Scanner;

public class BankingSystemMain {
	
	static Account[] myAccount;
	static int numOfAccount;
	
	public static void main(String[] args) {
		final int MAKE = 1;
		final int DEPOSIT = 2;
		final int WITHDRAW = 3;
		final int INQUIRE = 4;
		final int EXIT = 5;
		
		Scanner scan = new Scanner(System.in);
		myAccount = new Account[50];
		numOfAccount = 0;
		AccountManager manager = new AccountManager(50);
		
		// 프로그램 실행 함수
		while(true) {
			ICustomDefine.menuShow();
			int choice = scan.nextInt();
			
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
			case EXIT:
				//handler.saveFriendInfo();
				System.out.println("프로그램 종료");
				return;
			}
		}
	}
}

