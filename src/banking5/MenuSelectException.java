package banking5;

public class MenuSelectException extends Exception {
	
	public MenuSelectException() {
		super("메뉴는 1~6사이의 정수를 입력하세요.");
	}
	
}

class DepositErrorException extends Exception {
	
	public DepositErrorException() {
		super("입금액은 500원 단위로 가능합니다.");
	}
	
}

class WithdrawErrorException extends Exception {
	
	public WithdrawErrorException() {
		super("출금액은 1000원 단위로 가능합니다.");
	}
	
}