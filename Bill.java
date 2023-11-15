import java.util.UUID;
import java.util.Random;

public abstract class Bill {

    BankTransfer t = new BankTransfer();

    private double amount;


    public double getAmount() {
        return amount;
    }


    public void generateRandomBill() {
        Random rand = new Random();
        this.amount = rand.nextDouble(1000);
    }
    abstract void payBill();
}


class ElectricBill extends Bill{
    String electricCompanyBankAccountNumber;
    @Override
    public void payBill() {

    }
}

class GasBill extends Bill{
    String gasCompanyBankAccountNumber;



    @Override
    public void payBill() {

        //get balance from bank check if enough to pay
        //if balance is enough then call func from bank to deduce the bill amount from balance
        // and change isPaid to true as well as sending the deducted amount to the Gas company
        //else print error message saying the balance is not enough

        //t.transferMoney(this.gasMeterID,amnt);

    }
}

class WaterBill extends Bill{
    String waterCompanyBankAccountNumber;


    @Override
    public void payBill() {

        //get balance from bank check if enough to pay
        //if balance is enough then call func from bank to deduce the bill amount from balance
        // and change isPaid to true as well as sending the deducted amount to the water company
        //else print error message saying the balance is not enough

        //t.transferMoney(this.waterMeterID,amnt);


    }
}
