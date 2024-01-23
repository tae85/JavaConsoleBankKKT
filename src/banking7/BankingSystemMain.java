package banking7;

import java.util.InputMismatchException;

import javax.swing.plaf.synth.SynthOptionPaneUI;

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
			
			try {
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
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	

}


