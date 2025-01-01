package semaphore;

public class Account {
    private String accountHolderName;
    private double balance;

    public Account(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(accountHolderName + " successfully withdrew " + amount);
            return true;
        } else {
            System.out.println(accountHolderName + " has insufficient funds for withdrawal.");
            return false;
        }
    }

    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println(accountHolderName + " successfully deposited " + amount);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }
}
