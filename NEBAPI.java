import java.util.ArrayList;

public class NEBAPI {
    private ArrayList<NEBAccount> accounts;

    public NEBAPI()
    {
        ArrayList<NEBAccount> nebAccounts = new ArrayList<>();

        NEBAccount a1 = new NEBAccount("123", "Salma", "01040420022", 6000);
        NEBAccount a2 = new NEBAccount("234", "Laila", "01202021001", 7000);
        NEBAccount a3 = new NEBAccount("345", "Asmaa", "01060931171", 6600);

        nebAccounts.add(a1);
        nebAccounts.add(a2);
        nebAccounts.add(a3);

        this.accounts = nebAccounts;
    }

    public boolean verifyClient(String accountNumber, String mobileNumber) {
        if (verifyAccount(accountNumber))
        {
            NEBAccount targetAccount = getAccount(accountNumber);
            return  targetAccount!= null && targetAccount.getMobileNumber().equals(mobileNumber);
        }
        return false;
    }

    public boolean verifyAccount(String accountNumber) {
        NEBAccount account = getAccount(accountNumber);
        return account != null;
    }

    public double getBalance(String accountNumber) {
        NEBAccount account = getAccount(accountNumber);
        if (account != null)
            return account.getBalance();

        return 0;
    }


    private NEBAccount getAccount(String accountNumber) {
        for (NEBAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean withdrawal(String accountNumber, double amount) {
        NEBAccount account = getAccount(accountNumber);
        return account != null && account.withdraw(amount);
    }

    public boolean deposit(String accountNumber, double amount) {
        NEBAccount account = getAccount(accountNumber);
        return account != null && account.deposit(amount);
    }
}
class NEBAccount{
    private String accountNumber;
    private String name;
    private String mobileNumber;
    private double balance;

    public NEBAccount(String accountNumber, String name, String mobileNumber, double balance) {
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

