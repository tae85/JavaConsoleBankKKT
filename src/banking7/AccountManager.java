
package banking7;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {
	static Scanner scan = new Scanner(System.in);
	int choiceGrade;
	HashSet<Account> set;
	AutoSaver as = null;
	
	public AccountManager() {
		set = new HashSet<Account>();
	}
	
	// 계좌개설을 위한 함수
	public void makeAccount() {
		String makeAccount, makeName, grade;
		int makeBalance, interest;
		
		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.println("3.특판계좌");
		System.out.print("선택:"); choiceGrade = scan.nextInt();
		scan.nextLine();	// 버퍼 제거용
		
		if(choiceGrade < 1 || choiceGrade > 3) {
			System.out.println("1~3사이의 숫자를 입력해주세요.");
			return;
		}
		
		System.out.print("계좌번호:"); makeAccount = scan.nextLine();
		System.out.print("고객이름:"); makeName = scan.nextLine();
		System.out.print("잔고:"); makeBalance = scan.nextInt();
		scan.nextLine();	// 버퍼 제거용
		System.out.print("기본이자%(정수형태로입력):"); interest = scan.nextInt();
		
		if(choiceGrade == 1) {
			NormalAccount normal = new NormalAccount(makeAccount, makeName, makeBalance, interest);
			if(!set.add(normal)) {	// 동일한 객체가 있어 add에 실패해서 false를 반환한다
				scan.nextLine();	// 버퍼 제거용
				System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String duplication = scan.nextLine();
				if(duplication.equals("y")) {
					set.remove(normal);
					set.add(normal);
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else {
					System.out.print("계좌개설이 취소되었습니다.");
				}
			}
			else {
				System.out.println("계좌개설이 완료되었습니다.");
			}
		}
		else if(choiceGrade == 2) {
			scan.nextLine();	// 버퍼 제거용
			System.out.print("신용등급(A,B,C등급):"); grade = scan.nextLine();
			HighCreditAccount high = new HighCreditAccount(makeAccount, makeName, makeBalance, grade, interest);
			if(!set.add(high)) {
				System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String duplication = scan.nextLine();
				if(duplication.equals("y")) {
					set.remove(high);
					set.add(high);
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else {
					System.out.print("계좌개설이 취소되었습니다.");
				}
			}
			else {
				System.out.println("계좌계설이 완료되었습니다.");
			}
		} else if(choiceGrade == 3) {
			SpecialAccount special = new SpecialAccount(makeAccount, makeName, makeBalance, interest);
			if(!set.add(special)) {
				scan.nextLine();	// 버퍼 제거용
				System.out.print("중복계좌발견됨. 덮어쓸까요?(y or n)");
				String duplication = scan.nextLine();
				if(duplication.equals("y")) {
					set.remove(special);
					set.add(special);
					System.out.println("새로운 정보로 갱신되었습니다.");
				}
				else {
					System.out.print("계좌개설이 취소되었습니다.");
				}
			}
			else {
				System.out.println("계좌개설이 완료되었습니다.");
			}
		}
		System.out.println();
	}
	
	// 입    금
	public void depositMoney() throws DepositErrorException{
		String depositAccount;
		int deposit;
		
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
	public void withdrawMoney() throws WithdrawErrorException {
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
						System.out.println("출금이 완료되었습니다.");
					}
					else {
						System.out.println("잔고 부족. 금액전체를 출금할까요?(y or n)");
						scan.nextLine();
						withdrawAll = scan.nextLine();
						if(withdrawAll.equals("y")) {
							acc.balance = 0;
							System.out.println("출금이 완료되었습니다.");
						}
						else if(withdrawAll.equals("n")){
							System.out.println("출금이 취소되었습니다.");
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
			System.out.println("--------------------");
			acc.showAllAccount();
			System.out.println("--------------------");
		}
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
		System.out.println();
	}
	
	// 입금 시 500단위로만 가능
	public int inputDeposit() throws DepositErrorException {
		int deposit = scan.nextInt();
		if(deposit >= 0) {
			if(deposit % 500 != 0) {
				DepositErrorException ex = new DepositErrorException();
				deposit = 0;
				throw ex;
			}
		}
		return deposit;
	}
	
	// 출금 시 1000단위로만 가능
	public int inputWithdraw() throws WithdrawErrorException {
		int withdraw = scan.nextInt();
		if(withdraw >= 0) {
			if(withdraw%1000 != 0) {
				WithdrawErrorException ex = new WithdrawErrorException();
				withdraw = 0;
				throw ex;
			}
		}
		return withdraw;
	}
	
	// 계좌정보삭제
	public void deleteAccount() {
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
	
	public int choiceMenu() throws MenuSelectException {
		int choice = 0;
		choice = scan.nextInt();
		scan.nextLine();
		if(choice <= 0 || choice >= 8) {
			MenuSelectException ex = new MenuSelectException();
			throw ex;
		}
		return choice;
	}
	
	// 계좌정보 obj파일 저장
	public void saveAccountInfo() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("src/banking7/AccountInfo.obj"));
			for(Account acc : set) {
				out.writeObject(acc);
			}
			
			out.close();
			System.out.println("계좌정보가 저장 되었습니다.");
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
	
	// obj파일 로드
	public void readAccountInfo() {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(
					new FileInputStream("src/banking7/AccountInfo.obj"));
			
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
		catch(InvalidClassException e) {
			System.out.println("[예외]InvalidClassException");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("복원 중 알 수 없는 예외발생");
		}
		finally {
			try {
				if(in != null) {
					in.close();
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
				System.out.println("클로즈 예외 발생");
			}
		}
	}
	
	// 저장옵션
	public void saveOption() {
		System.out.println("저장옵션을 선택하세요.");
		System.out.println("1.자동저장On, 2.자동저장off");
		int option = scan.nextInt();
		
		try {
			if(option == 1) {
				if(as == null) {
					as = new AutoSaver(set);
					as.setDaemon(true);
					as.start();
					System.out.println("자동저장을 시작합니다.");
				}
				else {
					if(!as.isAlive()) {
						as = new AutoSaver(set);
						as.setDaemon(true);
						as.start();
						System.out.println("자동저장을 시작합니다.");
					}
					else {
						System.out.println("이미 자동저장이 실행중입니다.");
					}
				}
			}
			else if (option == 2) {
				if(as.isAlive()) {
					as.interrupt();
					System.out.println("자동저장을 종료합니다.");
				}
				else {
					System.out.println("자동저장이 실행중이 아닙니다.");
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}


































