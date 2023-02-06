package ch06.practice20;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		BankApplication bank = new BankApplication(scanner);
		
		while (true) {
			System.out.print(bank.qText);
			String choice = scanner.nextLine();
			
			boolean isStop = false;
			switch(choice) {
				case "1" :
					bank.makeAccount();
					break;
				case "2" :
					bank.listAccount();
					break;
				case "3" :
					bank.desposit();
					break;
				case "4" :
					bank.withdraw();
					break;
				case "5" :
					isStop = true;
					break;
			}
			
			if (isStop == true) {
				break;
			}
				
		}
		
		System.out.println("프로그램 종료");
	}
}
