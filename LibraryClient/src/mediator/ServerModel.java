package mediator;

import model.Book;
import model.UserType;


public interface ServerModel {
    boolean Login(String username, String password);
    void Register(UserType User);
    void addBookToServerLibrary(Book book);
    void borrowBook(String id);
    void returnBook(String id);
    void removeBook(String id);
}
