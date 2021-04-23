package model;

public class Log {
    private String text;
    private DateTime dateTime;

    public Log(String text){
        this.text = text;
        this.dateTime = new DateTime();
    }

    public String getText(){return text;}

    public DateTime getDateTime(){return dateTime;}

    @Override public String toString(){
        return text + " " + dateTime.toString();
    }
}
