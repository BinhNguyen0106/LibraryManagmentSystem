public class Menu {
    public static void displayMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Book Management");
        System.out.println("2. User Management");
        System.out.println("3. Borrow");
        System.out.println("4. Return");
        System.out.println("5. Search");
        System.out.println("6. Exit");
    }

    public static void displayBookMenu() {
        System.out.println("\n--- Book Management ---");
        System.out.println("a. Add new book");
        System.out.println("b. Remove book");
        System.out.println("c. Display all books");
    }

    public static void displayUserMenu() {
        System.out.println("\n--- User Management ---");
        System.out.println("a. Add new user");
        System.out.println("b. Remove user");
        System.out.println("c. Display all users");
    }
}
