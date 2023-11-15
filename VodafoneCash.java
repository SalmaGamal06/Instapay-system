import java.util.ArrayList;

public class VodafoneCash {
    private final ArrayList<VodafoneCashAccount> vodafoneAccounts = new ArrayList<VodafoneCashAccount>() {{
        add(new VodafoneCashAccount("Salma", "01234567891", 1000));
        add(new VodafoneCashAccount("Laila", "0123456788", 2000));
        add(new VodafoneCashAccount("Asmaa", "0123456787", 3000));
    }};
    VodafoneCashAccount vodafoneCashAccount;

    public boolean verifyWallet(String mobileNumber) {
        String mobile;
        for (VodafoneCashAccount vodafoneAccounts : vodafoneAccounts) {
            mobile = vodafoneAccounts.getMobileNumber();
            if (mobile.equals(mobileNumber)) {
                return true;
            }
        }
        return false;
    }

    public double getBalance(String mobileNumber) {
        for (VodafoneCashAccount vodafoneAccounts : vodafoneAccounts) {
            if (mobileNumber.equals(vodafoneAccounts.getMobileNumber())) {
                return vodafoneAccounts.getBalance();
            }
        }
        System.out.println("No account exists with this mobile number");
        return 0;
    }

    private VodafoneCashAccount getMobile(String mobile) {
        for (VodafoneCashAccount account : vodafoneAccounts) {
            if (account.getMobileNumber().equals(mobile)) {
                return account;
            }
        }
        return null;
    }

    public boolean withdrawal(String mobile, double amount) {
        VodafoneCashAccount vodafoneCashAccount = getMobile(mobile);
        if (vodafoneCashAccount != null) {
            return vodafoneCashAccount.withdraw(amount);
        } else {
            System.out.println("No Vodafone Cash account selected for withdrawal.");
            return false;
        }
    }

    public boolean deposit(String mobile, double amount) {
        VodafoneCashAccount vodafoneCashAccount = getMobile(mobile);
        if (vodafoneCashAccount != null) {
            return vodafoneCashAccount.deposit(amount);
        } else {
            System.out.println("No Vodafone Cash account selected for deposit.");
            return false;
        }
    }

}

class VodafoneCashAccount {
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

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
}