public interface BankAPI{

    public boolean verifyBankAccount(String mobileNumber);

    public double getBalance(String accountNumber);

    public boolean checkBalance(String accountNumber, double amount);

    public boolean withdrawal(String accountNumber, double amount);

    public boolean deposit(String accountNumber, double amount);
}
