import java.io.FileNotFoundException;
import java.util.Random;

public abstract class Bill {
    public Bill() {
        generateRandomBill();
    }

    String CompanyBankAccountNumber;
    BankTransfer t = new BankTransfer();
    protected double amount;

    public double getAmount() {
        return amount;
    }

    public double generateRandomBill() {
        Random rand = new Random();
        this.amount = Math.round(rand.nextDouble(1000) * 100.0) / 100.0;
        return amount;
    }

    abstract void payBill(User s) throws FileNotFoundException;

    public void updateBillAmount() {
        this.amount += generateRandomBill();
    }
}

class ElectricBill extends Bill {
    String CompanyBankAccountNumber = "123456789";
    BankService bank = new NEBService(new NEBAPI());

    @Override
    public void payBill(User s) throws FileNotFoundException {
        if (amount > 0) {
            t.transferMoney(s, bank, CompanyBankAccountNumber, amount);
            System.out.println("Electric bill paid successfully, amount paid: " + amount);
            amount = 0;
        } else {
            System.out.println("No bills to pay");
        }
    }
}

class GasBill extends Bill {
    String CompanyBankAccountNumber = "1234567810";
    BankService bank = new QNBService(new QNBAPI());

    @Override
    public void payBill(User s) throws FileNotFoundException {
        if (amount > 0) {
            t.transferMoney(s, bank, CompanyBankAccountNumber, amount);
            System.out.println("Electric bill paid successfully, amount paid: " + amount);
            amount = 0;
        } else {
            System.out.println("No bills to pay");
        }
    }
}

class WaterBill extends Bill {
    String CompanyBankAccountNumber = "1234567811";
    BankService bank = new QNBService(new QNBAPI());

    @Override
    public void payBill(User s) throws FileNotFoundException {
        if (amount > 0) {
            t.transferMoney(s, bank, CompanyBankAccountNumber, amount);
            System.out.println("Electric bill paid successfully, amount paid: " + amount);
            amount = 0;
        } else {
            System.out.println("No bills to pay");
        }
    }
}
