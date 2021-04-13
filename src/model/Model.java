package model;

import Utility.UnnamedPropertySubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertySubject {
    void addBookToLibrary(Book book);
    void BorrowBook(String id);

    int getLibraryBooksSize();
    Book getLibraryBookByIndex(int index);

    int getUserBooksSize();
    Book getUserBookByIndex(int index);
}
