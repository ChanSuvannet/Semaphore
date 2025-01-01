package semaphore;

public class ATM {

    public String getGreeting() {
        return "Welcome to the ATM!";
    }

    public void useATM(Customer customer) {
        System.out.println(customer.getName() + " is using the ATM.");
        customer.performTransaction();
    }
}
