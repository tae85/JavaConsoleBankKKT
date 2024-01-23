package banking4;

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
		final int DELETE = 5;
		final int EXIT = 6;
		
		myAccount = new Account[50];
		numOfAccount = 0;
		AccountManager manager = new AccountManager();
		
		// 프로그램 실행 함수
		while(true) {
			
			menuShow();
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
	
	// 메뉴출력
	public static void menuShow() {
		System.out.println("----- Menu -----");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입 금  ");
		System.out.println("3.출 금 ");
		System.out.print("4.계좌정보출력  ");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.프로그램종료 ");
		System.out.print("선택:");
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
				System.out.println("메뉴 입력 예외 발생됨.");
				System.out.println(e.getMessage());
			}
		}
		catch(InputMismatchException e) {
			System.out.println("문자를 입력할 수 없습니다.");
		}
		
		return choice;
	}

}


