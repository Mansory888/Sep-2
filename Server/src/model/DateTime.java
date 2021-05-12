package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class DateTime
{
    private LocalDateTime time;

    /**
     * Creates a Date Time class
     */
    public DateTime() {this.time = LocalDateTime.now();}

    /**
     * sets the time to the local time
     * @param time time
     */
    public DateTime(LocalDateTime time) {this.time = time;}

    /**
     * Returns the time stamp
     * @return time stamp
     */
    public String getTimestamp()
    {
        DateTimeFormatter dtf;
        dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return time.format(dtf);
    }

    /**
     * Returns the Sortable Date
     * @return sortable date
     */
    public String getSortableDate()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return time.format(dtf);
    }

    /**
     * Returns the time as a string
     * @return string
     */
    @Override public String toString() {return getTimestamp();}
}