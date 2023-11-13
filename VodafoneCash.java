import java.util.ArrayList;

public class VodafoneCash {
    private ArrayList<VodafoneCashAccount> vodafoneAccounts;
    VodafoneCashAccount vodafoneCashAccount;
    public boolean verifyWallet(String mobileNumber) {
        String mobile;
        for(VodafoneCashAccount vodafoneAccounts:vodafoneAccounts){
            mobile = vodafoneAccounts.getMobileNumber();
            if(mobile.equals(mobileNumber)){
                return true;
            }
        }
        return false;
    }
    public double getBalance(String mobileNumber){
        for(VodafoneCashAccount vodafoneAccounts:vodafoneAccounts){
            if(mobileNumber.equals(vodafoneAccounts.getMobileNumber())) {
                return vodafoneAccounts.getBalance();
            }
        }
        System.out.println("No account exists with this mobile number");
        return 0;
    }
//    Function that checks if the user has sufficient funds
    public boolean checkBalance(String mobileNumber,double amount){
        for(VodafoneCashAccount vodafoneAccounts:vodafoneAccounts){
            if(mobileNumber.equals(vodafoneAccounts.getMobileNumber())){
                if(vodafoneAccounts.getBalance()>=amount){
                    return true;
                }
            }
        }
        return false;
    }
    public void withdrawal(double amount){
        if (vodafoneCashAccount != null) {
            vodafoneCashAccount.withdraw(amount);
        } else {
            System.out.println("No Vodafone Cash account selected for withdrawal.");
        }

    }
    public void deposit(double amount){
        if (vodafoneCashAccount != null) {
            vodafoneCashAccount.deposit(amount);
        } else {
            System.out.println("No Vodafone Cash account selected for deposit.");
        }
    }

}
class VodafoneCashAccount{
    private String mobileNumber;
    private String userName;
    private double balance;

    public VodafoneCashAccount(String name, String mobileNumber, double balance) {
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
            System.out.println("The amount "+amount+"EGP"+"has been withdrawn successfully from your Vodafone Cash account");
            return true;
        }
        else{
            System.out.println("Transaction failed.Insufficient funds in your Vodafone Cash account");
            return false;
        }
    }

    public boolean deposit(double amount){
        if(amount>0){
            balance+=amount;
            System.out.println("The amount "+amount+"EGP"+"has been deposited successfully into your Vodafone Cash account");
            return true;
        }
        return false;
    }
}