import java.util.ArrayList;

public class Fawry {
    private ArrayList<FawryWalletAccount> FawryWalletAccounts;
    FawryWalletAccount fawryWalletAccount;
    public boolean verifyWallet(String mobileNumber) {
        String mobile;
        for(FawryWalletAccount fawryWalletAccount:FawryWalletAccounts){
            mobile = fawryWalletAccount.getMobileNumber();
            if(mobile.equals(mobileNumber)){
                return true;
            }
        }
        return false;
    }
    public double getBalance(String mobileNumber){
        for(FawryWalletAccount fawryWalletAccount:FawryWalletAccounts){
            if(mobileNumber.equals(fawryWalletAccount.getMobileNumber())) {
                return fawryWalletAccount.getBalance();
            }
        }
        System.out.println("No account exists with this mobile number");
        return 0;
    }

    public void withdrawal(double amount){
        if (fawryWalletAccount != null) {
            fawryWalletAccount.withdraw(amount);
        } else {
            System.out.println("No Fawry wallet account account selected for withdrawal.");
        }

    }
    public void deposit(double amount){
        if (fawryWalletAccount != null) {
            fawryWalletAccount.deposit(amount);
        } else {
            System.out.println("No Fawry wallet account selected for deposit.");
        }
    }
}
class FawryWalletAccount{
    private String mobileNumber;
    private String userName;
    private double balance;

    public FawryWalletAccount(String name, String mobileNumber, double balance) {
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
            System.out.println("The amount "+amount+"EGP"+"has been withdrawn successfully from your Fawry wallet account");
            return true;
        }
        else{
            System.out.println("Transaction failed.Insufficient funds in your Fawry wallet account");
            return false;
        }
    }

    public boolean deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("The amount "+amount+"EGP"+"has been deposited successfully into your Fawry wallet account");
            return true;
        }
        return false;
    }
}
