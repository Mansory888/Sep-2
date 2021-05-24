package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOImpl implements BookDAO {
    private static BookDAOImpl bookDAO;

    private BookDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized BookDAOImpl getInstance() throws SQLException{
        if(bookDAO == null){
            bookDAO = new BookDAOImpl();
        }
        return bookDAO;
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=library_management_system",
                "postgres","database13");
    }

    @Override
    public void create(Book book) throws SQLException{
        try(Connection connection = getConnection()){
           PreparedStatement statement = connection.prepareStatement("INSERT INTO books(book_id,title,author,description,year,genre) VALUES (?,?,?,?,?,?);");
            statement.setString(1,book.getId());
            statement.setString(2,book.getTitle());
            statement.setString(3,book.getAuthor());
            statement.setString(4,book.getDescription());
            statement.setInt(5,book.getYearOfPublication());
            statement.setString(6,book.getGenre());
            statement.executeUpdate();
        }

    }

    @Override
    public Book readById(String id)throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Book> readAllBooks()throws SQLException {
        return null;
    }

    @Override
    public void remove(String id)throws SQLException {

    }

    @Override
    public void updateBook(String id, String title, String Author, int year, String description, String genre)throws SQLException {

    }
}
