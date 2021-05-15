package model;

import mediator.LibraryClient;
import mediator.ServerModel;

import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class ModelManager implements Model{
    private LibraryInventory libraryInventory;
    private UserInventory userInventory;
    private PropertyChangeSupport property;
    private ArrayList<UserType> userTypes;
    private LocalDateTime userRegistrationDate;
    private String username;
    private ServerModel serverModel;
    private UserType user;
    private String ErrorLabel;

    /**
     *  Creates a model manager
     */
    public ModelManager(){
        property =  new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        userInventory = new UserInventory();
        userTypes = new ArrayList<>();
        username = "";
        userRegistrationDate = LocalDateTime.now();
        serverModel = new LibraryClient("localhost", 6789, this);
        user = null;
        ErrorLabel="";
    }

    /**
     * adds a book to library inventory
     * @param book book
     */
    @Override public void addBookToLibrary(Book book){
        libraryInventory.addBook(book);
        serverModel.addBookToServerLibrary(book);
    }

    /**
     * Returns user
     * @return user
     */
    @Override public UserType getUser() {
        return user;
    }

    /**
     * A method to set admin
     * @param user user
     */
    @Override public void setAdmin(Admin user) {
        this.user = user;
    }

    /**
     * A method to set customer
     * @param user user
     */
    @Override public void setCustomer(Customer user){
        this.user=user;
    }

    /**
     * Borrows a book from library by id
     * @param id id
     */
    @Override public void BorrowBook(String id){
        userInventory.addBook(libraryInventory.getBookById(id));
        serverModel.borrowBook(id);
    }

    /**
     * adds a user to the user list
     * @param user user
     */
    @Override public void addUser(UserType user){userTypes.add(user);}

    /**
     * gets the username of the user that has logged in
     */
    @Override public String getUsername(){return username;}

    /**
     * sets the username when the user is logged in
     */
    @Override public void setUsername(String username){
        this.username=username;
    }

    /**
     * gets the registration date of a registered user
     * @return date
     */
    @Override public String getUserRegistrationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return userRegistrationDate.format(formatter);
    }

    /**
     * sets a book as returned by id
     * @param id id
     */
    @Override public void setBookReturned(String id){
        userInventory.returnBookById(id);
        serverModel.returnBook(id);
    }

    /**
     * returns if a book is returned
     * @param id id
     * @return is returned
     */
    @Override public boolean getIsReturned(String id){return userInventory.getBookById(id).getIsReturned();}

    /**
     * A method to verify login
     * @param username username
     * @param password password
     * @return login verification
     */
    @Override public boolean Login(String username, String password){
        return serverModel.Login(username, password);
    }

    /**
     * A method to register
     * @param user user
     */
    @Override public void Register(UserType user){
        serverModel.Register(user);
    }

    /**
     * A method to get error Label
     * @return error label
     */
    @Override public String getErrorLabel(){return ErrorLabel;}

    /**
     * A method to set the error Label
     * @param label error label
     */
    @Override public void setErrorLabel(String label){ErrorLabel = label;}

    /**
     * Returns if its admin or no
     * @return true of false
     */
    @Override public boolean isAdmin(){ return user.isAdmin();}

    /**
     * Returns server model
     * @return server model
     */
    @Override public ServerModel getServerModel(){return serverModel;}

    /**
     * A method to load books to library from server
     * @param book book
     */
    @Override public void loadBooksToLibrary(Book book){
        libraryInventory.addBook(book);
    }

    /**
     * A method to returns books by id
     * @param id id
     */
    @Override public void returnBook(String id){
        userInventory.returnBookById(id);
        serverModel.returnBook(id);
    }

    /**
     * gets library books size
     * @return size
     */
    public int getLibraryBooksSize(){
        return libraryInventory.getSize();
    }

    /**
     * gets a library book by index
     * @param index index
     * @return book
     */
    @Override public Book getLibraryBookByIndex(int index) {return libraryInventory.getBook(index);}

    /**
     * gets the whole library inventory
     * @return library inventory
     */
    @Override public LibraryInventory getLibraryInventory(){return libraryInventory;}

    /**
     * gets library book by id
     * @param id id
     * @return book
     */
    @Override public Book getLibraryBookByID(String id){return libraryInventory.getBookById(id);}


    /**
     *  get user inventory books size
     * @return size
     */
    @Override public int getUserBooksSize() {return userInventory.getSize();}

    /**
     *  get book by index in user inventory
     * @param index index
     * @return book
     */
    @Override public Book getUserBookByIndex(int index) {return userInventory.getBook(index);}

    /**
     *  get the whole user inventory
     * @return user inventory
     */
    @Override public UserInventory getUserInventory(){return userInventory;}

    /**
     * get book by id in user inventory
     * @param id id
     * @return book
     */
    @Override public Book getUserBookByID(String id) {return userInventory.getBookById(id);}








}
