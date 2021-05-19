package model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Rating {
    private String username;
    private int rating;

    /**
     * A constructor creating a rating class
     *
     * @param username username
     * @param rating   rating
     */
    public Rating (String username, int rating){
        this.rating = rating;
        this.username = username;
    }

    /**
     * A method rating the rating
     *
     * @param rating rating
     */
    public void setRating(int rating){
        this.rating = rating;
    }

    /**
     * A method getting the rating
     */
    public int getRating(){
        return rating;
    }

    /**
     * A method setting username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * Returning a string with the username and rating
     * @return string
     */
    @Override public String toString() {
        return username + " " + rating;
    }
}
