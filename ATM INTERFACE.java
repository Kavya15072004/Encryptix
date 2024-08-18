import java.util.Scanner;

// Class representing a user's bank account
class BankAccount {
    private double balance;

    // Constructor to initialize the account with an initial balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit an amount into the account
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    // Method to withdraw an amount from the account
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;
                System.out.println("Successfully withdrawn: $" + amount);
            } else {
                System.out.println("Insufficient balance. Transaction failed.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }

    // Method to check the current balance
    public double checkBalance() {
        return balance;
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;

    // Constructor to initialize the ATM with a bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Method to display the ATM options and interact with the user
    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nATM Interface:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + account.checkBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4:
                    running = false;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }

        scanner.close();
    }
}

// Main class to run the ATM program
public class ATMInterface {

    public static void main(String[] args) {
        // Initialize the user's bank account with an initial balance of $1000
        BankAccount userAccount = new BankAccount(1000.0);

        // Create an ATM instance with the user's bank account
        ATM atm = new ATM(userAccount);

        // Start the ATM interface
        atm.start();
    }
}
