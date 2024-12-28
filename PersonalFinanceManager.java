package project;
import java.util.*;

class User {
    String username;
    String password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Budget {
    String name;
    double limit;
    double currentExpense;

    Budget(String name, double limit) {
        this.name = name;
        this.limit = limit;
        this.currentExpense = 0;
    }
}

class Expense {
    String category;
    double amount;

    Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }
}

public class PersonalFinanceManager {
    static Scanner scanner = new Scanner(System.in);
    static List<User> users = new ArrayList<>();
    static User loggedInUser = null;
    static List<Budget> budgets = new ArrayList<>();
    static List<Expense> expenses = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            if (loggedInUser == null) {
                System.out.println("1. Register\n2. Login\n3. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice == 1) {
                    register();
                } else if (choice == 2) {
                    login();
                } else {
                    System.out.println("Goodbye!");
                    break;
                }
            } else {
                System.out.println("\n1. Manage Budgets\n2. Manage Expenses\n3. Logout");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice == 1) {
                    manageBudgets();
                } else if (choice == 2) {
                    manageExpenses();
                } else {
                    System.out.println("Logged out.");
                    loggedInUser = null;
                }
            }
        }
    }

    static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }

    static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome " + username);
                return;
            }
        }
        System.out.println("Invalid credentials. Please try again.");
    }

    static void manageBudgets() {
        while (true) {
            System.out.println("\n1. Create Budget\n2. Update Budget\n3. Delete Budget\n4. View Budgets\n5. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter budget name: ");
                String name = scanner.nextLine();
                System.out.print("Enter budget limit: ");
                double limit = scanner.nextDouble();
                scanner.nextLine();
                budgets.add(new Budget(name, limit));
                System.out.println("Budget created!");
            } else if (choice == 2) {
                System.out.print("Enter budget name to update: ");
                String name = scanner.nextLine();
                for (Budget budget : budgets) {
                    if (budget.name.equals(name)) {
                        System.out.print("Enter new budget limit: ");
                        budget.limit = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Budget updated!");
                        break;
                    }
                }
            } else if (choice == 3) {
                System.out.print("Enter budget name to delete: ");
                String name = scanner.nextLine();
                budgets.removeIf(budget -> budget.name.equals(name));
                System.out.println("Budget deleted!");
            } else if (choice == 4) {
                for (Budget budget : budgets) {
                    System.out.println("Budget: " + budget.name + " | Limit: " + budget.limit + " | Current Expense: " + budget.currentExpense);
                }
            } else {
                break;
            }
        }
    }

    static void manageExpenses() {
        while (true) {
            System.out.println("\n1. Add Expense\n2. Edit Expense\n3. Delete Expense\n4. View Expenses\n5. Back");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter expense category: ");
                String category = scanner.nextLine();
                System.out.print("Enter expense amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                expenses.add(new Expense(category, amount));

                for (Budget budget : budgets) {
                    if (budget.name.equals(category)) {
                        budget.currentExpense += amount;
                        if (budget.currentExpense > budget.limit) {
                            System.out.println("\nWarning: Budget limit exceeded for " + budget.name);
                        }
                        break;
                    }
                }
                System.out.println("Expense added!");

            } else if (choice == 2) {
                System.out.print("Enter expense category to edit: ");
                String category = scanner.nextLine();
                for (Expense expense : expenses) {
                    if (expense.category.equals(category)) {
                        System.out.print("Enter new expense amount: ");
                        expense.amount = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.println("Expense updated!");
                        break;
                    }
                }
            } else if (choice == 3) {
                System.out.print("Enter expense category to delete: ");
                String category = scanner.nextLine();
                expenses.removeIf(expense -> expense.category.equals(category));
                System.out.println("Expense deleted!");
            } else if (choice == 4) {
                for (Expense expense : expenses) {
                    System.out.println("Category: " + expense.category + " | Amount: " + expense.amount);
                }
            } else {
                break;
            }
        }
    }
}

