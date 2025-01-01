package semaphore;

import java.util.concurrent.Semaphore;

public class BankATMSimulation {
    public static void main(String[] args) {
        // Initialize bank and ATM
        Bank bank = new Bank();
        bank.addAccount("Chan Suvannet", 3000.0);
        bank.addAccount("CHHAY INGCHHY", 1000.0);

        // Create a semaphore for ATM concurrency (2 ATMs available)
        Semaphore semaphore = new Semaphore(2);

        // Initialize ATM
        ATM atm = new ATM(semaphore);

        // Create customer threads
        Customer customer1 = new Customer("Chan Suvannet", atm, bank.getAccount("Chan Suvannet"));
        Customer customer2 = new Customer("CHHAY INGCHHY", atm, bank.getAccount("CHHAY INGCHHY"));
        Customer customer3 = new Customer("HONG BUNPHENG", atm, bank.getAccount("HONG BUNPHENG"));

        // Start customer threads sequentially
        try {
            customer1.start();
            customer1.join(); // Wait for customer1 to finish

            customer2.start();
            customer2.join(); // Wait for customer2 to finish

            customer3.start();
            customer3.join(); // Wait for customer3 to finish

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Simulation complete. Check logs for transaction details.");
    }
}
