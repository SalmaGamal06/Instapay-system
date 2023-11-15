import java.util.ArrayList;

public class AAIBAPI {
    private ArrayList<AAIBAccount> accounts;

    public AAIBAPI()
    {
        ArrayList<AAIBAccount> qnbAccounts = new ArrayList<>();

        AAIBAccount a1 = new AAIBAccount("999", "Salma", "01040420022", 10000);
        AAIBAccount a2 = new AAIBAccount("888", "Laila", "01202021001", 73000);
        AAIBAccount a3 = new AAIBAccount("432", "Asmaa", "01060931171", 12000);

        qnbAccounts.add(a1);
        qnbAccounts.add(a2);
        qnbAccounts.add(a3);

        this.accounts = qnbAccounts;
    }

    public boolean verifyClient( String accountNumber, String mobileNumber) {
        if (verifyAccount(accountNumber))
        {
            AAIBAccount targetAccount = getAccount(accountNumber);
            return  targetAccount!= null && targetAccount.getMobileNumber().equals(mobileNumber);
        }
        return false;
    }

    public boolean verifyAccount(String accountNumber) {
        AAIBAccount account = getAccount(accountNumber);
        return account != null;
    }

    public double getBalance(String accountNumber) {
        AAIBAccount account = getAccount(accountNumber);
        if (account != null)
            return account.getBalance();

        return 0;
    }

    private AAIBAccount getAccount(String accountNumber) {
        for (AAIBAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean withdrawal(String accountNumber, double amount) {
        AAIBAccount account = getAccount(accountNumber);
        return account != null && account.withdraw(amount);
    }

    public boolean deposit(String accountNumber, double amount) {
        AAIBAccount account = getAccount(accountNumber);
        return account != null && account.deposit(amount);
    }
}

class AAIBAccount{
    private String accountNumber;
    private String name;
    private String mobileNumber;
    private double balance;

    public AAIBAccount(String accountNumber, String name, String mobileNumber, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount){
        if(balance>= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean deposit(double amount){
        if(amount>0){
            balance+=amount;
            return true;
        }
        return false;
    }
}

