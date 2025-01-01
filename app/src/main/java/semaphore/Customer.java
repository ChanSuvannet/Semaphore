package semaphore;

import java.util.Scanner;

public class Customer extends Thread {
    private ATM atm;
    private Account account;

    public Customer(String customerName, ATM atm, Account account) {
        super(customerName); // This sets the thread name to the customer name
        this.atm = atm;
        this.account = account;
    }

    @Override
    public void run() {
        atm.useATM(this); // Use the ATM
    }

    // Synchronize method to prevent concurrent access to the Scanner
    public synchronized void performTransaction() {
        synchronized (System.in) { // Synchronize input to prevent concurrent Scanner access
            Scanner scanner = new Scanner(System.in);
            System.out.println(getName() + ", select a transaction: "); // This uses the customer name set in the thread
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: " + account.getBalance());
                    Transaction.logTransaction(getName() + " checked balance.");
                    break;
                case 2:
                    System.out.println("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    Transaction.logTransaction(getName() + " deposited " + depositAmount);
                    break;
                case 3:
                    System.out.println("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    Transaction.logTransaction(getName() + " withdrew " + withdrawalAmount);
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }
}
