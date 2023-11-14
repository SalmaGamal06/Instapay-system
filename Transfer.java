public interface Transfer {

    public void transferMoney(InstapayAccount s ,String recipient, double amount);
}

class WalletTransfer implements Transfer {

    WalletProvider wallet;
    public void transferMoney(InstapayAccount s ,String recipient, double amount) {
        //sends the recipient the amount using phone number
    }
}

class BankTransfer implements Transfer {


    public void transferMoney(InstapayAccount s ,String recipient, double amount) {
        //transfers to the bank account (recipient) the amount using bank account number
        if(s instanceof BankAccount){

        }

    }
}

class InstapayTransfer implements Transfer {

    @Override
    public void transferMoney(InstapayAccount s ,String recipient, double amount) {
        //transfers amount to another instapay account using username
    }
}
