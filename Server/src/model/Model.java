package model;

import java.util.ArrayList;

public interface Model extends UnnamedPropertySubject{
    void addLog(String log);
    void addMessage(String message);
    public void countUser(int i);
    public int getNrOfUsers();

    void addUser(UserType user);
    void addBookToLibrary(Book book);
    void BorrowBook(String id);
    ArrayList<UserType> getAllUsers();
}


