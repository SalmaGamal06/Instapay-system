public interface BankService{

    public boolean verifyBankAccount(String accountNumber);

    boolean verifyMobileNumber(String accountNumber, String mobileNumber);

    public double getBalance(String accountNumber);

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
    public boolean verifyMobileNumber(String accountNumber, String mobileNumber) {
        return neb.verifyClient(accountNumber, mobileNumber);
    }

    @Override
    public double getBalance(String accountNumber) {
        return neb.getBalance(accountNumber);
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


class QNBService implements BankService{
    private QNBAPI qnb;


    @Override
    public boolean verifyBankAccount(String accountNumber) {
        return  qnb.verifyAccount(accountNumber);
    }

    @Override
    public boolean verifyMobileNumber(String accountNumber, String mobileNumber) {
        return qnb.verifyClient(accountNumber, mobileNumber);
    }

    @Override
    public double getBalance(String accountNumber) {
        return qnb.getBalance(accountNumber);
    }


    @Override
    public boolean withdrawal(String accountNumber, double amount) {
        return qnb.withdrawal( accountNumber,amount);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        return qnb.deposit(accountNumber,amount);
    }
}


class AAIBService implements BankService{
    private AAIBAPI aaib;


    @Override
    public boolean verifyBankAccount(String accountNumber) {
        return  aaib.verifyAccount(accountNumber);
    }

    @Override
    public boolean verifyMobileNumber(String accountNumber, String mobileNumber) {
        return aaib.verifyClient(accountNumber, mobileNumber);
    }

    @Override
    public double getBalance(String accountNumber) {
        return aaib.getBalance(accountNumber);
    }


    @Override
    public boolean withdrawal(String accountNumber, double amount) {
        return aaib.withdrawal( accountNumber,amount);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        return aaib.deposit(accountNumber,amount);
    }
}


