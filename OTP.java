import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class OTP {

    private String otp;
    public String generateOTP() {

        // Characters allowed in the OTP
        String allowedChars = "0123456789";

        // Random object
        Random random = new Random();

        // StringBuilder to store the OTP
        StringBuilder otp = new StringBuilder();

        // Generate random OTP
        for (int i = 0; i < 6; i++) {

            int index = random.nextInt(allowedChars.length());
            otp.append(allowedChars.charAt(index));
        }
        this.otp = otp.toString();
        // Return the OTP as a string
        return otp.toString();
    }

    public void sendOTP() {
        String otp = generateOTP();
        System.out.println("Your OTP is: " + otp);
    }

    public boolean verifyOTP() {
        System.out.print("Enter the OTP you received: ");
        Scanner scanner = new Scanner(System.in);
        String userOTP = scanner.nextLine();
        if (userOTP.equals(this.otp)) {
            System.out.println("OTP verified successfully!");
            return true;
        } else {
            System.out.println("OTP verification failed!");
            return false;
        }
    }
}