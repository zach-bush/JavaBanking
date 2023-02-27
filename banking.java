import java.util.*;

public class BankingSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to the Banking System!");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thanks for using the Banking System!");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void createAccount() {
        String name = getStringInput("Enter your name: ");
        double initialBalance = getDoubleInput("Enter your initial balance: ");
        Account account = new Account(name, initialBalance);
        accounts.add(account);
        System.out.println("Account created with ID " + account.getId());
    }

    private static void depositMoney() {
        int accountId = getIntInput("Enter account ID: ");
        Account account = getAccountById(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        double amount = getDoubleInput("Enter amount to deposit: ");
        account.deposit(amount);
        System.out.println("Deposit successful. New balance is " + account.getBalance());
    }

    private static void withdrawMoney() {
        int accountId = getIntInput("Enter account ID: ");
        Account account = getAccountById(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        double amount = getDoubleInput("Enter amount to withdraw: ");
        if (amount > account.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        account.withdraw(amount);
        System.out.println("Withdrawal successful. New balance is " + account.getBalance());
    }

    private static void checkBalance() {
        int accountId = getIntInput("Enter account ID: ");
        Account account = getAccountById(accountId);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.println("Current balance is " + account.getBalance());
    }

    private static Account getAccountById(int id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.nextLine(); // clear the invalid input
            System.out.print("Invalid input. " + prompt);
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // clear the newline character
        return input;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.nextLine(); // clear the invalid input
            System.out.print("Invalid input. " + prompt);
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // clear the newline character
