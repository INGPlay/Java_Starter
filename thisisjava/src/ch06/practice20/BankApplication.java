package ch06.practice20;

import java.util.Scanner;

public class BankApplication {
	Scanner scanner;
	
	Account[] accountArray = new Account[100];
	
	String qText = """
			-----------------------------------------
			1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료
			-----------------------------------------
			선택> """;
	String text1 = """
			------
			계좌생성
			------
			""";
	String text2 = """
			------
			계좌목록
			------
			""";
	String text3 = """
			------
			예금
			------
			""";
	String text4 = """
			------
			출금
			------
			""";
	String text5 = """
			------
			종료
			------
			""";
	
	BankApplication(Scanner scanner) {
		this.scanner = scanner;
	}
	
	void makeAccount() {
		System.out.println(this.text1);
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
		
		System.out.print("계좌주: ");
		String name = scanner.nextLine();
		
		System.out.print("초기입금액: ");
		int money = Integer.parseInt(scanner.nextLine());
		
		Account account = new Account(accountNumber, name, money);
		
		for (int i = 0; i < this.accountArray.length; i++) {
			if (this.accountArray[i] == null) {
				this.accountArray[i] = account;
				break;
			}
		}
		
		System.out.println("계좌가 생성되었습니다.");
	}
	
	void listAccount() {
		System.out.println(this.text2);
		for (int i = 0; i < this.accountArray.length; i++) {
			if (this.accountArray[i] == null) {
				break;
			}
			
			System.out.println(this.accountArray[i].getAccountNumber() + " | " + this.accountArray[i].getName() + " | " + this.accountArray[i].getMoney());
		}
	}
	
	void desposit() {
		System.out.println(this.text3);
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
		
		int accountIndex = 0;
		for (int i = 0; i < this.accountArray.length; i++) {
			if (this.accountArray[i] == null) {
				System.out.println("계좌가 없습니다.");
				return;
			}
			
			if (this.accountArray[i].getAccountNumber().equals(accountNumber)) {
				accountIndex = i;
				break;
			}
		}
		
		System.out.print("예금액: ");
		int money = Integer.parseInt(scanner.nextLine());
		
		this.accountArray[accountIndex].setMoney(money);
	}
	
	void withdraw() {
		System.out.println(this.text4);
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
		
		int accountIndex = 0;
		for (int i = 0; i < this.accountArray.length; i++) {
			if (this.accountArray[i] == null) {
				System.out.println("계좌가 없습니다.");
				return;
			}
			
			if (this.accountArray[i].getAccountNumber().equals(accountNumber)) {
				accountIndex = i;
				break;
			}
		}
		
		System.out.print("출금액: ");
		int money = Integer.parseInt(scanner.nextLine());
		
		if (money > this.accountArray[accountIndex].getMoney()) {
			System.out.println("충분한 잔액이 없습니다.");
			return;
		}
		this.accountArray[accountIndex].setMoney(-money);
	}

}
