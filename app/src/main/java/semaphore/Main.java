package semaphore;

public class Main {
    public static void main(String[] args) {
        // Create a bank instance and accounts
        Bank bank = new Bank();
        bank.addAccount("Chan Suvannet", 1000);
        bank.addAccount("CHHAY INGCHHY", 500);
        bank.addAccount("HONG BUNPHENG", 300);
        bank.addAccount("Chheang Bunly", 200);

        // Create an ATM instance (only 1 ATM available)
        ATM atm = new ATM(1);

        // Create the ATMSystemUI instance
        ATMSystemUI atmSystemUI = new ATMSystemUI(bank, atm);

        // Create customer threads for Chan Suvannet, CHHAY INGCHHY, HONG BUNPHENG, and Chheang Bunly
        Customer chanSuvannet = new Customer("Chan Suvannet", atm, bank.getAccount("Chan Suvannet"), atmSystemUI);
        Customer chhayIngchhy = new Customer("CHHAY INGCHHY", atm, bank.getAccount("CHHAY INGCHHY"), atmSystemUI);
        Customer hongBunpheng = new Customer("HONG BUNPHENG", atm, bank.getAccount("HONG BUNPHENG"), atmSystemUI);
        Customer chheangBunly = new Customer("Chheang Bunly", atm, bank.getAccount("Chheang Bunly"), atmSystemUI);

        // Start each customer thread
        chanSuvannet.start();
        chhayIngchhy.start();
        hongBunpheng.start();
        chheangBunly.start();

        // Wait for each customer to finish before proceeding (join the threads)
        try {
            chanSuvannet.join();  // Wait for Chan Suvannet to finish
            chhayIngchhy.join();  // Wait for CHHAY INGCHHY to finish
            hongBunpheng.join();  // Wait for HONG BUNPHENG to finish
            chheangBunly.join();  // Wait for Chheang Bunly to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Once all customers finish, print a message
        System.out.println("All customers have finished using the ATM.");
    }
}
