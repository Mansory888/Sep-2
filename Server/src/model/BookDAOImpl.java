package model;

import java.sql.*;
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
        return DriverManager.getConnection("jdbc:postgresql://pg-870e174-nicoric-963b.aivencloud.com:15475/defaultdb?currentSchema=library_management_system",
                "avnadmin","wby0old272aqkhsk");
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
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books where book_id = ?");
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int yearOfPublication = resultSet.getInt("year");
                String genre = resultSet.getString("genre");
                return new Book(title,author,yearOfPublication,id,description,genre);
            }else{
                return null;
            }
        }
    }

    @Override
    public ArrayList<Book> readAllBooks()throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Books");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> books = new ArrayList<>();
            while (resultSet.next()){
                String bookId = resultSet.getString("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int yearOfPublication = resultSet.getInt("year");
                String genre = resultSet.getString("genre");
                books.add(new Book(title,author,yearOfPublication,bookId,description,genre));
            }
            return books;
        }
    }

    @Override
    public void remove(String id)throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM books WHERE book_id = ?");
            statement.setString(1,id);
            statement.executeUpdate();

        }
    }

    @Override
    public void updateBook(String id, String title, String author, int year, String description, String genre)throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE books SET title = ?, author = ?, description = ?, " +
                    "year = ?, genre = ? WHERE book_id = ? ");
            statement.setString(1,title);
            statement.setString(2, author);
            statement.setString(3,description);
            statement.setInt(4,year);
            statement.setString(5,genre);
            statement.setString(6,id);
            statement.executeUpdate();

        }
    }
}
