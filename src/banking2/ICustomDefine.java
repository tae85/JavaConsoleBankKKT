package banking2;

public interface ICustomDefine {
	// 메뉴출력
	public static void menuShow() {
		System.out.println("----- Menu -----");
		System.out.print("1.계좌개설  ");
		System.out.print("2.입 금  ");
		System.out.println("3.출 금 ");
		System.out.print("4.계좌정보출력  ");
		System.out.println("5.프로그램종료 ");
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
