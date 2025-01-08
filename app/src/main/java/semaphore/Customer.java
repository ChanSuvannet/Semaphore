package semaphore;

public class Customer extends Thread {
    private String customerName;
    private ATM atm;
    private Account account;
    private ATMSystemUI atmSystemUI;  // Reference to the ATM System UI for updates

    public Customer(String customerName, ATM atm, Account account, ATMSystemUI atmSystemUI) {
        this.customerName = customerName;
        this.atm = atm;
        this.account = account;
        this.atmSystemUI = atmSystemUI;
    }

    @Override
    public void run() {
        try {
            atmSystemUI.setCurrentCustomer(this);  // Set this customer as the current customer
            atm.useATM(this);  // The customer tries to use the ATM
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCustomerName() {
        return customerName;
    }

    public void checkBalance() {
        atmSystemUI.updateATMStatus(customerName + " is checking balance: $" + account.getBalance());
    }

    public void deposit(double amount) {
        account.deposit(amount);
        atmSystemUI.updateATMStatus(customerName + " deposited $" + amount + ". New balance: $" + account.getBalance());
    }

    public void withdraw(double amount) {
        if (account.getBalance() >= amount) {
            account.withdraw(amount);
            atmSystemUI.updateATMStatus(customerName + " withdrew $" + amount + ". New balance: $" + account.getBalance());
        } else {
            atmSystemUI.updateATMStatus(customerName + " has insufficient funds.");
        }
    }

    public void endTransaction() {
        atmSystemUI.updateATMStatus(customerName + " has ended the transaction.");
        atmSystemUI.processNextCustomer();  // Proceed to the next customer
    }
}
