package model;


import mediator.ServerModel;
import utility.UnnamedPropertySubject;

public interface Model extends UnnamedPropertySubject {
    void addBookToLibrary(Book book);
    void BorrowBook(String id);

    void addUser(UserType user);
    String getUsername();
    void setUsername(String username);
    String getUserRegistrationDate();
    public void setBookReturned(String id);
    public boolean getIsReturned(String id);
    boolean Login(String username, String password);
    void Register(UserType User);
    UserType getUser();
    boolean isAdmin();
    ServerModel getServerModel();
    void loadBooksToLibrary(Book book);
    void returnBook(String id);
    void fireProperty(String event, String message);

    String getErrorLabel();
    void setErrorLabel(String label);



    int getLibraryBooksSize();
    Book getLibraryBookByIndex(int index);
    LibraryInventory getLibraryInventory();
    Book getLibraryBookByID(String id);

    int getUserBooksSize();
    Book getUserBookByIndex(int index);
    UserInventory getUserInventory();
    Book getUserBookByID(String id);
    void setAdmin(Admin user);
    void setCustomer(Customer user);
}
