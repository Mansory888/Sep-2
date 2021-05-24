package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BorrowedBooksDAOImpl implements BorrowedBooksDAO {
    private static BorrowedBooksDAOImpl borrowedBooksDAO;
    private BorrowedBooksDAOImpl() throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }
    public static  synchronized BorrowedBooksDAOImpl getInstance() throws SQLException{
        if(borrowedBooksDAO==null){
            borrowedBooksDAO = new BorrowedBooksDAOImpl();
        }
        return borrowedBooksDAO;

    }
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:postgresql://pg-870e174-nicoric-963b.aivencloud.com:15475/defaultdb?currentSchema=library_management_system",
                "avnadmin","wby0old272aqkhsk");
    }
    @Override
    public void create(String bookId, String username) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO borrowed_books(date_borrowed, date_returned, status, username, book_id) VALUES (?,?,?,?,?);");
            preparedStatement.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(2,null);
            preparedStatement.setString(3,"borrowed");
            preparedStatement.setString(4,username);
            preparedStatement.setString(5,bookId);
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public ArrayList<Book> readAllBooks(String username) throws SQLException {

           try(Connection connection = getConnection()){
               PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM borrowed_books JOIN books on borrowed_books.book_id = books.book_id where username= ?");
               preparedStatement.setString(1,username);
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
    public void returnBook(String bookId, String username) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE borrowed_books SET status = ?, date_returned = ? WHERE username = ? AND book_id = ?");
            preparedStatement.setString(1,"borrowed");
            preparedStatement.setDate(2,java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.setString(3,username);
            preparedStatement.setString(4,bookId);

            preparedStatement.executeUpdate();
        }

    }
}
