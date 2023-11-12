import java.util.Properties;
import java.util.Random;

public class OTP {

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
        // Return the OTP as a string
        return otp.toString();
    }

    public void sendOTP(String userEmail, String OTP) {
       System.out.println("Your OTP is: " + generateOTP());
    }

    public boolean verifyOTP() {
        System.out.print("Enter the OTP you received: ");
        String userOTP = System.console().readLine();
        if (userOTP.equals(generateOTP())) {
            System.out.println("OTP verified successfully!");
            return true;
        } else {
            System.out.println("OTP verification failed!");
            return false;
        }
    }
}