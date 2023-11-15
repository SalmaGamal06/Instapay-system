public interface Transfer {
    public void transferMoney(InstapayAccount s ,String recipient, double amount);
}

class WalletTransfer implements Transfer {

    WalletProvider wallet;
    public void transferMoney(InstapayAccount s ,String recipient, double amount) {
        //sends the recipient the amount using phone number
        if(s instanceof WalletAccount){
            if(wallet.withdrawal(amount)){
                wallet.deposit(amount);
            }
        }
    }
}

class BankTransfer implements Transfer {

    BankService bank;
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
