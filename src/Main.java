import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Menu.displayMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Menu.displayBookMenu();
                    library.handleBookMenu(scanner);
                    break;
                case "2":
                    Menu.displayUserMenu();
                    library.handleUserMenu(scanner);
                    break;
                case "3":
                    library.borrowBook(scanner);
                    break;
                case "4":
                    library.returnBook(scanner);
                    break;
                case "5":
                    library.searchBook(scanner);
                    break;
                case "6":
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}