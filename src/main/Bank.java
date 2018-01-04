package main;

import java.util.Vector;

public class Bank {
	public int minimumBalance;
	public Vector<Account> accounts = new Vector<Account>();
	
	public Bank(int minimumBalance){
		this.minimumBalance = minimumBalance;
	}
	
	public static void main(String... args) throws LowBalanceException{
		Bank kotak = new Bank(2000);
		Account a1 = kotak.createAccount("SAL12345", "Debanita", true);
		Account a2 = kotak.createAccount("GEN12345", "Debanita", false);
		//Account a3 = kotak.createAccount("ABC3456", "Debanita", true); // return null;
		
		System.out.println(kotak.enquireBalance(a1)); //2000
		System.out.println(kotak.enquireBalance(a2));  //2000
		System.out.println(kotak.depositAmount(a1, 20000)); //22000
		System.out.println(kotak.enquireBalance(a1)); //22000
		System.out.println(kotak.depositAmount(a2, 60000)); //62000
		System.out.println(kotak.enquireBalance(a2)); //62000
		
		System.out.println(kotak.withdrawAmount(a1, 10000)); //12000
		System.out.println(kotak.enquireBalance(a1)); //12000
		//System.out.println(kotak.withdrawAmount(a2, 61000)); // throws LowBalanceException
		
		System.out.println(kotak.generateBonus(a1)); //360
		System.out.println(kotak.enquireBalance(a1)); //12360
		System.out.println(kotak.generateBonus(a2)); //1480
		System.out.println(kotak.enquireBalance(a2)); //63480
	}
	
	public Account createAccount(String accountNumber, String customerName, boolean isSalaryAccount){
		Account account;
		try {
			account = new Account(accountNumber, customerName, isSalaryAccount);
			account.setTotalBalance(minimumBalance);
			accounts.addElement(account);
			return account;
		} catch (Exception e) {
			return null;
		}
	}
	
	public float depositAmount(Account acc, float amount){
		acc.setTotalBalance(acc.getTotalBalance()+amount);
		return acc.getTotalBalance();
	}
	
	public float withdrawAmount(Account acc, float amount) throws LowBalanceException{
		float currentBalance = acc.getTotalBalance();
		if((currentBalance-amount) >= this.minimumBalance){
			acc.setTotalBalance(currentBalance-amount);
			return acc.getTotalBalance();
		}else{
			throw new LowBalanceException();
		}
	}
	
	public float generateBonus(Account acc){
		float currentBalance = acc.getTotalBalance();
		float bonusAmount = 0f;
		if(acc.isSalaryAccount()){
			if(currentBalance>50000){
				bonusAmount = 50000f * 3f / 100f;
				bonusAmount += (currentBalance-50000) * 6f / 100f;
			}else{
				bonusAmount = currentBalance * 3f / 100f;
			}
		}else{
			if(currentBalance>50000){
				bonusAmount = 50000f * 2f / 100f;
				bonusAmount += (currentBalance-50000) * 4f / 100f;
			}else{
				bonusAmount = currentBalance * 4f / 100f;
			}
		}
		acc.setTotalBalance(bonusAmount+currentBalance);
		return bonusAmount;
	}
	
	public float enquireBalance(Account acc){
		return acc.getTotalBalance();
	}

}
