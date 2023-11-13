import java.util.ArrayList;

public class QNBAPI {
    private ArrayList<QNBAccount> accounts;

    public QNBAPI()
    {
        ArrayList<QNBAccount> qnbAccounts = new ArrayList<>();

        QNBAccount a1 = new QNBAccount("123", "Salma", "1234567", 6000);
        QNBAccount a2 = new QNBAccount("234", "Laila", "1234569", 7000);
        QNBAccount a3 = new QNBAccount("345", "Asma", "1234561", 6600);

        qnbAccounts.add(a1);    
        qnbAccounts.add(a2);
        qnbAccounts.add(a3);

        this.accounts = qnbAccounts;
    }

    public boolean verifyClient(String mobileNumber) {
        String mobile;
        for (QNBAccount account : accounts) {
            mobile = account.getMobileNumber();
            if (mobile.equals(mobileNumber)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyAccount(String accountNumber) {
        String mobile;
        for (QNBAccount account : accounts) {
            mobile = account.getMobileNumber();
            if (mobile.equals(accountNumber)) {
                return true;
            }
        }
        return false;
    }

    public double getBalance(String accountNumber) {
        QNBAccount account = getAccount(accountNumber);
        if (account != null)
            return account.getBalance();

        return 0;
    }

    public boolean checkBalance(String accountNumber, double amount) {
        QNBAccount account = getAccount(accountNumber);
        return account != null && account.getBalance() >= amount;
    }

    private QNBAccount getAccount(String accountNumber) {
        for (QNBAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean withdrawal(String accountNumber, double amount) {
        QNBAccount account = getAccount(accountNumber);
        return account != null && account.withdraw(amount);
    }

    public boolean deposit(String accountNumber, double amount) {
        QNBAccount account = getAccount(accountNumber);
        return account != null && account.deposit(amount);
    }
}
class QNBAccount{
    private String accountNumber;
    private String name;
    private String mobileNumber;
    private double balance;

    public QNBAccount(String accountNumber, String name, String mobileNumber, double balance) {
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



