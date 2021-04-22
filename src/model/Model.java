package model;

import Utility.UnnamedPropertySubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertySubject {
    void addBookToLibrary(Book book);
    void BorrowBook(String id);

    public void addUser(UserType user);

    int getLibraryBooksSize();
    Book getLibraryBookByIndex(int index);
    LibraryInventory getLibraryInventory();
    Book getLibraryBookByID(String id);

    int getUserBooksSize();
    Book getUserBookByIndex(int index);
    UserInventory getUserInventory();
    Book getUserBookByID(String id);
}
