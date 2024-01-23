package banking2;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountManager {
	int numOfAccount;
	Account[] myAccount;
	
	public AccountManager(int num) {
		myAccount = new Account[num];
		numOfAccount = 0;
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String makeAccount, makeName, grade;
		int makeBalance, choice, interest;
		
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:"); choice = scan.nextInt();
		scan.nextLine();
		
		if(choice == 1) {
			System.out.print("계좌번호:"); makeAccount = scan.nextLine();
			System.out.print("고객이름:"); makeName = scan.nextLine();
			System.out.print("잔고:"); makeBalance = scan.nextInt();
			scan.nextLine();
			System.out.print("기본이자%(정수형태로입력):"); interest = scan.nextInt();
			myAccount[numOfAccount++] = new NormalAccount(makeAccount, makeName, makeBalance, interest);
		}
		else if(choice == 2) {
			System.out.print("계좌번호:"); makeAccount = scan.nextLine();
			System.out.print("고객이름:"); makeName = scan.nextLine();
			System.out.print("잔고:"); makeBalance = scan.nextInt();
			System.out.print("기본이자%(정수형태로입력):"); interest = scan.nextInt();
			scan.nextLine();
			System.out.print("신용등급(A,B,C등급):"); grade = scan.nextLine();
			myAccount[numOfAccount++] = new HighCreditAccount(makeAccount, makeName, makeBalance, grade, interest);
			
		}
		else {
			System.out.println("잘못누름");
		}
		
		System.out.println("계좌계설이 완료되었습니다.");
		System.out.println();
	}
	
	// 입    금
	public void depositMoney() {
		Scanner scan = new Scanner(System.in);
		String depositAccount;
		int deposit;
		NormalAccount nomal = new NormalAccount();
		
		System.out.println("***입 금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요.");
		System.out.print("계좌번호:"); depositAccount = scan.nextLine();
		System.out.print("입금액:"); deposit = scan.nextInt();
		for(int i = 0; i < numOfAccount; i++) {
			if(depositAccount.compareTo(myAccount[i].account) == 0) {
				myAccount[i].interestCal(deposit);
			}
		}
		System.out.println("입금이 완료되었습니다.");
		System.out.println();
	}
	
	// 출    금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		String withdrawAccount;
		int withdraw;
		
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호:"); withdrawAccount = scan.nextLine();
		System.out.print("출금액:"); withdraw = scan.nextInt();
		for(int i = 0; i < numOfAccount; i++) {
			if(withdrawAccount.compareTo(myAccount[i].account) == 0) {
				myAccount[i].balance -= withdraw;
			}
		}
		
		System.out.println("출금이 완료되었습니다.");
		System.out.println();
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(int i = 0; i < numOfAccount; i++) {
			myAccount[i].showAllAccount();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
}
