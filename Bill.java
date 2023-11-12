import java.util.UUID;
import java.util.Random;

public class Bill {
    private double amount;
    private boolean isPaid;
    private String billID;
    private BillStrategy strategy;

    public double getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getBillID() {
        return billID;
    }

    private static String generateRandomBillId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 8);
    }

    private static double generateRandomAmount() {
        Random random = new Random();
        return 10.0 + (10.0 - 500.0) * random.nextDouble();
    }

    public Bill createRandomBill() {
        double amnt = generateRandomAmount();
        String id = generateRandomBillId();
        Bill bill = new Bill();
        bill.amount = amnt;
        bill.billID = id;
        return bill;
    }

    public void setBillStrategy(BillStrategy strategy) {
        this.strategy = strategy;
    }

    public void excutePayment() {
        strategy.pay();
    }


}
