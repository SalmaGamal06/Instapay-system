public interface BankAPI{

    public boolean verifyBankAccount(String mobileNumber);

    public double getBalance(String accountNumber);

    public boolean checkBalance(String accountNumber, double amount);

    public boolean withdrawal(String accountNumber, double amount);

    public boolean deposit(String accountNumber, double amount);
}

class NEB implements BankAPI{
    private NEBAPI neb;

    @Override
    public boolean verifyBankAccount(String mobileNumber) {
        return neb.verifyClient(mobileNumber);
    }

    @Override
    public double getBalance(String accountNumber) {
        return neb.getBalance(accountNumber);
    }

    @Override
    public boolean checkBalance(String accountNumber, double amount) {
        return neb.checkBalance(accountNumber, amount);
    }

    @Override
    public boolean withdrawal(String accountNumber, double amount) {
        return neb.withdrawal( accountNumber,amount);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        return neb.deposit(accountNumber,amount);
    }
}

