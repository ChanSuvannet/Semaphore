package semaphore;

import java.util.concurrent.Semaphore;

public class ATM {
    private Semaphore semaphore;
    private String currentUser;

    public ATM(int permits) {
        this.semaphore = new Semaphore(permits);  // Create the semaphore with 1 permit (for one customer at a time)
        this.currentUser = "No one is using the ATM";
    }

    public synchronized void useATM(Customer customer) {
        try {
            semaphore.acquire();  // Acquire a permit (block other customers if ATM is in use)
            currentUser = customer.getCustomerName();  // Set the current user
            currentUser = "No one is using the ATM";  // Reset after transaction
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();  // Release the permit (allow the next customer to use the ATM)
        }
    }

    public String getCurrentUser() {
        return currentUser;  // Get the current user
    }
}
