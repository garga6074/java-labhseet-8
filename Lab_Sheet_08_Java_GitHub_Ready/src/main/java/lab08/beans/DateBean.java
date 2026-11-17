package lab08.beans;
import java.time.LocalDate;

/** currentDate is read-only: it has a getter and intentionally no setter. */
public class DateBean {
    public DateBean() { }
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
