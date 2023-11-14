import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User {
    private String userName;
    private String password;
    private String mobileNumber;
    private InstapayAccount instapayAccount;
    private String provider;

    public User(String userName, String password, String mobileNumber, String accountType, String provider) {
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        setInstapayAccount(accountType,provider);
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
        if(instapayAccount instanceof BankAccount){return "Bank";}
            return "Wallet";
    }
    public void setInstapayAccountAndProvider(String accountType,String provider) {
        if(accountType.equals("Bank")){
            if(provider.equals("NEB")) {
                this.instapayAccount = new BankAccount(new NEBService());
            }
            else if(provider.equals("QNB")){
                this.instapayAccount = new BankAccount(new QNBService());
            }
        }
        else if(accountType.equals("Wallet")){
            if(provider.equals("Vodafone")){
//                this.instapayAccount = new WalletAccount(new VodafoneCashProvider());
            }
            else if(provider.equals("CIB")){
//                this.instapayAccount = new WalletAccount(new CIBWalletProvider());
            }
            else if(provider.equals("Fawry")){
//                this.instapayAccount = new WalletAccount(new FawryProvider());
            }
            this.instapayAccount = new WalletAccount();
        }
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

class Registration{
    private OTP otp;
    private User user;
    private FileStorage file;
    private Validation validation;

    public void register() throws IOException {
        System.out.println("-----------------Registration-----------------");
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String userName =scanner.nextLine();
        validation=new UserNameValidation();
        if(validation.isValid(userName)){
            file=new FileStorage();
            if(!file.isUserNameExist(userName)){
                System.out.print("Enter your password: ");
                String password =scanner.nextLine();
                validation=new PasswordValidation();
                if(validation.isValid(password)){
                    System.out.print("Enter your mobile number: ");
                    String mobileNumber =scanner.nextLine();
                    validation=new MobileNumberValidation();
                    if(validation.isValid(mobileNumber)){
                        if(!file.isMobileNumberExist(mobileNumber)){
                            otp=new OTP();
                            otp.sendOTP();
                            if(!otp.verifyOTP()){
                                System.out.println("Registration failed, Try again later");
                                return;
                            }
                            System.out.print("Enter your account type (Bank/Wallet): ");
                            String accountType =scanner.nextLine();
                            if(accountType.equals("Bank") || accountType.equals("Wallet")){
                                user=new User(userName,password,mobileNumber,accountType);
                                file.add(user);
                                System.out.println("Registration done successfully");

                            }
                            else{
                                System.out.println("Invalid account type");
                                while(true){
                                    System.out.print("Enter your account type (Bank/Wallet): ");
                                    accountType =scanner.nextLine();
                                    if(accountType.equals("Bank") || accountType.equals("Wallet")){
                                        user=new User(userName,password,mobileNumber,accountType);
                                        file.add(user);
                                        System.out.println("Registration done successfully");
                                        break;
                                    }
                                    else{
                                        System.out.println("Invalid account type");
                                    }
                                }
                            }
                        }
                        else{
                            System.out.println("Mobile number is already exist");
                            System.out.println("Registration failed, Try again later");
                        }
                    }
                    else{
                        System.out.println("Invalid mobile number");
                        System.out.println("Registration failed, Try again later");
                    }
                }
                else{
                    System.out.println("Invalid password");
                    System.out.println("Registration failed, Try again later");
                }
            }
            else{
                System.out.println("Username is already exist");
                System.out.println("Registration failed, Try again later");
            }
        }
        else{
            System.out.println("Invalid username");
            System.out.println("Registration failed, Try again later");
        }
    }


}
//Template Method Design Pattern
abstract class Validation{
    public boolean isValid(String input){
        String userNameRegex = getRegexExpression();
        Pattern pattern = Pattern.compile(userNameRegex);
        return pattern.matcher(input).matches();
    }
    public abstract String getRegexExpression();
}
class UserNameValidation extends Validation{
    @Override
    public String getRegexExpression() {
        return "^[a-zA-Z0-9_]{5,}$";
    }
}
class PasswordValidation extends Validation{
    @Override
    public String getRegexExpression() {
        return "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    }
}
class MobileNumberValidation extends Validation{
    @Override
    public String getRegexExpression() {
        return "^(010|011|012|015)[0-9]{8}$";
    }
}
class Login{
    private FileStorage file;

    public void login() throws IOException {
        System.out.println("-----------------Login-----------------");
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String userName =scanner.nextLine();
        System.out.print("Enter your password: ");
        String password =scanner.nextLine();
        file=new FileStorage();
        if(file.isUserNameExist(userName)){
            User user=file.read(userName);
            if(user.getPassword().equals(password)){
                System.out.println("Login done successfully");
                System.out.println("Welcome "+user.getUserName());
            }
            else{
                System.out.println("Invalid password");
                System.out.println("Login failed, Try again later");
            }
        }
        else{
            System.out.println("Invalid username");
            System.out.println("Login failed, Try again later");
        }
    }
}