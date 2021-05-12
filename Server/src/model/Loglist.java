package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Loglist {

    private ArrayList<Log> list;
    private static Loglist loglist;
    private static Object lock = new Object();


    /**
     * Creates a Log lis with the logs
     */
    private  Loglist(){
        this.list = new ArrayList<>();
    }

    /**
     * Returns the log list instance
     * @return log list instance
     */
    public static Loglist getInstance(){
        if(loglist==null){
            synchronized (lock){
                if(loglist==null){
                    loglist = new Loglist();
                }
            }
        }
        return loglist;
    }

    /**
     * Adds a log to the list
     * @param txt text
     */
    public void addlog(String txt){
        Log logLine = new Log(txt);
        list.add(logLine);
        addToFile(logLine);
    }

    /**
     * Returns the log list size
     * @return log list size
     */
    public int getLogSize() {
        return list.size();
    }

    /**
     * Adds the logs to a txt file
     * @param log logs
     */
    private void addToFile(Log log)
    {
        if (log == null)
        {
            return;
        }
        BufferedWriter out = null;
        try
        {
            String filename = "Log-"
                    + log.getDateTime().getSortableDate() + ".txt";
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        }
        catch (Exception e) {e.printStackTrace();}
        finally
        {
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
