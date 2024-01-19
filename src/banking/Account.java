package banking;

import java.util.Scanner;

public class Account {
	static String account;
	static String name;
	static int balance;
	static Account[] myAccount;
	static int numOfAccount;
	
//	public Account() {
//	}
	
	public Account(String account, String name, int balance) {
		this.account = account;
		this.name = name;
		this.balance = balance;
	}

	public static void menuShow() {
		System.out.println("----- Menu -----");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입 금  ");
		System.out.println("3.출 금 ");
		System.out.print("4.계좌정보출력  ");
		System.out.println("5.프로그램종료 ");
		System.out.print("선택:");
	}
	
	public static void main(String[] args) {
		final int MAKE = 1;
		final int DEPOSIT = 2;
		final int WITHDRAW = 3;
		final int INQUIRE = 4;
		final int EXIT = 5;
		
		Scanner scan = new Scanner(System.in);
		myAccount = new Account[50];
		numOfAccount = 0;
		
		// 프로그램 실행 함수
		while(true) {
			menuShow();
			int choice = scan.nextInt();
			
			switch (choice) {
			case MAKE:
				makeAccount();
				break;
			case DEPOSIT:
				depositMoney();
				break;
			case WITHDRAW:
				withdrawMoney();
				break;
			case INQUIRE:
				showAccInfo();
				break;
			case EXIT:
				//handler.saveFriendInfo();
				System.out.println("프로그램 종료");
				return;
			}

		}

	}
	
	// 계좌개설을 위한 함수
	public static void makeAccount() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호:"); account = scan.nextLine();
		System.out.print("고객이름:"); name = scan.nextLine();
		System.out.print("잔고:"); balance = scan.nextInt();
		
//		Account addAcount = new Account(account, name, balance);
		myAccount[numOfAccount++] = new Account(account, name, balance);
		
		System.out.println("계좌계설이 완료되었습니다.");
		System.out.println();
	}
	
	// 입    금
	public static void depositMoney() {
		Scanner scan = new Scanner(System.in);
		int deposit;
		
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호:"); account = scan.nextLine();
		System.out.print("입금액:"); deposit = scan.nextInt();
		for(int i = 0; i < numOfAccount; i++) {
			if(account.compareTo(myAccount[i].account) == 0) {
				myAccount[i].balance += deposit;
			}
		}
		System.out.println("입금이 완료되었습니다.");
		System.out.println();
	}
	
	// 출    금
	public static void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		int withdraw;
		
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호:"); account = scan.nextLine();
		System.out.print("출금액:"); withdraw = scan.nextInt();
		for(int i = 0; i < numOfAccount; i++) {
			if(account.compareTo(myAccount[i].account) == 0) {
				myAccount[i].balance -= withdraw;
			}
		}
		
		System.out.println("출금이 완료되었습니다.");
		System.out.println();
	}
	
	// 전체계좌정보출력
	public static void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i = 0; i < numOfAccount; i++) {
			System.out.println("-------------");
			System.out.println("계좌번호:" + myAccount[i].account);
			System.out.println("이름:" + myAccount[i].name);
			System.out.println("잔액:" + myAccount[i].balance);
			System.out.println("-------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}


}






















