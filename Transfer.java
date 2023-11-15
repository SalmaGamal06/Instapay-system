import java.io.File;
import java.io.FileNotFoundException;

public interface Transfer {
    public void transferMoney(InstapayAccount s, String recipient, double amount) throws FileNotFoundException;
}

class WalletTransfer implements Transfer {
    FileStorage file = new FileStorage();
    WalletProvider wallet;

    //recipient here means mobile number
    public void transferMoney(InstapayAccount s, String recipient, double amount) throws FileNotFoundException {
        //sends the recipient the amount using phone number
        if (s instanceof WalletAccount) {
            wallet = file.readByMobile(recipient).getWalletProvider();
            if (wallet.withdrawal(recipient, amount)) {
                wallet.deposit(recipient, amount);
                System.out.println("Transfer done successfully");
            } else {
                System.out.println("Insufficient balance");
            }
        }
    }
}

class BankTransfer implements Transfer {
    FileStorage file = new FileStorage();
    BankService bank;
    BankService bank2;

    //recipient here means bank account number
    public void transferMoney(InstapayAccount s, String recipient, double amount) throws FileNotFoundException {
        //transfers to the bank account (recipient) the amount using bank account number
        if (s instanceof BankAccount) {
            bank = file.readByAccountNum(recipient).getBankService();
            if (bank.withdrawal(recipient, amount)) {
                bank.deposit(recipient, amount);
                System.out.println("Transfer done successfully");
            } else {
                System.out.println("Insufficient balance");
            }
        }
    }
}

class InstapayTransfer implements Transfer {
    FileStorage file = new FileStorage();

    //recipient here means username
    @Override
    public void transferMoney(InstapayAccount s, String recipient, double amount) throws FileNotFoundException {
        //transfers amount to another instapay account using username
        if (s instanceof BankAccount) {
            if (file.read(recipient).getTypeOfInstapayAccount().equals("Bank")) {
                String bankAccountNumber = file.read(recipient).getBankAccountNumber();
                BankTransfer bankTransfer = new BankTransfer();
                bankTransfer.transferMoney(s, bankAccountNumber, amount);
            } else if (file.read(recipient).getTypeOfInstapayAccount().equals("Wallet")) {
                String mobileNumber = file.read(recipient).getMobileNumber();
                WalletTransfer walletTransfer = new WalletTransfer();
                walletTransfer.transferMoney(s, mobileNumber, amount);
            }
        } else if (s instanceof WalletAccount) {
            if (file.read(recipient).getTypeOfInstapayAccount().equals("Wallet")) {
                String mobileNumber = file.read(recipient).getMobileNumber();
                WalletTransfer walletTransfer = new WalletTransfer();
                walletTransfer.transferMoney(s, mobileNumber, amount);
            } else {
                System.out.println("You can't transfer to a bank account using a wallet");
            }
        }
    }
}
// bank bank
//bank wallet
//wallet wallet