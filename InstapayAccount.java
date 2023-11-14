import java.util.Vector;

public abstract class InstapayAccount {
    //figure out a way to fill the vector :)
    Vector<Bill> bills = new Vector<>(3) ;
    Bill wb = new WaterBill();
    Bill gb = new GasBill();
    Bill eb = new ElectricBill();

//    Transfer t = new Transfer();

    public abstract double inquireBalance();

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

    public BankService getBankService(){
        return bank;
    }

    @Override
    public double inquireBalance() {
        return bank.getBalance(this.bankAccountNumber);
    }



}


class WalletAccount extends InstapayAccount{
    private WalletProvider wallet;
    String PhoneNumber;
    public boolean processWalletVerification(String mobileNumber){return true;}

    @Override
    public double inquireBalance() {
        return wallet.getBalance(this.PhoneNumber);
    }
    public WalletProvider getWallet(){
        return wallet;
    }
}

