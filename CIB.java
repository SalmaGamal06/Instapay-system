import java.util.ArrayList;

public class CIB {
    private ArrayList<CIBWalletAccount> cibWalletAccounts;
    CIBWalletAccount cibWalletAccount;
    public boolean verifyWallet(String mobileNumber) {
        String mobile;
        for(CIBWalletAccount cibWalletAccount:cibWalletAccounts){
            mobile = cibWalletAccount.getMobileNumber();
            if(mobile.equals(mobileNumber)){
                return true;
            }
        }
        return false;
    }
    public double getBalance(String mobileNumber){
        for(CIBWalletAccount cibWalletAccount:cibWalletAccounts){
            if(mobileNumber.equals(cibWalletAccount.getMobileNumber())) {
                return cibWalletAccount.getBalance();
            }
        }
        System.out.println("No account exists with this mobile number");
        return 0;
    }
    //    Function that checks if the user has sufficient funds
    public boolean checkBalance(String mobileNumber,double amount){
        for(CIBWalletAccount cibWalletAccount:cibWalletAccounts){
            if(mobileNumber.equals(cibWalletAccount.getMobileNumber())){
                if(cibWalletAccount.getBalance()>=amount){
                    return true;
                }
            }
        }
        return false;
    }
    public void withdrawal(double amount){
        if (cibWalletAccount != null) {
            cibWalletAccount.withdraw(amount);
        } else {
            System.out.println("No CIB wallet account account selected for withdrawal.");
        }

    }
    public void deposit(double amount){
        if (cibWalletAccount != null) {
            cibWalletAccount.deposit(amount);
        } else {
            System.out.println("No CIB wallet account selected for deposit.");
        }
    }

}
class CIBWalletAccount{
    private String mobileNumber;
    private String userName;
    private double balance;

    public CIBWalletAccount(String name, String mobileNumber, double balance) {
        this.userName = name;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getUserNameName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount){
        if(balance>= amount) {
            balance -= amount;
            System.out.println("The amount "+amount+"EGP"+"has been withdrawn successfully from your CIB wallet account");
            return true;
        }
        else{
            System.out.println("Transaction failed.Insufficient funds in your CIB wallet account");
            return false;
        }
    }

    public boolean deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("The amount "+amount+"EGP"+"has been deposited successfully into your CIB wallet account");
            return true;
        }
        return false;
    }
}