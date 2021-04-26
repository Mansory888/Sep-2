package model;


public interface Model {
    void addBookToLibrary(Book book);
    void BorrowBook(String id);

    void addUser(UserType user);
    String getUsername();
    void setUsername(String username);
    String getUserRegistrationDate();
    public void setBookReturned(String id);
    public boolean getIsReturned(String id);


    int getLibraryBooksSize();
    Book getLibraryBookByIndex(int index);
    LibraryInventory getLibraryInventory();
    Book getLibraryBookByID(String id);

    int getUserBooksSize();
    Book getUserBookByIndex(int index);
    UserInventory getUserInventory();
    Book getUserBookByID(String id);
}
