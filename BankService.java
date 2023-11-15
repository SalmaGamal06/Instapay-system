public abstract class BankService implements Provider {

    public abstract boolean verify(String accountNumber);

    abstract boolean verifyMobileNumber(String accountNumber, String mobileNumber);

    public abstract double getBalance(String accountNumber);

    public abstract boolean withdrawal(String accountNumber, double amount);

    public abstract boolean deposit(String accountNumber, double amount);
}

class NEBService extends BankService {
    private NEBAPI neb;

    public NEBService(NEBAPI neb) {
        this.neb = neb;
    }

    @Override
    public boolean verify(String accountNumber) {
        return neb.verifyAccount(accountNumber);
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
        return neb.withdrawal(accountNumber, amount);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        return neb.deposit(accountNumber, amount);
    }
}


class QNBService extends BankService {
    private QNBAPI qnb;

    public QNBService(QNBAPI qnb) {
        this.qnb = qnb;
    }

    @Override
    public boolean verify(String accountNumber) {
        return qnb.verifyAccount(accountNumber);
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
        return qnb.withdrawal(accountNumber, amount);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        return qnb.deposit(accountNumber, amount);
    }
}


class AAIBService extends BankService {
    private AAIBAPI aaib;

    public AAIBService(AAIBAPI aaib) {
        this.aaib = aaib;
    }

    @Override
    public boolean verify(String accountNumber) {
        return aaib.verifyAccount(accountNumber);
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
        return aaib.withdrawal(accountNumber, amount);
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        return aaib.deposit(accountNumber, amount);
    }
}


