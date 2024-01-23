package banking7;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class AutoSaver extends Thread {
	String threadName;
	HashSet<Account> set;
	boolean isAutoSaver = false;
	
	public AutoSaver() {
		
	}
	public AutoSaver(HashSet<Account> set) {
		this.set = set;
	}
	
	@Override
	public void run() {
		System.out.println("***자동저장을 시작합니다***");
		try {
			while(true) {
				
				BufferedWriter out = new BufferedWriter(
						new FileWriter("src/banking7/AutoSaveAccount.txt"));
				
				for(Account acc : set) {
					if(acc instanceof SpecialAccount) {
						out.write("[특판계좌]");
					}
					else if(acc instanceof HighCreditAccount) {
						out.write("[신용신뢰계좌]");
					}
					else if(acc instanceof NormalAccount) {
						out.write("[보통계좌]");
					}
					out.write("계좌번호=");
					out.write(acc.account);
					out.write(", 이름=");
					out.write(acc.name);
					out.write(", 잔고=");
					out.write(String.valueOf(acc.balance));
					out.write(", 기본이자=");
					out.write(String.valueOf(acc.interest));
					out.write("%");
					if(acc instanceof HighCreditAccount) {
						out.write(", 신용등급=");
						HighCreditAccount high = (HighCreditAccount)acc;
						out.write(high.grade);
					}
					out.newLine();
				}
				System.out.println("계좌정보가 텍스트로 자동저장되었습니다.");
				out.close();
				sleep(5000);
				
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("파일없음");
		}
		catch (InterruptedException e) {
			System.out.println("InterruptedException 예외발생");
		}
		catch(IOException e) {
			System.out.println("IO오류");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		super.run();
		
		
//		System.out.println("AutoSaver예외발생");
//		System.out.println("쓰레드=Thread[Thread-0,5,main]");
//		
//		System.out.println("선택:");
//		
//		System.out.println("자동저장을 시작합니다.");
//		
//		System.out.println("자동저장을 종료합니다.");
	}
	
	
	
	

	
}
