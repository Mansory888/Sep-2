package model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Log {
    private String text;
    private DateTime dateTime;

    /**
     * Creates a log
     * @param text log
     */
    public Log(String text){
        this.text = text;
        this.dateTime = new DateTime();
    }

    /**
     * Returns the log text
     * @return log text
     */
    public String getText(){return text;}

    /**
     * Returns the date time
     * @return date time
     */
    public DateTime getDateTime(){return dateTime;}

    /**
     * Returns a string with the date time and the log
     * @return string with the date time and the log
     */
    @Override public String toString(){
        return text + " " + dateTime.toString();
    }
}
