package main;

public class Account {
	private String accountNumber, customerName;
	private float totalBalance;
	private boolean isSalaryAccount;

	public Account(String accountNumber, String customerName, boolean isSalaryAccount) throws InvalidAccountNumber{
		if(isSalaryAccount){
			if(accountNumber.startsWith("SAL")){}
			else{
				throw new InvalidAccountNumber();
			}
		}else {
			if(accountNumber.startsWith("GEN")){}
			else{
				throw new InvalidAccountNumber();
			}
		}
		
		this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.isSalaryAccount = isSalaryAccount;
	}

	public void setTotalBalance(float totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public float getTotalBalance() {
		return totalBalance;
	}
	public boolean isSalaryAccount() {
		return isSalaryAccount;
	}


}
