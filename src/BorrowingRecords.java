public class BorrowingRecords {
    private Book book;
    private User user;
    private String borrowDate;
    private String returnDate;
    private double fine;

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public BorrowingRecords() {
    }

    @Override
    public String toString() {
        return String.format("book = '%s', user = '%s', borrowingDate = '%s', returnDate = '%s', fine = %.2f",
                book, user, borrowDate, returnDate, fine);
    }
}
