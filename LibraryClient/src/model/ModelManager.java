package model;

import javafx.fxml.FXML;
import mediator.LibraryClient;
import mediator.ServerModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ModelManager implements Model{
    private LibraryInventory libraryInventory;
    private UserInventory userInventory;
    private PropertyChangeSupport property;
    private ArrayList<UserType> userTypes;
    private LocalDateTime userRegistrationDate;
    private String username;
    private ServerModel serverModel;

    public ModelManager(){
        property =  new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        userInventory = new UserInventory();
        userTypes = new ArrayList<>();
        username = "";
        userRegistrationDate = LocalDateTime.now();
        serverModel = new LibraryClient("localhost", 6789, this);
    }

    @Override public void addBookToLibrary(Book book){
        libraryInventory.addBook(book);
    }

    @Override public void BorrowBook(String id){
        userInventory.addBook(libraryInventory.getBookById(id));
    }

    @Override public void addUser(UserType user){userTypes.add(user);}

    @Override public String getUsername(){return username;}

    @Override public void setUsername(String username){this.username = username;}

    @Override public String getUserRegistrationDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return userRegistrationDate.format(formatter);
    }

    @Override public void setBookReturned(String id){
        userInventory.returnedBookById(id);
    }

    @Override public boolean getIsReturned(String id){return userInventory.getBookById(id).getIsReturned();}




    public int getLibraryBooksSize(){
        return libraryInventory.getSize();
    }

    @Override public Book getLibraryBookByIndex(int index) {return libraryInventory.getBook(index);}

    @Override public LibraryInventory getLibraryInventory(){return libraryInventory;}

    @Override public Book getLibraryBookByID(String id){return libraryInventory.getBookById(id);}



    @Override public int getUserBooksSize() {return userInventory.getSize();}

    @Override public Book getUserBookByIndex(int index) {return userInventory.getBook(index);}

    @Override public UserInventory getUserInventory(){return userInventory;}

    @Override public Book getUserBookByID(String id) {return userInventory.getBookById(id);}








}
