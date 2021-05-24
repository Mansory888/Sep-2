package model;

import javafx.fxml.FXML;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class UserDAOImpl implements UserDAO{
    private static UserDAOImpl userDAO;

    /**
     * Creates a user database implementation
     * @throws SQLException SQLException
     */
    private UserDAOImpl() throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    /**
     * Returns the UserDAOImpl instance
     * @return log UserDAOImpl instance
     * @throws SQLException SQLException
     */
    public static synchronized UserDAOImpl getInstance() throws SQLException{
        if(userDAO == null){
            userDAO = new UserDAOImpl();
        }
        return userDAO;
    }

    /**
     * Returns the connection to the database
     * @return connection to the database
     * @throws SQLException SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://pg-870e174-nicoric-963b.aivencloud.com:15475/defaultdb?currentSchema=library_management_system",
                "avnadmin", "wby0old272aqkhsk");
    }

    /**
     * A method to create a user in the database
     * @param userType usertype
     * @throws SQLException SQLException
     */
    @Override public void create(UserType userType) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users(username,email,password,isAdmin) VALUES (?,?,?,?);"
            );
            preparedStatement.setString(1, userType.getUsername());
            preparedStatement.setString(2, userType.getEmail());
            preparedStatement.setString(3, userType.getPassword());
            preparedStatement.setString(4,"Customer");
            preparedStatement.executeUpdate();

        }

    }

    /**
     * A method to read a user by username
     * @param username username
     * @return the user
     * @throws SQLException SQLException
     */
    @Override public UserType readByUsername(String username) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? "
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                if (IsAdminByUsername(username)){
                    return new Admin(resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password"));
                } else {
                    return new Customer(resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password"));
                }
            } else {
                return  null;
            }
        }
    }

    /**
     * A method to read all the users from the database and return an array with them
     * @return array with users
     * @throws SQLException SQLException
     */
    @Override public ArrayList<UserType> readAllUsers() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users "
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<UserType> users = new ArrayList<>();
            while (resultSet.next()){
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                if(IsAdminByUsername(username)){
                    users.add(new Admin(email, username, password));
                } else {
                    users.add(new Customer(email,username,password));
                }
            }
            return users;
        }
    }

    /**
     * A method to get if its admin
     * @param username username
     * @return true or false
     * @throws SQLException SQLException
     */
    @Override public boolean IsAdminByUsername(String username) throws SQLException{
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT isAdmin FROM users WHERE username = ? "
            );
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                if(resultSet.getString("isAdmin").equals("Admin")){
                    return true;
                } else {
                    return false;
                }
            }  else {
                return false;
            }
        }
    }

    /**
     * A method to remove users from the database
     * @param username username
     * @throws SQLException SQLException
     */
    @Override public void removeUser(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        }
    }


    /**
     * A method to edit the users in the database
     * @param email email
     * @param username username
     * @param password password
     * @throws SQLException SQLException
     */
    @Override public void editUsers(String email, String username, String password) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET email = ?, username = ?, password = ? WHERE username = ?"
            );
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,username);
            preparedStatement.executeUpdate();
        }
    }
}
