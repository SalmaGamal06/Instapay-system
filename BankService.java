public interface BankService{

    public boolean verifyBankAccount(String accountNumber);
    public boolean verifyMobileNumber(String accountNumber);

    public double getBalance(String accountNumber);

    public boolean checkBalance(String accountNumber, double amount);

    public boolean withdrawal(String accountNumber, double amount);

    public boolean deposit(String accountNumber, double amount);
}

class NEBService implements BankService{
    private NEBAPI neb;


    @Override
    public boolean verifyBankAccount(String accountNumber) {
        return  neb.verifyAccount(accountNumber);
    }

    @Override
    public boolean verifyMobileNumber(String mobileNumber) {
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

