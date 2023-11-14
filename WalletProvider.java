public interface WalletProvider {
    public boolean verifyWallet(String mobileNumber);
    public double getBalance(String mobileNumber);
    public void withdrawal(double amount);
    public void deposit(double amount);

}
class VodafoneCashProvider implements WalletProvider{
    private VodafoneCash vodafoneCash;
    @Override
    public boolean verifyWallet(String mobileNumber){

        return vodafoneCash.verifyWallet(mobileNumber);
    }
    @Override
    public double getBalance(String mobileNumber){

        return vodafoneCash.getBalance(mobileNumber);
    }
    @Override
    public void withdrawal(double amount){
        vodafoneCash.withdrawal(amount);
    }
    @Override
    public void deposit(double amount){
        vodafoneCash.deposit(amount);
    }
}

class CIBWalletProvider implements WalletProvider{
    private CIB cib;
    @Override
    public boolean verifyWallet(String mobileNumber){

        return cib.verifyWallet(mobileNumber);
    }
    @Override
    public double getBalance(String mobileNumber){

        return cib.getBalance(mobileNumber);
    }
    @Override
    public void withdrawal(double amount){
        cib.withdrawal(amount);
    }
    @Override
    public void deposit(double amount){
        cib.deposit(amount);
    }

}

abstract class FawryWalletProvider implements WalletProvider{
    private Fawry fawry;
    @Override
    public boolean verifyWallet(String mobileNumber){
        return fawry.verifyWallet(mobileNumber);
    }
    @Override
    public double getBalance(String mobileNumber){

        return fawry.getBalance(mobileNumber);
    }
    @Override
    public void withdrawal(double amount){
        fawry.withdrawal(amount);
    }
    @Override
    public void deposit(double amount){
        fawry.deposit(amount);
    }

}

