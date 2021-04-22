package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ModelManager implements Model{
    private LibraryInventory libraryInventory;
    private UserInventory userInventory;
    private PropertyChangeSupport property;

    public ModelManager(){
        property =  new PropertyChangeSupport(this);
        libraryInventory = new LibraryInventory();
        userInventory = new UserInventory();
    }

    public void addBookToLibrary(Book book){
        libraryInventory.addBook(book);
    }

    public void BorrowBook(String id){
        userInventory.addBook(libraryInventory.getBookById(id));
    }



    public int getLibraryBooksSize(){
        return libraryInventory.getSize();
    }

    public Book getLibraryBookByIndex(int index) {return libraryInventory.getBook(index);}

    public LibraryInventory getLibraryInventory(){return libraryInventory;}

    public Book getLibraryBookByID(String id){return libraryInventory.getBookById(id);}


    public int getUserBooksSize() {return userInventory.getSize();}

    public Book getUserBookByIndex(int index) {return userInventory.getBook(index);}

    public UserInventory getUserInventory(){return userInventory;}

    public Book getUserBookByID(String id) {return userInventory.getBookById(id);}




    @Override public void addListener(PropertyChangeListener listener){
        property.addPropertyChangeListener(listener);
    }

    @Override public void removeListener(PropertyChangeListener listener){
        property.removePropertyChangeListener(listener);
    }




}
