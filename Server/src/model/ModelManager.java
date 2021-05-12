package model;

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

    /**
     * Creates a model manager for the server
     */
    public ModelManager(){
        loglist = Loglist.getInstance();
        this.property = new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        users = new ArrayList<>();
        users.add(new Admin("username@gmail.com","username","Username1*"));
        libraryInventory.addBook(new Book("1","a",1, "1","1"));
        libraryInventory.addBook(new Book("2", "a", 2, "2", "2"));
        libraryInventory.addBook(new Book("3", "a", 3, "3", "3"));
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
                users.get(i).getUserInventory().returnedBookById(id);
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
