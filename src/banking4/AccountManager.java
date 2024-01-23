package banking4;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	HashSet<Account> set;
	
	public AccountManager() {
		set = new HashSet<Account>();
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
		
		System.out.print("계좌번호:"); makeAccount = scan.nextLine();
		System.out.print("고객이름:"); makeName = scan.nextLine();
		System.out.print("잔고:"); makeBalance = scan.nextInt();
		scan.nextLine();
		System.out.print("기본이자%(정수형태로입력):"); interest = scan.nextInt();
		
		if(choice == 1) {
			NormalAccount normal = new NormalAccount(makeAccount, makeName, makeBalance, interest);
			if(set.contains(normal)) {
				scan.nextLine();
				System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String duplication = scan.nextLine();
				if(duplication.equals("y")) {
					deleteAccount(makeAccount);
					set.add(normal);
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else {
					set.add(normal);
				}
			}
			else {
				set.add(normal);
				System.out.println("계좌계설이 완료되었습니다.");
			}
		}
		else if(choice == 2) {
			scan.nextLine();
			System.out.print("신용등급(A,B,C등급):"); grade = scan.nextLine();
			HighCreditAccount high = new HighCreditAccount(makeAccount, makeName, makeBalance, grade, interest);
			if(set.contains(high)) {
				System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String duplication = scan.nextLine();
				if(duplication.equals("y")) {
					deleteAccount(makeAccount);
					set.add(high);
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else {
					set.add(high);
				}
			}
			else {
				set.add(high);
				System.out.println("계좌계설이 완료되었습니다.");
			}
		}
		else {
			System.out.println("잘못누름");
		}
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
		System.out.print("입금액:"); deposit = inputDeposit();
		
		if(deposit > 0) {
			for(Account acc : set) {
				if(depositAccount.equals(acc.account)) {
					acc.interestCal(deposit);
				}
			}
			System.out.println("입금이 완료되었습니다.");
		}
		else if (deposit < 0){
			System.out.println("음수는 입력할 수 없습니다.");
		}
		System.out.println();
	}
	
	// 출    금
	public void withdrawMoney() {
		Scanner scan = new Scanner(System.in);
		String withdrawAccount, withdrawAll;
		int withdraw;
		
		System.out.println("***출 금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요.");
		System.out.print("계좌번호:"); withdrawAccount = scan.nextLine();
		System.out.print("출금액:"); withdraw = inputWithdraw();
		if(withdraw > 0) {
			for(Account acc : set) {
				if(withdrawAccount.compareTo(acc.account) == 0) {
					if(acc.balance > withdraw) {
						acc.balance -= withdraw;
					}
					else {
						System.out.println("잔고 부족. 금액전체를 출금할까요?(y or n)");
						withdrawAll = scan.nextLine();
						if(withdrawAll.equals("y")) {
							acc.balance = 0;
							System.out.println("출금이 완료되었습니다.");
						}
						else if(withdrawAll.equals("n")){
							break;
						}
					}
				}
			}
		}
		else if (withdraw < 0){
			System.out.println("음수는 입력할 수 없습니다.");
		}
		
		System.out.println();
	}
	
	// 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("***계좌정보출력***");
		for(Account acc : set) {
			acc.showAllAccount();
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
	
	public int inputDeposit() {
		Scanner scan = new Scanner(System.in);
		int deposit = 0;
		
		try {
			deposit = scan.nextInt();
			if(deposit >= 0) {
				if(deposit % 500 != 0) {
					DepositErrorException ex = new DepositErrorException();
					deposit = 0;
					throw ex;
				}
			}
		}
		catch (DepositErrorException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("문자를 입력할 수 없습니다.");
		}
		
		return deposit;
	}
	
	public int inputWithdraw() {
		Scanner scan = new Scanner(System.in);
		int withdraw = 0;
		try {
			withdraw = scan.nextInt();
			if(withdraw >= 0) {
				if(withdraw%1000 != 0) {
					WithdrawErrorException ex = new WithdrawErrorException();
					withdraw = 0;
					throw ex;
				}
			}
		}
		catch (WithdrawErrorException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("문자를 입력할 수 없습니다.");
		}
		
		return withdraw;
	}
	
	public void deleteAccount() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 계좌번호를 입력하세요:");
		String deleteAccount = scan.nextLine();
		
		int deleteIndex = -1;
		for(Account acc : set) {
			if(deleteAccount.compareTo(acc.account) == 0) {
				set.remove(acc);
				deleteIndex = 1;
				break;
			}
		}
	
		if(deleteIndex == -1) {
			System.out.println("일치하는 계좌가 없습니다.");
		}
		else {
			System.out.println("계좌를 삭제하였습니다.");
		}
	}
	
	public void deleteAccount(String deleteAccount) {
		int deleteIndex = -1;
		for(Account acc : set) {
			if(deleteAccount.compareTo(acc.account) == 0) {
				set.remove(acc);
				deleteIndex = 1;
				break;
			}
		}
	}
	
	public int choiceMenu() {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		try {
			choice = scan.nextInt();
			if(choice <= 0 || choice >= 7) {
				MenuSelectException ex = new MenuSelectException();
				throw ex;
			}
		}
		catch (MenuSelectException e) {
			System.out.println("메뉴 입력 예외 발생됨.");
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("문자를 입력할 수 없습니다.");
		}
		
		return choice;
	}
}
