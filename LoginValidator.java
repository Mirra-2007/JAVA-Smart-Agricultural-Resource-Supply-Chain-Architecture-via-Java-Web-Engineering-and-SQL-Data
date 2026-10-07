import java.util.Scanner;

public class LoginValidator {

    /**
     * Validates if the given StringBuffer input is a valid Gmail address using regex.
     * 
     * @param emailBuffer StringBuffer containing the user input email
     * @return true if valid Gmail address, false otherwise
     */
    public static boolean isValidGmail(StringBuffer emailBuffer) {
        if (emailBuffer == null) {
            return false;
        }

        // Convert StringBuffer to String to use Regex matching
        String email = emailBuffer.toString().trim();

        // Regex pattern: ensures non-empty username followed by @gmail.com (case-insensitive)
        String gmailRegex = "(?i)^[A-Za-z0-9._%+-]+@gmail\\.com$";

        return email.matches(gmailRegex);
    }

    /**
     * Alternative check: Directly checks if StringBuffer ends with @gmail.com
     * without full regex matching.
     * 
     * @param emailBuffer StringBuffer containing the user input email
     * @return true if it ends with @gmail.com and has a username prefix
     */
    public static boolean matchesGmailEnding(StringBuffer emailBuffer) {
        if (emailBuffer == null) {
            return false;
        }

        String domain = "@gmail.com";
        int bufferLength = emailBuffer.length();
        int domainLength = domain.length();

        // Must be longer than "@gmail.com" to ensure there is a username before @
        if (bufferLength <= domainLength) {
            return false;
        }

        // Extract the ending portion of StringBuffer
        String tail = emailBuffer.substring(bufferLength - domainLength);
        return tail.equalsIgnoreCase(domain);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("   FRESH FARMS - Login Email Validator   ");
        System.out.println("=========================================");
        
        System.out.print("Enter your email address: ");
        String userInput = scanner.nextLine();

        // Storing the input into a StringBuffer as requested
        StringBuffer emailBuffer = new StringBuffer(userInput);

        // Perform validation
        boolean isValid = isValidGmail(emailBuffer);

        System.out.println("\n-----------------------------------------");
        if (isValid) {
            System.out.println(" SUCCESS: Access Granted! Valid Gmail address.");
        } else {
            System.out.println(" ERROR: Access Denied! Only @gmail.com accounts are allowed.");
        }
        System.out.println("-----------------------------------------");

        scanner.close();
    }
}