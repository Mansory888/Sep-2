package model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class Rating {
    private String username;
    private int rating;
    private String book_id;

    /**
     * A constructor creating a rating class
     * @param username username
     * @param rating rating
     */
    public Rating(String username, int rating, String book_id){
        this.rating = rating;
        this.username = username;
        this.book_id = book_id;
    }

    /**
     * A method rating the rating
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
     * returning the username
     * @return username
     */
    public String getUsername(){
        return username;
    }

    /**
     * returning the book id
     *
     * @return book id
     */
    public String getBook_id() {
        return book_id;
    }

    /**
     * Returning a string with the username and rating
     * @return string
     */
    @Override public String toString(){return username + " " + rating;}
}
