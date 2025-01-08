package semaphore;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class ATMSystemUI {
    private Bank bank;
    private ATM atm;
    private DefaultListModel<String> model;
    private Queue<Customer> waitingQueue;  // Queue of customers to wait their turn for ATM
    private Customer currentCustomer;  // The customer currently using the ATM

    public ATMSystemUI(Bank bank, ATM atm) {
        this.bank = bank;
        this.atm = atm;
        this.waitingQueue = new LinkedList<>();
        createUI();
        initializeCustomers();  // Automatically initialize customers when system starts
    }

    public void createUI() {
        JFrame frame = new JFrame("ATM System");
        JPanel panel = new JPanel();

        // Layout setup
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create the list model to display the status
        model = new DefaultListModel<>();
        model.addElement("No one is using the ATM");

        // Create a JList to show the ATM status
        JList<String> list = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(list);
        panel.add(scrollPane);

        // Add buttons for selecting options
        JButton checkBalanceButton = new JButton("Check Balance");
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton endTransactionButton = new JButton("End Transaction");

        // Button actions
        checkBalanceButton.addActionListener(e -> {
            if (currentCustomer != null) {
                currentCustomer.checkBalance();
            }
        });

        depositButton.addActionListener(e -> {
            if (currentCustomer != null) {
                String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
                double amount = Double.parseDouble(amountStr);
                currentCustomer.deposit(amount);
            }
        });

        withdrawButton.addActionListener(e -> {
            if (currentCustomer != null) {
                String amountStr = JOptionPane.showInputDialog("Enter withdrawal amount:");
                double amount = Double.parseDouble(amountStr);
                currentCustomer.withdraw(amount);
            }
        });

        endTransactionButton.addActionListener(e -> {
            if (currentCustomer != null) {
                currentCustomer.endTransaction();
                processNextCustomer(); // Automatically move to the next customer after transaction ends
            }
        });

        panel.add(checkBalanceButton);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(endTransactionButton);

        frame.add(panel);
        frame.setSize(300, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Automatically initialize customers when the system starts
    private void initializeCustomers() {
        // You can define customer names or load them dynamically from your database
        String[] customerNames = {"Chan Suvannet", "CHHAY INGCHHY", "HONG BUNPHENG", "Chheang Bunly"};

        for (String name : customerNames) {
            Account account = bank.getAccount(name);
            if (account != null) {
                Customer customer = new Customer(name, atm, account, this);
                waitingQueue.add(customer);  // Add customer to the queue
            }
        }

        // Start processing the first customer
        processNextCustomer();
    }

    // Method to process the next customer automatically
    public void processNextCustomer() {
        if (!waitingQueue.isEmpty()) {
            currentCustomer = waitingQueue.poll();  // Get the next customer in the queue
            model.set(0, currentCustomer.getCustomerName() + " is using the ATM.");
            atm.useATM(currentCustomer);  // Let the current customer use the ATM
        } else {
            model.set(0, "All customers have finished using the ATM.");
        }
    }

    // Update the UI status (called from the Customer class after a transaction is completed)
    public void updateATMStatus(String status) {
        SwingUtilities.invokeLater(() -> model.addElement(status));
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }
}
