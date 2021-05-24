package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BorrowedBooksDAO {
    void create(String bookId, String username) throws SQLException;
    ArrayList<Book> readAllBooks(String username) throws SQLException;
    void returnBook(String bookId,String username) throws SQLException;
}
