import java.util.ArrayList;

public class CIB {
    private final ArrayList<CIBWalletAccount> cibWalletAccounts=new ArrayList<CIBWalletAccount>(){{
        add(new CIBWalletAccount("Salma","0123456789",1000));
        add(new CIBWalletAccount("Laila","0123456788",2000));
        add(new CIBWalletAccount("Asmaa","0123456787",3000));
    }};
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
    private CIBWalletAccount getMobile(String mobile) {
        for (CIBWalletAccount account : cibWalletAccounts) {
            if (account.getMobileNumber().equals(mobile)) {
                return account;
            }
        }
        return null;
    }
    public boolean withdrawal(String mobile,double amount){
        CIBWalletAccount cibWalletAccount = getMobile(mobile);
        if (cibWalletAccount != null) {
            return cibWalletAccount.withdraw(amount);
        } else {
            System.out.println("No CIB wallet account account selected for withdrawal.");
        }
        return false;
    }
    public boolean deposit(String mobile,double amount){
        CIBWalletAccount cibWalletAccount = getMobile(mobile);
        if (cibWalletAccount != null) {
            return cibWalletAccount.deposit(amount);
        } else {
            System.out.println("No CIB wallet account selected for deposit.");
        }
        return false;
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