package banking3;

import java.util.InputMismatchException;
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
		
		myAccount = new Account[50];
		numOfAccount = 0;
		AccountManager manager = new AccountManager(50);
		
		// 프로그램 실행 함수
		while(true) {
			
			ICustomDefine.menuShow();
			int choice = choiceMenu();
			
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
				//manager.saveAccountInfo();
				System.out.println("프로그램 종료");
				return;
			}
		}
	}
	
	public static int choiceMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		try {
			choice = scan.nextInt();
			try {
				if(choice <= 0 || choice >= 6) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
			}
			catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
		}
		catch(InputMismatchException e) {
			System.out.println("문자를 입력할 수 없습니다.");
		}
		
		return choice;
	}

}


