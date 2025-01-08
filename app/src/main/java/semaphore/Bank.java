package semaphore;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void addAccount(String name, double initialBalance) {
        accounts.put(name, new Account(name, initialBalance));
    }

    public Account getAccount(String name) {
        return accounts.get(name);
    }
}
