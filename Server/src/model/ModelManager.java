package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ModelManager implements Model {

    private Loglist loglist;
    private PropertyChangeSupport property;
    private int nrOfUsers;

    public ModelManager(){
        loglist = Loglist.getInstance();
        this.property = new PropertyChangeSupport(this);
    }

    @Override
    public synchronized void addLog(String log)
    {
        String logValue = loglist.getLogSize() + ": " + log;
        loglist.addlog(logValue);
        property.firePropertyChange("add", null, logValue);
    }

    @Override
    public synchronized void addMessage(String message){
        property.firePropertyChange("message",null, message);
    }

    @Override public synchronized void countUser(int i){
        nrOfUsers += i;
    }

    @Override public int getNrOfUsers(){
        return nrOfUsers;
    }

    @Override public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }

}
