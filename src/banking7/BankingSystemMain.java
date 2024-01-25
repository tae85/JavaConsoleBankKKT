package banking7;

import java.util.InputMismatchException;

public class BankingSystemMain {
	
	public static void main(String[] args) {
		// 핸들러 호출
		AccountManager manager = new AccountManager();
		
		//  obj파일 리딩 
		manager.readAccountInfo();
		
		// 프로그램 실행 함수
		while(true) {
			
			ICustomDefine.menuShow();
			
			try {
				int choice = manager.choiceMenu();
				
				switch (choice) {
				case ICustomDefine.MAKE:
					manager.makeAccount();
					break;
				case ICustomDefine.DEPOSIT:
					manager.depositMoney();
					break;
				case ICustomDefine.WITHDRAW:
					manager.withdrawMoney();
					break;
				case ICustomDefine.INQUIRE:
					manager.showAccInfo();
					break;
				case ICustomDefine.DELETE:
					manager.deleteAccount();
					break;
				case ICustomDefine.SAVEOPTION:
					manager.saveOption();
					break;
				case ICustomDefine.EXIT:
					manager.saveAccountInfo();
					System.out.println("프로그램 종료");
					return;
				}
			}
			catch (MenuSelectException e) {
				System.out.println("메뉴 입력 예외 발생됨.");
				System.out.println(e.getMessage());
			}
			catch (DepositErrorException e) {
				System.out.println(e.getMessage());
			}
			catch (WithdrawErrorException e) {
				System.out.println(e.getMessage());
			}
			catch(InputMismatchException e) {
				System.out.println("문자를 입력할 수 없습니다.");
				AccountManager.scan.next();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}


