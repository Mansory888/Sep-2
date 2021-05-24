package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookDAO {
    void create(Book book) throws SQLException;
    Book readById(String id) throws SQLException;
    ArrayList<Book> readAllBooks() throws SQLException;
    void remove(String id) throws SQLException;
    void updateBook(String id,String title,String author,int year,String description,String genre) throws SQLException;
}
