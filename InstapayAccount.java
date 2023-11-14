import java.util.Vector;

public abstract class InstapayAccount {
    Vector<Bill> bills = new Vector<>();
    Transfer t = new Transfer();

    public abstract double inquireBalance();
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

            //check if the payment is done successfully fist
            // can achieve by changing excutePayment return value to bool
            bills.remove(b);

        }
    }
    public void transaction(TransferStrategy s, String r, double a){
        t.setTransferStrategy(s);
        t.strategy.transferMoney(r,a);
    }

}

class BankAccount extends InstapayAccount{
    String  bankAccountNumber;
    BankService bank;

    public BankAccount(BankService bank) {
        this.bank = bank;
    }

    public boolean processBankAccountVerification(String accountNumber){
             return bank.verifyBankAccount(accountNumber);
    }
    public BankAccount() {}


    public boolean processMobileNumberVerification(String mobileNumber){
        return bank.verifyMobileNumber(mobileNumber);
    }

    @Override
    public double inquireBalance() {
        return bank.getBalance(this.bankAccountNumber);
    }
}


class WalletAccount extends InstapayAccount{
    WalletProvider wallet;

    String mobileNumber;

    public WalletAccount(WalletProvider wallet) {
        this.wallet = wallet;
    }

    public boolean processWalletVerification(String mobileNumber){
       return wallet.verifyWallet(mobileNumber);
    }
    public WalletAccount() {}
    @Override
    public double inquireBalance() {
        return wallet.getBalance(this.mobileNumber);
    }
}

