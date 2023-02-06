package ch06.practice20;

public class Account {
	private String accountNumber;
	private String name;
	private int money;
	
	public String getAccountNumber() {
		return this.accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
		return;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
		return;
	}
	
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money += money;
		return;
	}
	
	public Account(String accountNumber, String name, int money) {
		this.setAccountNumber(accountNumber);
		this.setName(name);
		this.setMoney(money);
	}
}
