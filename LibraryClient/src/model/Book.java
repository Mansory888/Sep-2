package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * @author Nick/Rokas
 * @version 1.0
 *
 */


public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private int year;
    private ArrayList<Rating> ratings;

    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean borrowed;
    private boolean returned;


    /**
     * Creating a book, that has protections for wrong values
     *
     * @param title title for a book
     * @param author author for a book
     * @param year year for a book
     * @param id id for a book
     * @param description description for bok
     */
    public Book(String title, String author, int year, String id, String description){
        if(title!=null && !title.equals("") && author!=null && !author.equals("") && id!=null &&  !id.equals("") && description!=null &&  !description.equals("") ){
            Pattern patternAuthor = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
            if(patternAuthor.matcher(author).find()){
                throw  new IllegalArgumentException("Author name contains numbers or symbols.");
            }
            Pattern patternId= Pattern.compile("[^0-9 ]");
            if(patternId.matcher(id).find()){
                throw  new IllegalArgumentException("Book ID contains characters or symbols.");
            }
            if(year<0){
                throw new IllegalArgumentException("Entered year is lower than 0!");
            }
            this.description = description;
            this.title = title;
            this.author = author;
            this.id = id;

            this.year = year;
            ratings = new ArrayList<>();
            borrowDate = LocalDateTime.now();
            returnDate = LocalDateTime.now();
            borrowed = false;
            returned = false;
        }else{
            throw new IllegalArgumentException("There are empty fields.");
        }
    }

    /**
     * Returns the title
     * @return title
     */
    public String getTitle(){return title;}

    /**
     * Returns book author
     * @return author
     */
    public String getAuthor(){return author;}

    /**
     * Returns the rating
     * @return rating
     */
    public double getRating(){
        int result = 0;
        if (ratings == null || ratings.isEmpty()){
            return 0.0;
        } else {
            for (int i = 0; i < ratings.size(); i++) {
                result += ratings.get(i).getRating();
            }
            System.out.println(ratings.toString());
            return result/ratings.size();
        }
    }

    /**
     * Sets the rating
     * @param rate rate
     */
    public void setRating(int rate, String username){
        boolean isRatedByUser = false;
        if(ratings != null){
            for (int i = 0; i < ratings.size(); i++) {
                if (ratings.get(i).getUsername().equals(username)) {
                    isRatedByUser = true;
                    ratings.get(i).setRating(rate);
                }
            }

            if (!isRatedByUser) {
                ratings.add(new Rating(username, rate));
            }
        }
    }

    /**
     * returns book id
     * @return id
     */
    public String getId(){return id;}

    /**
     * returns year of publication
     * @return year
     */
    public int getYearOfPublication(){
        return year;
    }

    /**
     * returns description
     * @return description
     */
    public String getDescription(){return description;}

    /**
     * Equals method that compares books
     * @param obj the book
     * @return the result
     */
    @Override public boolean equals(Object obj){
        if(!(obj instanceof Book)){
            return false;
        }
        Book other =(Book) obj;
        return id.equals(other.id) && title.equals(other.title) && author.equals(other.author) &&
                description.equals(other.description) && year == other.year;
    }

    /**
     * sets the id
     * @param id id
     */
    public void setId(String id){ this.id = id;}

    /**
     * sets the title
     * @param title title
     */
    public void setTitle(String title) {this.title = title;}

    /**
     * sets the author
     * @param author author
     */
    public void setAuthor(String author) {this.author = author;}

    /**
     * sets the description
     * @param description description
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * sets the year
     * @param year year
     */
    public void setYear(int year){this.year = year;}

    /**
     * returns the borrow date
     * @return borrow date
     */
    public String getBorrowDate(){
        if(borrowed){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return borrowDate.format(formatter);
        }
        return "";
    }

    /**
     * returns the return date
     * @return return date
     */
    public String getReturnDate(){
        if(returned){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return returnDate.format(formatter);
        }
       return "";
    }

    /**
     * sets borrowed
     */
    public void setBorrowed(){borrowed = true;}

    /**
     * set returned
     */
    public void setReturned(){returned = true;}

    /**
     * returns if its returned
     * @return returned
     */
    public boolean getIsReturned(){return  returned;}

    /**
     * Returning a string with the information about the book
     * @return string
     */
    @Override public String toString(){
        return "Id: "+ id + " Title: "+ title + " Author: " + author + " Year: " + year;
    }
}
