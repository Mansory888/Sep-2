package model;

public class Rating {
    private String username;
    private int rating;

    public Rating(String username, int rating){
        this.rating = rating;
        this.username = username;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public int getRating(){
        return rating;
    }

    public String getUsername(){
        return username;
    }

    @Override public String toString(){return username + " " + rating;}
}
