package banking7;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public class AutoSaver extends Thread {
	String threadName;
	HashSet<Account> set;
	
	public AutoSaver(HashSet<Account> set) {
		this.set = set;
	}
	
	@Override
	public void run() {
		System.out.println("***자동저장을 시작합니다***");
		try {
			while(true) {
				
				PrintWriter out = new PrintWriter(
						new FileWriter("src/banking7/AutoSaveAccount.txt"));
				
				for(Account acc : set) {
					if(acc instanceof SpecialAccount) {
						SpecialAccount special = (SpecialAccount)acc;
						out.printf("[특판계좌]계좌번호=%s, 이름=%s, 잔고=%d, 기본이자=%d%%\n", 
								special.account, special.name, special.balance, 
								special.interest);
					}
					else if(acc instanceof HighCreditAccount) {
						HighCreditAccount high = (HighCreditAccount)acc;
						out.printf("[신용신뢰계좌]계좌번호=%s, 이름=%s, 잔고=%d, 기본이자=%d%%, 신용등급=%s\n", 
								high.account, high.name, high.balance, high.interest, high.grade);
					}
					else if(acc instanceof NormalAccount) {
						NormalAccount normal = (NormalAccount)acc;
						out.printf("[보통계좌]계좌번호=%s, 이름=%s, 잔고=%d, 기본이자=%d%%\n", 
								normal.account, normal.name, normal.balance, 
								normal.interest);
					}
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
		
	}
	
}
