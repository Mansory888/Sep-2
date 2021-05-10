package model;

import javafx.beans.binding.ObjectBinding;
import javafx.fxml.FXML;
import mediator.LibraryClient;
import mediator.ServerModel;

import java.beans.PropertyChangeListener;
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
    private Object user;
    private boolean verifyLogin;
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
        verifyLogin = false;
        user = null;
        ErrorLabel="";
    }

    /**
     * adds a book to library inventory
     * @param book book
     */
    @Override public void addBookToLibrary(Book book){
        libraryInventory.addBook(book);
    }
    @Override
    public Object getUser() {
        return user;
    }
    @Override
    public void setUser(Object user) {
        this.user = user;
    }

    /**
     * Borrows a book from library by id
     * @param id id
     */
    @Override public void BorrowBook(String id){
        userInventory.addBook(libraryInventory.getBookById(id));
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
        userInventory.returnedBookById(id);
    }

    /**
     * returns if a book is returned
     * @param id id
     * @return is returned
     */
    @Override public boolean getIsReturned(String id){return userInventory.getBookById(id).getIsReturned();}

    @Override public boolean Login(String username, String password){

        return serverModel.Login(username, password);
    }

    @Override public void Register(UserType user){
        serverModel.Register(user);
    }

    @Override public boolean getVerifyLogin(){
        return verifyLogin;
    }

    @Override public void setVerifyLogin(boolean value){
        verifyLogin = value;
    }

    @Override public String getErrorLabel(){return ErrorLabel;}

    @Override public void setErrorLabel(String label){ErrorLabel = label;}

    @Override public void addBookToServerLibrary(Book book){
        serverModel.addBookToServerLibrary(book);
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
