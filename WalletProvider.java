public interface WalletProvider {
    public boolean verifyWallet(String mobileNumber);
    public double getBalance(double balance);
    public boolean checkBalance(double balance);
    public void withdrawal(double amount);
    public void deposit(double amount);

}
public abstract class VodafoneCashProvider implements WalletProvider{
    //    implements all the functions in walletprovider but in its own unique way than the other wallet providers
}

public abstract class CIBWalletProvider implements WalletProvider{
    //    implements all the functions in walletprovider but in its own unique way than the other wallet providers
}

public abstract class FawryProvider implements WalletProvider{
//    implements all the functions in walletprovider but in its own unique way than the other wallet providers
}

