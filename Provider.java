public interface Provider {
    public abstract boolean verify(String accountNumber);

    public abstract double getBalance(String accountNumber);

    public abstract boolean withdrawal(String accountNumber, double amount);

    public abstract boolean deposit(String accountNumber, double amount);
}
