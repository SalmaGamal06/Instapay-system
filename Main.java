import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        User user;
        System.out.println("---------------------------------------------------");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3- Exit");
        System.out.println("---------------------------------------------------");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();
        if (choice == 1 || choice == 2) {
            if (choice == 1) {
                Login login = new Login();
                if (!login.login()) {
                    System.out.println("Login failed");
                    return;
                }
                user = login.getUser();
            } else {
                Registration register = new Registration();
                if (!register.register()) {
                    System.out.println("Registration failed");
                    return;
                }
                user = register.getUser();
            }
            while (true) {
                System.out.println("---------------------Main Menu---------------------");
                System.out.println("1- Pay bill");
                System.out.println("2- Transfer money");
                System.out.println("3- Inquire balance");
                System.out.println("4- Exit");
                System.out.println("---------------------------------------------------");
                System.out.print("Enter your choice: ");
                int choice2 = input.nextInt();
                if (choice2 == 1) {
                    System.out.println("---------------------Pay Bill---------------------");
                    System.out.println("1- Electric bill");
                    System.out.println("2- Gas bill");
                    System.out.println("3- Water bill");
                    System.out.println("4- Back to Main menu");
                    System.out.println("5- Exit");
                    System.out.println("---------------------------------------------------");
                    System.out.print("Choose the type of bill you want to pay : ");
                    int choice3 = input.nextInt();
                    if (choice3 == 1) {
                        System.out.println("Electric bill amount: " + user.getElectricBill().getAmount());
                        System.out.println("Will you pay the bill?");
                        System.out.println("1- Yes");
                        System.out.println("2- No");
                        System.out.print("Enter your choice: ");
                        int choice4 = input.nextInt();
                        if (choice4 == 1) {
                            user.getElectricBill().payBill(user.getInstapayAccount());
                        } else {
                            System.out.println("Bill not paid");
                        }
                    } else if (choice3 == 2) {
                        System.out.println("Electric bill amount: " + user.getElectricBill().getAmount());
                        System.out.println("Will you pay the bill?");
                        System.out.println("1- Yes");
                        System.out.println("2- No");
                        System.out.print("Enter your choice: ");
                        int choice4 = input.nextInt();
                        if (choice4 == 1) {
                            user.getGasBill().payBill(user.getInstapayAccount());
                        } else {
                            System.out.println("Bill not paid");
                        }
                    } else if (choice3 == 3) {
                        System.out.println("Electric bill amount: " + user.getElectricBill().getAmount());
                        System.out.println("Will you pay the bill?");
                        System.out.println("1- Yes");
                        System.out.println("2- No");
                        System.out.print("Enter your choice: ");
                        int choice4 = input.nextInt();
                        if (choice4 == 1) {
                            user.getWaterBill().payBill(user.getInstapayAccount());
                        } else {
                            System.out.println("Bill not paid");
                        }
                    } else if (choice3 == 4) {
                        continue;
                    } else if (choice3 == 5) {
                        break;
                    } else {
                        System.out.println("Invalid choice");
                    }
                } else if (choice2 == 2) {
                    System.out.println("---------------------Transfer Money---------------------");
                    if (user.getTypeOfInstapayAccount().equals("Bank")) {
                        System.out.println("1- Transfer to another Instapay account");
                        System.out.println("2- Transfer to a wallet account");
                        System.out.println("3- Transfer to a bank account");
                    } else {
                        System.out.println("1- Transfer to another Instapay account");
                        System.out.println("2- Transfer to a wallet account");
                    }
                    System.out.println("4- Back to Main menu");
                    System.out.println("5- Exit");
                    System.out.println("---------------------------------------------------------");
                    System.out.print("Choose the type of transfer you want to do : ");
                    int choice3 = input.nextInt();
                    if (choice3 == 1) {
                        InstapayTransfer instapayTransfer = new InstapayTransfer();
                        System.out.print("Enter the username of the recipient : ");
                        String recipient = input.next();
                        System.out.print("Enter the amount you want to transfer : ");
                        double amount = input.nextDouble();
                        instapayTransfer.transferMoney(user.getInstapayAccount(), recipient, amount);
                    } else if (choice3 == 2) {
                        WalletTransfer walletTransfer = new WalletTransfer();
                        System.out.print("Enter the mobile number of the recipient : ");
                        String recipient = input.next();
                        System.out.print("Enter the amount you want to transfer : ");
                        double amount = input.nextDouble();
                        walletTransfer.transferMoney(user.getInstapayAccount(), recipient, amount);
                    } else if (choice3 == 3 && user.getTypeOfInstapayAccount().equals("Bank")) {
                        BankTransfer bankTransfer = new BankTransfer();
                        System.out.print("Enter the bank account number of the recipient : ");
                        String recipient = input.next();
                        System.out.print("Enter the amount you want to transfer : ");
                        double amount = input.nextDouble();
                        bankTransfer.transferMoney(user.getInstapayAccount(), recipient, amount);
                    } else if (choice3 == 4) {
                        continue;
                    } else if (choice3 == 5) {
                        break;
                    } else {
                        System.out.println("Invalid choice");
                    }
                } else if (choice2 == 3) {
                    System.out.println(user.getInstapayAccount().inquireBalance());
                } else if (choice2 == 4) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            }
        }
    }
}
