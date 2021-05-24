package model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RatingsDAO {
    void createRating(int rating, String username, String id) throws SQLException;
    ArrayList<Rating> getAllRatings() throws SQLException;
    boolean bookRated(String username, String id) throws SQLException;

}
