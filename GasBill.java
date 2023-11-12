public class GasBill implements BillStrategy {
    String gasMeterID;
    @Override
    public void pay() {

        //get balance from bank check if enough to pay
        //if balance is enough then call func from bank to deduce the bill amount from balance
        // and change isPaid to true as well as sending the deducted amount to the Gas company
        //else print error message saying the balance is not enough

    }
}
