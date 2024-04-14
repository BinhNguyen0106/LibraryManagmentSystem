import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static String getCurrentDate(){
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return currentDate.format(formatter);
    }

    public static String getReturnedDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate datePlusWeek = currentDate.plusWeeks(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        return datePlusWeek.format(formatter);
    }

    public static long getDaysOverdue(String returnDate){
        // Get current date
        LocalDate actualReturnDate = LocalDate.now();

        // Parse borrowing date and current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        LocalDate expectedReturnDate = LocalDate.parse(returnDate.trim(), formatter);

        // Calculate the difference in days between return date and current date
        long overdue = actualReturnDate.toEpochDay() - expectedReturnDate.toEpochDay();
        return (overdue <= 0) ? 0 : overdue;
    }
}
