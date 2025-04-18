import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) {
        try {
            File file = new File("wGraph1");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Read line: " + line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - wGraph1");
            e.printStackTrace(); // Print the stack trace for more details
        }
    }
}