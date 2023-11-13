public interface WalletProvider {
    public boolean verifyWallet(String mobileNumber);
    public double getBalance(String mobileNumber);
    public boolean checkBalance(String mobileNumber,double amount);
    public void withdrawal(double amount);
    public void deposit(double amount);

}
abstract class VodafoneCashProvider implements WalletProvider{
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
    public boolean checkBalance(String mobileNumber,double amount) {
        return vodafoneCash.checkBalance(mobileNumber,amount);
    }
    public void withdrawal(double amount){
        vodafoneCash.withdrawal(amount);
    }
    public void deposit(double amount){
        vodafoneCash.deposit(amount);
    }
}

abstract class CIBWalletProvider implements WalletProvider{
    //    implements all the functions in walletprovider but in its own unique way than the other wallet providers
}

abstract class FawryProvider implements WalletProvider{
//    implements all the functions in walletprovider but in its own unique way than the other wallet providers
}

