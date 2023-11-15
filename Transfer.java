import java.io.File;
import java.io.FileNotFoundException;

public interface Transfer {
    public void transferMoney(User s, Provider recipient_provider, String recipient, double amount) throws FileNotFoundException;
}

class WalletTransfer implements Transfer {
    //recipient here means mobile number
    public void transferMoney(User s, Provider recipient_provider, String recipient, double amount) throws FileNotFoundException {
        //sends the recipient the amount using phone number
        if (s.getWalletProvider().withdrawal(s.getMobileNumber(), amount)) {
            recipient_provider.deposit(recipient, amount);
            System.out.println("Transfer done successfully");
        } else {
            System.out.println("Insufficient balance");
        }
    }
}

class BankTransfer implements Transfer {
    //recipient here means bank account number
    public void transferMoney(User s, Provider recipient_provider, String recipient, double amount) throws FileNotFoundException {
        //transfers to the bank account (recipient) the amount using bank account number
        if (s.getInstapayAccount() instanceof BankAccount) {
            if (s.getBankService().withdrawal(s.getBankAccountNumber(), amount)) {
                recipient_provider.deposit(recipient, amount);
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
    public void transferMoney(User s, Provider recipient_provider, String recipient, double amount) throws FileNotFoundException {
        //transfers amount to another instapay account using username
        if (s.getInstapayAccount() instanceof BankAccount) {
            if (file.read(recipient).getTypeOfInstapayAccount().equals("Bank")) {
                String bankAccountNumber = file.read(recipient).getBankAccountNumber();
                BankTransfer bankTransfer = new BankTransfer();
                bankTransfer.transferMoney(s, recipient_provider, bankAccountNumber, amount);
            } else if (file.read(recipient).getTypeOfInstapayAccount().equals("Wallet")) {
                String mobileNumber = file.read(recipient).getMobileNumber();
                WalletTransfer walletTransfer = new WalletTransfer();
                walletTransfer.transferMoney(s, recipient_provider, mobileNumber, amount);
            }
        } else if (s.getInstapayAccount() instanceof WalletAccount) {
            if (file.read(recipient).getTypeOfInstapayAccount().equals("Wallet")) {
                String mobileNumber = file.read(recipient).getMobileNumber();
                WalletTransfer walletTransfer = new WalletTransfer();
                walletTransfer.transferMoney(s, recipient_provider, mobileNumber, amount);
            } else {
                System.out.println("You can't transfer to a bank account using a wallet");
            }
        }
    }
}
// bank bank
//bank wallet
//wallet wallet