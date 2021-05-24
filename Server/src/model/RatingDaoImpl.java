package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class RatingDaoImpl implements RatingsDAO{
    private static RatingDaoImpl ratingDao;

    private RatingDaoImpl() throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized RatingDaoImpl getInstance() throws SQLException{
        if(ratingDao==null){
            ratingDao = new RatingDaoImpl();
        }
        return ratingDao;
    }

    /**
     * Returns the connection to the database
     *
     * @return connection to the database
     * @throws SQLException SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://pg-870e174-nicoric-963b.aivencloud.com:15475/defaultdb?currentSchema=library_management_system",
                "avnadmin", "wby0old272aqkhsk");
    }



    @Override public void createRating(int rating, String username, String id) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book_ratings(username, book_id, rating) VALUES (?,?,?);"
            );
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,id);
            preparedStatement.setInt(3,rating);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void edit(Rating rating) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE book_ratings(username, book_id, rating) VALUES (?,?,?);"
            );
        }

    }

    @Override public ArrayList<Rating> getAllRatings() throws SQLException{
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM book_ratings"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Rating> ratings = new ArrayList<>();
            while (resultSet.next()) {
               ratings.add(new Rating(resultSet.getString("username"),
                       resultSet.getInt("rating"),resultSet.getString("book_id")));
            }
            return ratings;
        }
    }

}
