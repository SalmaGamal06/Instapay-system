public abstract class InstapayAccount {
    public abstract double inquireBalance();
}

class BankAccount extends InstapayAccount {
    String bankAccountNumber;
    BankService bank;

    public BankAccount(BankService bank, String bankAccountNumber) {
        this.bank = bank;
        this.bankAccountNumber = bankAccountNumber;
    }
    public BankService getBankService(){
        return this.bank;
    }
    public boolean processAccountVerification(String accountNumber) {
        return bank.verify(accountNumber);
    }


    public boolean processMobileVerification(String accountNumber, String mobileNumber) {
        return bank.verifyMobileNumber(accountNumber, mobileNumber);
    }

    @Override
    public double inquireBalance() {
        return bank.getBalance(this.bankAccountNumber);
    }
}


class WalletAccount extends InstapayAccount {
    WalletProvider wallet;

    String mobileNumber;

    public WalletAccount(WalletProvider wallet, String mobileNumber) {
        this.wallet = wallet;
        this.mobileNumber = mobileNumber;
    }
    public WalletProvider getWalletProvider(){
        return this.wallet;
    }

    public boolean processWalletVerification(String mobileNumber) {
        return wallet.verify(mobileNumber);
    }

    @Override
    public double inquireBalance() {
        return wallet.getBalance(this.mobileNumber);
    }
}

