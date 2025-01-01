package semaphore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    public static void logTransaction(String message) {
        try {
            // Ensure the logs folder exists
            File logFolder = new File("logs");
            if (!logFolder.exists()) {
                logFolder.mkdirs(); // Create the folder if it does not exist
            }

            FileWriter fileWriter = new FileWriter("logs/transaction_logs.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            printWriter.println(timestamp + " - " + message);

            printWriter.close();
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage()); // Print error to console
        }
    }

}
