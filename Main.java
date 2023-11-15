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
                            user.getElectricBill().payBill(user);
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
                            user.getGasBill().payBill(user);
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
                            user.getWaterBill().payBill(user);
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
                        FileStorage file = new FileStorage();
                        User recipientUser = file.read(recipient);
                        System.out.print("Enter the amount you want to transfer : ");
                        double amount = input.nextDouble();
                        if (recipientUser.getProvider().equals("NEB")) {
                            instapayTransfer.transferMoney(user, new NEBService(new NEBAPI()), recipient, amount);
                        } else if (recipientUser.getProvider().equals("QNB")) {
                            instapayTransfer.transferMoney(user, new QNBService(new QNBAPI()), recipient, amount);
                        } else if (recipientUser.getProvider().equals("AAIB")) {
                            instapayTransfer.transferMoney(user, new AAIBService(new AAIBAPI()), recipient, amount);
                        } else if (recipientUser.getProvider().equals("Vodafone")) {
                            instapayTransfer.transferMoney(user, new VodafoneCashProvider(new VodafoneCash()), recipient, amount);
                        } else if (recipientUser.getProvider().equals("CIB")) {
                            instapayTransfer.transferMoney(user, new CIBWalletProvider(new CIB()), recipient, amount);
                        } else if (recipientUser.getProvider().equals("Fawry")) {
                            instapayTransfer.transferMoney(user, new FawryWalletProvider(new Fawry()), recipient, amount);
                        }
                    } else if (choice3 == 2) {
                        WalletTransfer walletTransfer = new WalletTransfer();
                        System.out.println("1- Vodafone Cash");
                        System.out.println("2- CIB Wallet");
                        System.out.println("3- Fawry Wallet");
                        System.out.println("enter the type of wallet you want to transfer to : ");
                        String choice4 = input.next();
                        System.out.print("Enter the mobile number of the recipient : ");
                        String recipient = input.next();
                        System.out.print("Enter the amount you want to transfer : ");
                        double amount = input.nextDouble();
                        if (choice4.equals("Vodafone")) {
                            walletTransfer.transferMoney(user, new VodafoneCashProvider(new VodafoneCash()), recipient, amount);
                        } else if (choice4.equals("CIB")) {
                            walletTransfer.transferMoney(user, new CIBWalletProvider(new CIB()), recipient, amount);
                        } else if (choice4.equals("Fawry")) {
                            walletTransfer.transferMoney(user, new FawryWalletProvider(new Fawry()), recipient, amount);
                        }
                    } else if (choice3 == 3 && user.getTypeOfInstapayAccount().equals("Bank")) {
                        BankTransfer bankTransfer = new BankTransfer();
                        System.out.println("1- NEB");
                        System.out.println("2- QNB");
                        System.out.println("3- AAIB");
                        System.out.println("enter the type of bank you want to transfer to : ");
                        String choice4 = input.next();
                        System.out.print("Enter the bank account number of the recipient : ");
                        String recipient = input.next();
                        System.out.print("Enter the amount you want to transfer : ");
                        double amount = input.nextDouble();
                        if (choice4.equals("NEB")) {
                            bankTransfer.transferMoney(user, new NEBService(new NEBAPI()), recipient, amount);
                        } else if (choice4.equals("QNB")) {
                            bankTransfer.transferMoney(user, new QNBService(new QNBAPI()), recipient, amount);
                        } else if (choice4.equals("AAIB")) {
                            bankTransfer.transferMoney(user, new AAIBService(new AAIBAPI()), recipient, amount);
                        }
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
