package semaphore;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(String customerName, double initialBalance) {
        accounts.put(customerName, new Account(customerName, initialBalance));
    }

    public Account getAccount(String customerName) {
        return accounts.get(customerName);
    }
}
