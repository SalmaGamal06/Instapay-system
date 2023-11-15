import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User {
    private String userName;
    private String password;
    private String mobileNumber;
    private InstapayAccount instapayAccount;
    private BankAccount bankAccount;
    private WalletAccount walletAccount;
    private String provider;
    private String BankAccountNumber;

    ArrayList<Bill> bills = new ArrayList<Bill>() {{
        add(new WaterBill());
        add(new GasBill());
        add(new ElectricBill());
    }};

    public Bill getWaterBill() {
        return bills.get(0);
    }

    public Bill getGasBill() {
        return bills.get(1);
    }

    public Bill getElectricBill() {
        return bills.get(2);
    }

    public User(String userName, String password, String mobileNumber, String accountType, String provider, String BankAccountNumber) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.provider = provider;
        if (accountType.equals("Bank")) {
            if (provider.equals("NEB")) {
                this.instapayAccount = new BankAccount(new NEBService(new NEBAPI()), BankAccountNumber);
                this.bankAccount = new BankAccount(new NEBService(new NEBAPI()), BankAccountNumber);
            } else if (provider.equals("QNB")) {
                this.instapayAccount = new BankAccount(new QNBService(new QNBAPI()), BankAccountNumber);
                this.bankAccount = new BankAccount(new QNBService(new QNBAPI()), BankAccountNumber);
            } else if (provider.equals("AAIB")) {
                this.instapayAccount = new BankAccount(new AAIBService(new AAIBAPI()), BankAccountNumber);
                this.bankAccount = new BankAccount(new AAIBService(new AAIBAPI()), BankAccountNumber);
            }
        } else if (accountType.equals("Wallet")) {
            setBankAccountNumber("0");
            if (provider.equals("Vodafone")) {
                this.instapayAccount = new WalletAccount(new VodafoneCashProvider(new VodafoneCash()), mobileNumber);
                this.walletAccount = new WalletAccount(new VodafoneCashProvider(new VodafoneCash()), mobileNumber);
            } else if (provider.equals("CIB")) {
                this.instapayAccount = new WalletAccount(new CIBWalletProvider(new CIB()), mobileNumber);
                this.walletAccount = new WalletAccount(new CIBWalletProvider(new CIB()), mobileNumber);
            } else if (provider.equals("Fawry")) {
                this.instapayAccount = new WalletAccount(new FawryWalletProvider(new Fawry()), mobileNumber);
                this.walletAccount = new WalletAccount(new FawryWalletProvider(new Fawry()), mobileNumber);
            }
        }
    }

    public BankService getBankService() {
        if (provider.equals("NEB")) {
            return new NEBService(new NEBAPI());
        } else if (provider.equals("QNB")) {
            return new QNBService(new QNBAPI());
        } else if (provider.equals("AAIB")) {
            return new AAIBService(new AAIBAPI());
        }
        return null;
    }

    public WalletProvider getWalletProvider() {
        if (provider.equals("Vodafone")) {
            return new VodafoneCashProvider(new VodafoneCash());
        } else if (provider.equals("CIB")) {
            return new CIBWalletProvider(new CIB());
        } else if (provider.equals("Fawry")) {
            return new FawryWalletProvider(new Fawry());
        }
        return null;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getTypeOfInstapayAccount() {
        if (instapayAccount instanceof BankAccount) {
            return "Bank";
        }
        return "Wallet";
    }

    public InstapayAccount getInstapayAccount() {
        if (instapayAccount instanceof BankAccount) {
            return instapayAccount;
        }
        return instapayAccount;
    }

    public String getProvider() {
        return provider;
    }

    public String getBankAccountNumber() {
        if (instapayAccount instanceof BankAccount) {
            return BankAccountNumber;
        }
        return "0";
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public WalletAccount getWalletAccount() {
        return walletAccount;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        BankAccountNumber = bankAccountNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}

class Registration {
    private OTP otp;
    private User user;
    private FileStorage file;
    private Validation validation;

    public boolean register() throws IOException {
        System.out.println("-----------------Registration-----------------");
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        validation = new UserNameValidation();
        if (validation.isValid(userName)) {
            file = new FileStorage();
            if (!file.isUserNameExist(userName)) {
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();
                validation = new PasswordValidation();
                if (validation.isValid(password)) {
                    System.out.print("Enter your mobile number: ");
                    String mobileNumber = scanner.nextLine();
                    validation = new MobileNumberValidation();
                    if (validation.isValid(mobileNumber)) {
                        if (!file.isMobileNumberExist(mobileNumber)) {
                            otp = new OTP();
                            otp.sendOTP();
                            if (!otp.verifyOTP()) {
                                System.out.println("Registration failed, Try again later");
                                return false;
                            }
                            System.out.print("Enter your account type (Bank/Wallet): ");
                            String accountType = scanner.nextLine();
                            if (accountType.equals("Bank") || accountType.equals("Wallet")) {
                                System.out.print("Enter your provider : ");
                                String provider = scanner.nextLine();
                                while (!provider.equals("NEB") && !provider.equals("QNB") && !provider.equals("AAIB") && !provider.equals("Vodafone") && !provider.equals("CIB") && !provider.equals("Fawry")) {
                                    System.out.println("Invalid provider");
                                    System.out.print("Enter your provider : ");
                                    provider = scanner.nextLine();
                                }
                                if (accountType.equals("Bank")) {
                                    System.out.print("Enter your Account Number : ");
                                    String accountNumber = scanner.nextLine();
                                    user = new User(userName, password, mobileNumber, accountType, provider, accountNumber);
                                    if (user.getBankAccount().processMobileVerification(accountNumber, user.getMobileNumber())) {
                                        file.add(user);
                                    } else {
                                        System.out.println("Invalid account number");
                                        System.out.println("Registration failed, Try again later");
                                        return false;
                                    }
                                } else {
                                    user = new User(userName, password, mobileNumber, accountType, provider, "0");
                                    if (user.getWalletAccount().processWalletVerification(user.getMobileNumber())) {
                                        file.add(user);
                                    } else {
                                        System.out.println("Invalid Wallet");
                                        System.out.println("Registration failed, Try again later");
                                        return false;
                                    }
                                }
                                System.out.println("Registration done successfully");
                                return true;
                            } else {
                                System.out.println("Invalid account type");
                                while (true) {
                                    System.out.print("Enter your account type (Bank/Wallet): ");
                                    accountType = scanner.nextLine();
                                    if (accountType.equals("Bank") || accountType.equals("Wallet")) {
                                        System.out.print("Enter your provider : ");
                                        String provider = scanner.nextLine();
                                        while (!provider.equals("NEB") && !provider.equals("QNB") && !provider.equals("AAIB") && !provider.equals("Vodafone") && !provider.equals("CIB") && !provider.equals("Fawry")) {
                                            System.out.println("Invalid provider");
                                            System.out.print("Enter your provider : ");
                                            provider = scanner.nextLine();
                                        }
                                        if (accountType.equals("Bank")) {
                                            System.out.print("Enter your Account Number : ");
                                            String accountNumber = scanner.nextLine();
                                            user = new User(userName, password, mobileNumber, accountType, provider, accountNumber);
                                            if (user.getBankAccount().processMobileVerification(accountNumber, user.getMobileNumber())) {
                                                file.add(user);
                                            } else {
                                                System.out.println("Invalid account number");
                                                System.out.println("Registration failed, Try again later");
                                                return false;
                                            }
                                        } else {
                                            user = new User(userName, password, mobileNumber, accountType, provider, "0");
                                            if (user.getWalletAccount().processWalletVerification(user.getMobileNumber())) {
                                                file.add(user);
                                            } else {
                                                System.out.println("Invalid Wallet");
                                                System.out.println("Registration failed, Try again later");
                                                return false;
                                            }
                                        }
                                        System.out.println("Registration done successfully");
                                        return true;
                                    } else {
                                        System.out.println("Invalid account type");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Mobile number is already exist");
                            System.out.println("Registration failed, Try again later");
                        }
                    } else {
                        System.out.println("Invalid mobile number");
                        System.out.println("Registration failed, Try again later");
                    }
                } else {
                    System.out.println("Invalid password");
                    System.out.println("Registration failed, Try again later");
                }
            } else {
                System.out.println("Username is already exist");
                System.out.println("Registration failed, Try again later");
            }
        } else {
            System.out.println("Invalid username");
            System.out.println("Registration failed, Try again later");
        }
        return false;
    }

    public User getUser() {
        return user;
    }
}

//Template Method Design Pattern
abstract class Validation {
    public boolean isValid(String input) {
        String userNameRegex = getRegexExpression();
        Pattern pattern = Pattern.compile(userNameRegex);
        return pattern.matcher(input).matches();
    }

    public abstract String getRegexExpression();
}

class UserNameValidation extends Validation {
    @Override
    public String getRegexExpression() {
        return "^[a-zA-Z0-9_]{5,}$";
    }
}

class PasswordValidation extends Validation {
    @Override
    public String getRegexExpression() {
        return "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    }
}

class MobileNumberValidation extends Validation {
    @Override
    public String getRegexExpression() {
        return "^(010|011|012|015)[0-9]{8}$";
    }
}

class Login {
    private FileStorage file;
    User user;

    public boolean login() throws IOException {
        System.out.println("-----------------Login-----------------");
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        file = new FileStorage();
        if (file.isUserNameExist(userName)) {
            user = file.read(userName);
            if (user.getPassword().equals(password)) {
                System.out.println("Login done successfully");
                System.out.println("Welcome " + user.getUserName());
                return true;
            } else {
                System.out.println("Invalid password");
                System.out.println("Login failed, Try again later");
            }
        } else {
            System.out.println("Invalid username");
            System.out.println("Login failed, Try again later");
        }
        return false;
    }

    public User getUser() {
        return user;
    }
}