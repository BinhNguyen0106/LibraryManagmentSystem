import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private final List<Book> books = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    private final List<BorrowingRecords> borrowingRecords = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
        System.out.println(book);
    }

    public void removeBook(String bookName) {
        boolean removed = books.removeIf(book -> book.getBookName().equals(bookName));
        if (removed) {
            System.out.println(String.format("Book %s removed successfully!", bookName));
        } else {
            System.out.println(String.format("Book %s not found.", bookName));
        }
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("--- Display All Books ---");
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully!");
        System.out.println(user);
    }

    public void removeUser(int userId) {
        boolean removed = users.removeIf(user -> user.getUserId() == userId);
        if (removed) {
            System.out.println(String.format("User id %s removed successfully!", userId));
        } else {
            System.out.println(String.format("User id %s not found.", userId));
        }
    }

    public void displayAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered");
        } else {
            System.out.println("--- Display All Users ---");
            for (User user : users) {
                System.out.println(user.toString());
            }
        }
    }

    public void handleBookMenu(Scanner scanner) {
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "a":
                addBook(getBookDetails(scanner));
                break;
            case "b":
                System.out.print("Enter the name of the book to remove: ");
                String bookName = scanner.nextLine();
                removeBook(bookName);
                break;
            case "c":
                displayAllBooks();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void handleUserMenu(Scanner scanner) {
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "a":
                addUser(getUserDetails(scanner));
                break;
            case "b":
                System.out.print("Enter the ID of the user to remove: ");
                int userId = Integer.parseInt(scanner.nextLine());
                removeUser(userId);
                break;
            case "c":
                displayAllUsers();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private Book getBookDetails(Scanner scanner) {
        Book book = new Book();
        System.out.print("Enter Book Name: ");
        book.setBookName(scanner.nextLine());
        System.out.print("Enter Author: ");
        book.setAuthor(scanner.nextLine());
        System.out.print("Enter Publisher: ");
        book.setPublisher(scanner.nextLine());
        System.out.print("Enter Date (DD-MMM-YYYY): ");
        book.setPublishDate(scanner.nextLine());
        book.setAvailable(true);
        return book;
    }

    private User getUserDetails(Scanner scanner) {
        User user = new User();
        System.out.print("Enter User Name: ");
        user.setUserName(scanner.nextLine());
        System.out.print("Enter User ID: ");
        user.setUserId(Integer.parseInt(scanner.nextLine()));
        return user;
    }

    public boolean isBookAvailable(Book bk){
        return bk.isAvailable();
    }

    private Book findBook(String bookName) {
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                return book;
            }
        }
        return null;
    }

    private User findUser(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void borrowBook(Scanner scanner) {
        System.out.print("Enter the book name to borrow: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter user ID: ");
        int userId = Integer.parseInt(scanner.nextLine());

        Book book = findBook(bookName);
        User user = findUser(userId);
        BorrowingRecords record = new BorrowingRecords();
        if (book != null && user != null) {
            if (isBookAvailable(book)) {
                record.setBook(book);
                record.setUser(user);
                record.setBorrowDate(Utils.getCurrentDate());
                record.setReturnDate(Utils.getReturnedDate());
                record.setFine(Constants.DEFAULT_FINE_VALUE);
                book.setAvailable(false);
                borrowingRecords.add(record);
                System.out.println("Book borrowed successfully.");
            } else {
                for (BorrowingRecords rcrd : borrowingRecords){
                    if (rcrd.getBook().equals(book)){
                        System.out.println(String.format("Book is not available. Please come back after %s",
                                rcrd.getReturnDate()));
                    }
                }
            }
        } else {
            System.out.println("Book or user not found. Please check your input.");
        }
    }

    public void searchBook(Scanner scanner) {
        System.out.println("Enter book information");
        String str = scanner.nextLine();
        int count = 0;
        for (Book book : books) {
            if (book.getBookName().equalsIgnoreCase(str)
                    || book.getAuthor().equalsIgnoreCase(str)
                    || book.getPublisher().equalsIgnoreCase(str) ) {
                System.out.println(book.toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Book not found");
        }
    }

    private BorrowingRecords findBorrowingRecord(String bookName, int userId) {
        for (BorrowingRecords record : borrowingRecords) {
            if (record.getBook().getBookName().equalsIgnoreCase(bookName) && record.getUser().getUserId() == userId) {
                return record;
            }
        }
        return null;
    }

    private double calculateFine(BorrowingRecords record) {
        long daysOverdue = Utils.getDaysOverdue(record.getReturnDate());
        return Constants.FINE_RATE_PER_DAY * daysOverdue;
    }

    public void returnBook(Scanner scanner) {
        System.out.println("--- Returning a Book ---");
        System.out.print("Enter the name of the book to return: ");
        String bookName = scanner.nextLine();
        Book book = findBook(bookName);

        System.out.print("Enter the user ID who borrowed the book: ");
        int userId = Integer.parseInt(scanner.nextLine());

        BorrowingRecords record = findBorrowingRecord(bookName, userId);
        if (record != null) {

            // Calculate fine (if any)
             record.setFine(calculateFine(record));

            // Remove the book from the user's borrowing records
            borrowingRecords.remove(record);
            book.setAvailable(true);
            System.out.println("Book returned successfully.");
            System.out.println("Fine: $" + record.getFine());
        } else {
            System.out.println("No borrowing record found.");
        }
    }
}
