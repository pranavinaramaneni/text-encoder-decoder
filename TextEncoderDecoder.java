import java.util.Scanner;

public class TextEncoderDecoder {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Basic Text Encoder and Decoder ===");
        int shift = getShiftFromUser();
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = readIntSafely("Enter your choice: ");
            switch (choice) {
                case 1: encodeFlow(shift); break;
                case 2: decodeFlow(shift); break;
                case 3: shift = getShiftFromUser(); break;
                case 4: System.out.println("Current shift value: " + (shift % 26 + 26) % 26); break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from the menu.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        System.out.println(" 1) Encode a message");
        System.out.println(" 2) Decode a message");
        System.out.println(" 3) Change/reset shift value");
        System.out.println(" 4) Show current shift value");
        System.out.println(" 5) Exit");
    }

    private static void encodeFlow(int shift) {
        scanner.nextLine(); // consume leftover newline
        while (true) {
            System.out.print("Enter message to ENCODE (or press Enter to go back): ");
            String message = scanner.nextLine();
            if (message.isEmpty()) break;
            System.out.println("Encoded output: " + encode(message, shift));
        }
    }

    private static void decodeFlow(int shift) {
        scanner.nextLine(); // consume leftover newline
        while (true) {
            System.out.print("Enter message to DECODE (or press Enter to go back): ");
            String message = scanner.nextLine();
            if (message.isEmpty()) break;
            System.out.println("Decoded output: " + decode(message, shift));
        }
    }

    private static int getShiftFromUser() {
        int rawShift = readIntSafely("Enter shift value: ");
        int normalized = ((rawShift % 26) + 26) % 26;
        System.out.println("Using shift: " + normalized);
        return rawShift;
    }

    public static String encode(String input, int shift) {
        if (input == null || input.isEmpty()) return input;
        int s = ((shift % 26) + 26) % 26;
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append((char) ('A' + (c - 'A' + s) % 26));
            } else if (Character.isLowerCase(c)) {
                sb.append((char) ('a' + (c - 'a' + s) % 26));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String decode(String input, int shift) {
        if (input == null || input.isEmpty()) return input;
        int s = ((shift % 26) + 26) % 26;
        int back = (26 - s) % 26;
        return encode(input, back);
    }

    private static int readIntSafely(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer. Try again.");
            }
        }
    }
}
