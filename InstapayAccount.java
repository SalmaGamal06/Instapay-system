public abstract class InstapayAccount {
    public abstract double inquireBalance();
}

class BankAccount extends InstapayAccount {
    String bankAccountNumber;
    BankService bank;

    public BankAccount(BankService bank) {
        this.bank = bank;
    }

    public boolean processAccountVerification(String accountNumber) {
        return bank.verifyBankAccount(accountNumber);
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

    public WalletAccount(WalletProvider wallet) {
        this.wallet = wallet;
    }

    public boolean processWalletVerification(String mobileNumber) {
        return wallet.verifyWallet(mobileNumber);
    }

    @Override
    public double inquireBalance() {
        return wallet.getBalance(this.mobileNumber);
    }
}

