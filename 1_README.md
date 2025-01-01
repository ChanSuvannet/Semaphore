# Bank ATM System Simulation

## Overview
This project demonstrates the use of Java's `Semaphore` to simulate multiple customers accessing a limited number of ATMs concurrently. The system ensures synchronized access to the ATMs, simulating real-world concurrency management.

## Features
- **Concurrency Control**: Ensures that a fixed number of customers can use ATMs at the same time.
- **Thread Safety**: Synchronizes access to shared resources to prevent race conditions.
- **Realistic Simulation**: Models real-world scenarios with waiting times, ATM usage, and controlled access.

## How It Works

### Components
1. **ATM Class**:
   - Represents the shared ATM resource.
   - Simulates ATM usage with the `useATM` method, which includes a delay to mimic real-world processing time.

2. **Customer Class**:
   - Represents a customer as a thread.
   - Customers wait for an ATM to become available (acquiring a semaphore permit), use it, and then release it (releasing the permit).

3. **Main Program**:
   - Manages the simulation by creating multiple customer threads.
   - Uses a `Semaphore` to limit the number of concurrent ATM users.
   - Ensures all threads complete execution before finishing the program.

### Execution Flow
1. Customers are created as separate threads.
2. Each customer attempts to acquire a semaphore permit to access an ATM.
3. If a permit is available, the customer uses the ATM and holds it for a brief simulated duration.
4. After using the ATM, the customer releases the semaphore permit, making it available for others.
5. The simulation continues until all customers have completed their ATM transactions.

## How to Run

### Prerequisites
- Java Development Kit (JDK) installed.
- A Java IDE (like IntelliJ IDEA, Eclipse) or a text editor with terminal access.

### Steps
1. Copy the code into a Java file named `BankATMSimulation.java`.
2. Compile the program:
   ```bash
   javac BankATMSimulation.java
   ```
3. Run the compiled program:
   ```bash
   java BankATMSimulation
   ```

## Example Output
```
Customer 1 is waiting to use the ATM.
Customer 2 is waiting to use the ATM.
Customer 1 is using the ATM.
Customer 2 is using the ATM.
Customer 3 is waiting to use the ATM.
Customer 4 is waiting to use the ATM.
Customer 1 is done using the ATM.
Customer 1 has released the ATM.
Customer 3 is using the ATM.
Customer 2 is done using the ATM.
Customer 2 has released the ATM.
Customer 4 is using the ATM.
...
All customers are done using the ATMs.
```

## Project Structure
- **ATM Class**: Defines the ATM resource and its operations.
- **Customer Class**: Manages customer threads and their interaction with the ATM.
- **Main Program**: Coordinates the simulation.

## Future Enhancements
- Add more detailed transaction simulation (e.g., balance checks, withdrawals, deposits).
- Introduce dynamic ATM availability.
- Implement a graphical interface for better visualization of the simulation.

## Key Learnings
- **Semaphore Usage**: Managing concurrent access to limited resources.
- **Thread Synchronization**: Ensuring safe access to shared resources.
- **Java Multithreading**: Creating and managing threads for concurrent operations.

## Conclusion
This project serves as a practical example of concurrency management in Java using semaphores. It demonstrates how to handle shared resources safely and efficiently in a multithreaded environment.

## Code

### ATM Class
```java
// ATM class to simulate a shared ATM resource
class ATM {
    public void useATM(String customerName) {
        System.out.println(customerName + " is using the ATM.");
        try {
            Thread.sleep(2000); // Simulate time spent using the ATM
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(customerName + " is done using the ATM.");
    }
}
```

### Customer Class
```java
// Customer class representing a thread
class Customer extends Thread {
    private Semaphore semaphore;
    private ATM atm;
    private String customerName;

    public Customer(Semaphore semaphore, ATM atm, String customerName) {
        this.semaphore = semaphore;
        this.atm = atm;
        this.customerName = customerName;
    }

    @Override
    public void run() {
        try {
            System.out.println(customerName + " is waiting to use the ATM.");
            semaphore.acquire();
            atm.useATM(customerName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(customerName + " has released the ATM.");
            semaphore.release();
        }
    }
}
```

### Main Program
```java
import java.util.concurrent.Semaphore;

public class BankATMSimulation {
    public static void main(String[] args) {
        // Number of ATMs available
        int numberOfATMs = 2;
        Semaphore semaphore = new Semaphore(numberOfATMs);

        // Shared ATM resource
        ATM atm = new ATM();

        // Create customer threads
        Thread customer1 = new Customer(semaphore, atm, "Customer 1");
        Thread customer2 = new Customer(semaphore, atm, "Customer 2");
        Thread customer3 = new Customer(semaphore, atm, "Customer 3");
        Thread customer4 = new Customer(semaphore, atm, "Customer 4");
        Thread customer5 = new Customer(semaphore, atm, "Customer 5");

        // Start customer threads
        customer1.start();
        customer2.start();
        customer3.start();
        customer4.start();
        customer5.start();

        // Wait for all customers to finish
        try {
            customer1.join();
            customer2.join();
            customer3.join();
            customer4.join();
            customer5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All customers are done using the ATMs.");
    }
}
```


## Team Members
- **Member 1**: Chan Suvannet
- **Member 2**: CHHAY INGCHHY
- **Member 3**: HONG BUNPHENG
- **Member 4**: Chheang Bunly

