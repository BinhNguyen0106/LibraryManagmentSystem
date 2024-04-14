
public class Book extends Library{

    private String bookName;
    private String author;
    private String publisher;
    private String publishDate;
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean _available) {
        isAvailable = _available;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

     public void setPublishDate(String date) {
        this.publishDate = date;
    }
    public Book() {
    }
    @Override
    public String toString() {
        return String.format("name = '%s', author = '%s', publisher = '%s', date = '%s', available = %b",
                bookName, author, publisher, publishDate, isAvailable);
   }


}
