

# Bank ATM System Simulation - Testing Flow
---

## Overview
This project simulates a **Bank ATM System** with multiple customers accessing limited ATMs concurrently using semaphores. The following **testing flow** describes how to test different components of the system, including **Account**, **ATM**, and **Customer** classes. It explains how to test the program manually and automatically.

## Testing Flow

### 1. **Preparation**
Before you start testing, ensure that the following steps are completed:
- All the required **Java SDK** and **JUnit** libraries are installed.
- The project has been compiled without errors.

### 2. **Start the Program**
When you run the program, the following occurs:
- The **Main Program** initializes an **ATM** and creates several **Customer** objects (threads).
- The **Customer** threads attempt to use the **ATM** concurrently.
- The **Semaphore** is used to manage access to the ATM, ensuring that only a limited number of customers can use the ATM at the same time.

**Test Manual Flow**:
1. Compile and run the program (`javac BankATMSimulation.java && java BankATMSimulation`).
2. You will observe customers competing for access to the ATM. Follow the output as customers either wait for the ATM or perform transactions (e.g., balance check, deposit, withdrawal).

### 3. **Testing the Account Class**
The **Account** class is crucial in maintaining the customer's balance during ATM transactions. Key operations include deposit, withdrawal, and balance checking.

#### Manual Testing of Account Class:
1. **Test Deposit**:
   - Run a customer transaction and deposit money into the account.
   - Verify that the account balance increases.
   - Example: Initial balance = 1000, deposit = 500 → Final balance = 1500.

2. **Test Withdrawal**:
   - Run a customer transaction and withdraw money.
   - Verify that the account balance decreases after the withdrawal.
   - Example: Initial balance = 1000, withdrawal = 300 → Final balance = 700.

3. **Test Insufficient Funds**:
   - Try to withdraw an amount greater than the current balance.
   - Ensure the withdrawal does not occur and the balance remains unchanged.

#### Automated Testing of Account Class:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testDeposit() {
        Account account = new Account(1000);
        account.deposit(500);
        assertEquals(1500, account.getBalance(), "Balance should be updated after deposit.");
    }

    @Test
    void testWithdraw() {
        Account account = new Account(1000);
        account.withdraw(300);
        assertEquals(700, account.getBalance(), "Balance should be updated after withdrawal.");
    }

    @Test
    void testInsufficientFunds() {
        Account account = new Account(1000);
        account.withdraw(1200);
        assertEquals(1000, account.getBalance(), "Balance should remain unchanged if withdrawal exceeds funds.");
    }
}
```

### 4. **Testing the ATM Class**
The **ATM** class manages customer interactions with the ATM, including performing transactions like checking balance, deposit, and withdrawal.

#### Manual Testing of ATM Class:
1. **Test ATM Usage**:
   - Ensure that when customers use the ATM, they follow a realistic pattern (i.e., a customer should wait if the ATM is busy).
   - Observe if customers are correctly queued and the ATM allows customers to complete their transaction.

2. **Test Concurrency**:
   - Run multiple customers concurrently (e.g., 3 customers, 2 ATMs) and check if the ATM properly handles concurrent access.
   - Customers should be able to use the ATM if the limit is not exceeded, and others must wait for their turn.

#### Automated Testing of ATM Class:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Semaphore;

class ATMTest {

    @Test
    void testATMUsage() {
        Account account = new Account(1000);
        ATM atm = new ATM();
        Semaphore semaphore = new Semaphore(2);  // Limit to 2 customers using ATM at a time

        Customer customer1 = new Customer("Customer 1", atm, account);
        Customer customer2 = new Customer("Customer 2", atm, account);
        Customer customer3 = new Customer("Customer 3", atm, account);

        customer1.start();
        customer2.start();
        customer3.start();

        try {
            customer1.join();
            customer2.join();
            customer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(account.getBalance() >= 0, "Balance should not be negative after transactions.");
    }
}
```

### 5. **Testing the Customer Class**
The **Customer** class represents a thread (each customer is a thread). It simulates user input for performing transactions (balance check, deposit, withdrawal).

#### Manual Testing of Customer Class:
1. **Test Customer Transaction**:
   - Simulate transactions for each customer and verify that the correct transaction takes place.
   - Verify that after a transaction, the balance reflects the change made by the customer.

2. **Test Concurrent Access**:
   - Start multiple customers concurrently and check if they can access the ATM without issues.
   - Test that if a customer is using the ATM, others must wait until the ATM is free (enforced by the **Semaphore**).

#### Automated Testing of Customer Class:
```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void testCustomerTransactions() {
        Account account = new Account(1000);
        ATM atm = new ATM();
        Semaphore semaphore = new Semaphore(2);  // Limit to 2 customers using ATM at a time

        Customer customer1 = new Customer("Customer 1", atm, account);
        Customer customer2 = new Customer("Customer 2", atm, account);

        customer1.start();
        customer2.start();

        try {
            customer1.join();
            customer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(account.getBalance() >= 0, "Balance should not be negative after transactions.");
    }
}
```

### 6. **Test Results and Reporting**
After running the tests, you'll receive a **JUnit Test Report** that shows whether the tests passed or failed. Here’s an example:

**Test Results**:
- All tests passed for the **Account** class.
- The **ATM** class correctly handles multiple customers with semaphores.
- The **Customer** class performs transactions correctly.

---

## Conclusion
This **Testing Flow** ensures that the **Bank ATM System** works correctly by validating the core components: **Account**, **ATM**, and **Customer** classes. Manual testing helps ensure the system behaves as expected in a real-world scenario, while automated unit tests provide ongoing validation for the project's future changes.

---

This **README** provides a comprehensive overview of how to test the **Bank ATM System** simulation, including the manual and automated testing steps for each major class.