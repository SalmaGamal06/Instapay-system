public interface BillStrategy {

    TransferBillPayments t = new TransferBillPayments();
    void pay(double amnt);


}

class ElectricBill implements BillStrategy{
    String electricMeterID;
    @Override
    public void pay(double amnt) {

        //get balance from bank check if enough to pay
        //if balance is enough then call func from bank to deduce the bill amount from balance
        // and change isPaid to true as well as sending the deducted amount to the electricity company
        //else print error message saying the balance is not enough

        t.transferMoney(this.electricMeterID,amnt);


    }
}

class GasBill implements BillStrategy {
    String gasMeterID;
    @Override
    public void pay(double amnt) {

        //get balance from bank check if enough to pay
        //if balance is enough then call func from bank to deduce the bill amount from balance
        // and change isPaid to true as well as sending the deducted amount to the Gas company
        //else print error message saying the balance is not enough

        t.transferMoney(this.gasMeterID,amnt);

    }
}

class WaterBill implements BillStrategy{
    String waterMeterID;

    @Override
    public void pay(double amnt) {

        //get balance from bank check if enough to pay
        //if balance is enough then call func from bank to deduce the bill amount from balance
        // and change isPaid to true as well as sending the deducted amount to the water company
        //else print error message saying the balance is not enough

        t.transferMoney(this.waterMeterID,amnt);


    }
}
