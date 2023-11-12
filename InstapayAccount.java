public interface InstapayAccount {
//    private Bills[] bills
//    public void setTransfer(Transfer transfer){};
    public double inquireBalance();
    public void transaction(String recipient,double amount);

}
