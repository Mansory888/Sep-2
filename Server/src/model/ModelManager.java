package model;

import javafx.fxml.FXML;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class ModelManager implements Model {

    private Loglist loglist;
    private PropertyChangeSupport property;
    private int nrOfUsers;
    private LibraryInventory libraryInventory;
    private ArrayList<UserType> users;
    private ArrayList<String> notifications;

    /**
     * Creates a model manager for the server
     */
    public ModelManager(){
        loglist = Loglist.getInstance();
        this.property = new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        users = new ArrayList<>();
        notifications = new ArrayList<>();
        libraryInventory.addBook(new Book("The Lean Startup","Eric Ries",2011, "1","The Lean Startup: How Today's Entrepreneurs Use Continuous Innovation to Create Radically Successful Businesses is a book by Eric Ries describing his proposed lean startup strategy for startup companies. " +
                "Ries developed the idea for the lean startup from his experiences as a startup advisor, employee, and founder.","Finance"));
        libraryInventory.addBook(new Book("Zero to One", "Blake Masters", 2014, "2", "Zero to One: Notes on Startups, or How to Build the Future is a 2014 book by the American entrepreneur and investor Peter Thiel co-written with Blake Masters.","Finance"));
        libraryInventory.addBook(new Book("The $100 Startup", "Chris Guillebeau", 2012, "3", "Here, Chris Guillebeau shows you how to lead a life of adventure, meaning and purpose--and earn a good living. Still in his early thirties, Chris has traveled around the world--and yet he's never held a real job","Finance"));
    }

    /**
     * A method to add logs to the log list
     * @param log logs
     */
    @Override public synchronized void addLog(String log)
    {
        String logValue = loglist.getLogSize() + ": " + log;
        loglist.addlog(logValue);
        property.firePropertyChange("add", null, logValue);
        if(notifications.size() > 100){
            notifications.clear();
        }
    }

    /**
     * A method to add message to the gui
     * @param message message
     */
    @Override public synchronized void addMessage(String message){
        property.firePropertyChange("message",null, message);
    }

    /**
     * A method to count users online
     * @param i + or - user
     */
    @Override public synchronized void countUser(int i){
        nrOfUsers += i;
    }

    /**
     * A method to get the number of users online
     * @return nr of users online
     */
    @Override public int getNrOfUsers(){
        return nrOfUsers;
    }

    /**
     * A method to return a book in a specific users library
     * @param id id of the book
     * @param username username of the user
     */
    @Override public void returnBook(String id, String username){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getUsername().equals(username)){
                users.get(i).getUserInventory().returnBookById(id);
            }
        }
    }

    /**
     * A method to borrow a book in a specific users library
     * @param id id of the book
     * @param username username of the user
     */
    @Override public void borrowBook(String id, String username){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).getUserInventory().addBook(libraryInventory.getBookById(id));
            }
        }
    }

    /**
     * Rating a book in the users inventory
     * @param rating rating
     * @param username username
     * @param id id
     */
    @Override public void rateBookInUserInventory(int rating, String username, String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                users.get(i).getUserInventory().getBookById(id).setRating(rating, username);
            }
        }
    }


    /**
     * adds a book to library inventory
     *
     * @param book book
     */
    @Override
    public void addBookToLibrary(Book book) { libraryInventory.addBook(book); }

    /**
     * adds a user to the user list
     *
     * @param user user
     */
    @Override
    public void addUser(UserType user) { users.add(user); }


    /**
     * Returns all the users
     * @return users
     */
    @Override public ArrayList<UserType> getAllUsers(){return users;}

    /**
     * Returns the library inventory
     * @return library inventory
     */
    @Override public LibraryInventory getLibraryInventory(){
        return libraryInventory;
    }

    /**
     * Returns the notifications list
     * @return notifications list
     */
    @Override public ArrayList<String> getNotifications(){return notifications;}

    /**
     * Adds a listener to the model
     * @param listener listener
     */
    @Override public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener from the model
     * @param listener listener
     */
    @Override public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }

}
