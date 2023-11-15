public interface WalletProvider {
    public boolean verifyWallet(String mobileNumber);
    public double getBalance(String mobileNumber);
    public boolean withdrawal(String mobile,double amount);
    public boolean deposit(String mobile,double amount);

}
class VodafoneCashProvider implements WalletProvider{
    private VodafoneCash vodafoneCash;
    public VodafoneCashProvider(VodafoneCash vodafoneCash) {
        this.vodafoneCash = vodafoneCash;
    }
    @Override
    public boolean verifyWallet(String mobileNumber){

        return vodafoneCash.verifyWallet(mobileNumber);
    }
    @Override
    public double getBalance(String mobileNumber){

        return vodafoneCash.getBalance(mobileNumber);
    }
    @Override
    public boolean withdrawal(String mobile,double amount){
        return vodafoneCash.withdrawal(mobile,amount);
    }
    @Override
    public boolean deposit(String mobile,double amount){
        return vodafoneCash.deposit(mobile,amount);
    }
}

class CIBWalletProvider implements WalletProvider{
    private CIB cib;
    public CIBWalletProvider(CIB cib) {
        this.cib = cib;
    }
    @Override
    public boolean verifyWallet(String mobileNumber){

        return cib.verifyWallet(mobileNumber);
    }
    @Override
    public double getBalance(String mobileNumber){

        return cib.getBalance(mobileNumber);
    }
    @Override
    public boolean withdrawal(String mobile,double amount){
        return cib.withdrawal(mobile,amount);
    }
    @Override
    public boolean deposit(String mobile,double amount){
        return cib.deposit(mobile,amount);
    }

}

class FawryWalletProvider implements WalletProvider{
    private Fawry fawry;
    public FawryWalletProvider(Fawry fawry) {
        this.fawry = fawry;
    }
    @Override
    public boolean verifyWallet(String mobileNumber){
        return fawry.verifyWallet(mobileNumber);
    }
    @Override
    public double getBalance(String mobileNumber){

        return fawry.getBalance(mobileNumber);
    }
    @Override
    public boolean withdrawal(String mobile,double amount){
        return fawry.withdrawal(mobile,amount);
    }
    @Override
    public boolean deposit(String mobile,double amount){
        return fawry.deposit(mobile,amount);
    }

}

