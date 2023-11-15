import java.io.FileNotFoundException;
import java.util.Random;

public abstract class Bill {
    public Bill() {
        generateRandomBill();
    }

    String CompanyBankAccountNumber;
    String bank;
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

    abstract void payBill(InstapayAccount s) throws FileNotFoundException;

    public void updateBillAmount() {
        this.amount += generateRandomBill();
    }
}

class ElectricBill extends Bill {
    String CompanyBankAccountNumber = "123456789";
    String bank = "NEB";

    @Override
    public void payBill(InstapayAccount s) throws FileNotFoundException {
        t.transferMoney(s, CompanyBankAccountNumber, amount);
        System.out.println("Electric bill paid successfully, amount paid: " + amount);
        amount = 0;
    }
}

class GasBill extends Bill {
    String CompanyBankAccountNumber = "1234567810";
    String bank = "QNB";

    @Override
    public void payBill(InstapayAccount s) throws FileNotFoundException {
        t.transferMoney(s, CompanyBankAccountNumber, amount);
        System.out.println("Electric bill paid successfully, amount paid: " + amount);
        amount = 0;
    }
}

class WaterBill extends Bill {
    String CompanyBankAccountNumber = "1234567811";
    String bank = "AAIB";

    @Override
    public void payBill(InstapayAccount s) throws FileNotFoundException {
        t.transferMoney(s, CompanyBankAccountNumber, amount);
        System.out.println("Electric bill paid successfully, amount paid: " + amount);
        amount = 0;
    }
}
