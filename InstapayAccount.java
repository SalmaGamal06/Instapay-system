import java.util.Vector;

public class InstapayAccount {
    Vector<Bill> bills = new Vector<>();
    Transfer t = new Transfer();

    public double inquireBalance(){

        double balance = 0;
        //request balance firm bank/wallet provider and return the balance
        return balance;
    }
    public void addBill(BillStrategy s){
        Bill b = new Bill();
        b.setBillStrategy(s);
        b.createRandomBill();
        bills.add(b);
    }
    private Bill findBillById(String targetBillId) {
        for (Bill bill : bills)
            if (bill.getBillID().equals(targetBillId)) {
                return bill;
            }
        return null;
    }
    public void payBill(String id){
        Bill b = findBillById(id);
        if(b == null){
            System.out.println("Bill not found");
        }
        else if(bills.contains(b)){
            b.excutePayment();
            bills.remove(b);
        }
    }
    public void transaction(TransferStrategy s, String r, double a){
        t.setTransferStrategy(s);
        t.strategy.transferMoney(r,a);
    }

}

