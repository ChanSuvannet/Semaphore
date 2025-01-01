package semaphore;

import java.util.concurrent.Semaphore;

public class ATM {
    private Semaphore semaphore;

    public ATM(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void useATM(Customer customer) {
        try {
            semaphore.acquire();  // Acquire a permit to use the ATM
            System.out.println(customer.getName() + " is using the ATM.");
            customer.performTransaction();  // Customer performs a transaction
            Thread.sleep(1000);  // Sleep for a second to allow smooth sequence
            System.out.println(customer.getName() + " has finished using the ATM.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();  // Release the permit after using the ATM
        }
    }
}
