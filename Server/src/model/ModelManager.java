package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model {

    private Loglist loglist;
    private PropertyChangeSupport property;
    private int nrOfUsers;
    private LibraryInventory libraryInventory;
    private UserInventory userInventory;
    private ArrayList<UserType> users;

    public ModelManager(){
        loglist = Loglist.getInstance();
        this.property = new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        userInventory = new UserInventory();
        users = new ArrayList<>();
        users.add(new Admin("username@gmail.com","username","Username1*"));
        libraryInventory.addBook(new Book("1","a",1, "1","1"));
        libraryInventory.addBook(new Book("2", "a", 2, "2", "2"));
        libraryInventory.addBook(new Book("3", "a", 3, "3", "3"));
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
     * Borrows a book from library by id
     *
     * @param id id
     */
    @Override
    public void BorrowBook(String id) { userInventory.addBook(libraryInventory.getBookById(id)); }

    @Override public ArrayList<UserType> getAllUsers(){return users;}

    @Override public LibraryInventory getLibraryInventory(){
        return libraryInventory;
    }

    @Override public UserInventory getUserInventory() {return userInventory;}






    @Override public void addListener(PropertyChangeListener listener)
    {
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener)
    {
        property.removePropertyChangeListener(listener);
    }

}
