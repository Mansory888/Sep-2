package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
    void create(UserType userType) throws SQLException;
    UserType readByUsername(String username) throws SQLException;
    ArrayList<UserType> readAllUsers() throws SQLException;
    boolean IsAdminByUsername(String username) throws SQLException;
    void removeUser(String username) throws SQLException;
    void editUsers(String email, String username, String password) throws SQLException;


}
