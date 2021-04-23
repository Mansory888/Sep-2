package model;

import com.sun.management.VMOption;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ModelManager implements Model{
    private LibraryInventory libraryInventory;
    private UserInventory userInventory;
    private PropertyChangeSupport property;
    private ArrayList<UserType> userTypes;
    private LocalDateTime userRegistrationDate;
    private String username;

    public ModelManager(){
        property =  new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        userInventory = new UserInventory();
        userTypes = new ArrayList<>();
        username = "";
        userRegistrationDate = LocalDateTime.now();
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




    @Override public void addListener(PropertyChangeListener listener){
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener){
        property.removePropertyChangeListener(listener);
    }




}
