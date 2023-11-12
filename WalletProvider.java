public interface WalletProvider {
    public boolean verifyWallet(String mobileNumber);
    public double getBalance(double balance);
    public boolean checkBalance(double balance);
    public void withdrawal(double amount);
    public void deposit(double amount);

}
