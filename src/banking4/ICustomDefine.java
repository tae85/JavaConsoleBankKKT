package banking4;

public interface ICustomDefine {
	// 메뉴출력
	public static void menuShow() {
		System.out.println("------------- Menu -------------");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입금  ");
		System.out.print("3.출금  ");
		System.out.println("4.계좌정보출력");
		System.out.print("5.계좌정보삭제   ");
		System.out.println("6.프로그램종료");
		System.out.print("선택:");
	}
	
	public static int creditGrade(String grade) {
		int addInterest;
		
		switch(grade) {
		case "A" :
			addInterest = 2;
			break;
		case "B" :
			addInterest = 4;
			break;
		case "C" :
			addInterest = 7;
			break;
		default :
			addInterest = 0;
			break;
		}
		
		return addInterest;
	}
}
