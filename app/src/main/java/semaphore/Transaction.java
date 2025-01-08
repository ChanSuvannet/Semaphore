package semaphore;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    public static void logTransaction(String message) {
        try (FileWriter writer = new FileWriter("transaction_logs.txt", true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            writer.write(timestamp + " - " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
