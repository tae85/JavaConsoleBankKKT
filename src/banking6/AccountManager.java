package banking6;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	HashSet<Account> set;
	AutoSaver as;
	int choiceGrade;
	
	public AccountManager() {
		set = new HashSet<Account>();
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		Scanner scan = new Scanner(System.in);
		String makeAccount, makeName, grade;
		int makeBalance, interest;
		
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:"); choiceGrade = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호:"); makeAccount = scan.nextLine();
		System.out.print("고객이름:"); makeName = scan.nextLine();
		System.out.print("잔고:"); makeBalance = scan.nextInt();
		scan.nextLine();
		System.out.print("기본이자%(정수형태로입력):"); interest = scan.nextInt();
		
		if(choiceGrade == 1) {
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
		else if(choiceGrade == 2) {
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
			if(choice <= 0 || choice >= 8) {
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
	
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/banking5/AccountInfo.obj"));
			for(Account acc : set) {
				out.writeObject(acc);
			}
			
			out.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("파일 없음");
		}
		catch(IOException e) {
			System.out.println("뭔가 없음");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readAccountInfo() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(
					new FileInputStream("src/banking5/AccountInfo.obj"));
			
			while(true) {
				Account acc = (Account)in.readObject();
				set.add(acc);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("[예외]Obj파일이 없습니다.");
		}
		catch(EOFException e) {
			System.out.println("[예외]파일의 끝까지 모두 복원했습니다.");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("복원 중 알 수 없는 예외발생");
		}
		finally {
			/* try절에서 예외가 발생하더라도 finally절은 무조건 실행되므로 정상적으로 닫을 수 있다. */
			try {
				in.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("클로즈 예외 발생");
			}
		}
	}
	
	public void saveOption() {
		Scanner scan = new Scanner(System.in);
		as = new AutoSaver(set);
		
		System.out.println("저장옵션을 선택하세요.");
		System.out.println("1.자동저장On, 2.자동저장off");
		int option = scan.nextInt();
		as.setDaemon(true);
		
		try {
			if(option == 1) {
				if(as.isAlive()) {
					System.out.println("이미 자동저장이 실행중입니다.");
				}
				else {
					as.start();
				}
			}
			else if (option == 2) {
				System.out.println(as.isAlive());
					as.interrupt();
				
				System.out.println("자동저장을 종료합니다.");
			}
			System.out.println("현재 활성화 된 상태의 쓰레드수" + Thread.activeCount());
			System.out.println("현재 실행중인 쓰레드명:" + Thread.currentThread().getName());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


































