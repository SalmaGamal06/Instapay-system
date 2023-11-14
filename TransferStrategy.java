public interface TransferStrategy {

    
    public void transferMoney(String recipient, double amount);
}

class TransferBillPayments implements TransferStrategy {

    public void transferMoney(String recipient, double amount) {
        //transfer amount to utility company
        //payBill calls this function to tansfer amount to correct company

    }
}

class WalletTransfer implements TransferStrategy {

    WalletProvider wallet;
    public void transferMoney(String recipient, double amount) {
        //sends the recipient the amount using phone number
    }
}

class BankTransfer implements TransferStrategy {

    public void transferMoney(String recipient, double amount) {
        //transfers to the bank account (recipient) the amount using bank account number
    }
}

class InstapayTransfer implements TransferStrategy {

    @Override
    public void transferMoney(String recipient, double amount) {
        //transfers amount to another instapay account using username
    }
}
