package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.Date;
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
    private String genre;
    private ArrayList<Rating> ratings;

    private Date borrowDate;
    private Date returnDate;
    private boolean returned;


    /**
     * Creating a book, that has protections for wrong values
     *
     * @param title title for a book
     * @param author author for a book
     * @param year year for a book
     * @param id id for a book
     * @param description description for book
     * @param genre genre of the book
     */
    public Book(String title, String author, int year, String id, String description, String genre){
        if(title!=null && !title.equals("") && author!=null && !author.equals("") && id!=null &&  !id.equals("") && description!=null &&  !description.equals("")
                && genre!=null && !genre.equals("")){
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
            if(description.length()>3000){
                throw new IllegalArgumentException("Description is over 3000 characters");
            }
            this.description = description;
            this.title = title;
            this.author = author;
            this.id = id;

            this.year = year;
            ratings = new ArrayList<>();
            borrowDate = Date.valueOf(LocalDate.now());
            returnDate = Date.valueOf(LocalDate.now());
            returned = false;
            this.genre = genre;
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
        int index = 0;
        if (ratings == null || ratings.isEmpty()){
            return 0.0;
        } else {
            for (int i = 0; i < ratings.size(); i++) {
                if(ratings.get(i)!=null){
                    result += ratings.get(i).getRating();
                    index++;
                }
            }
            return (double) result/index;
        }
    }

    /**
     * Sets the rating
     * @param rate rate
     * @param username users nickname
     */
    public void setRating(int rate, String username){
        boolean isRatedByUser = false;
        if( rate<=5 && rate>=0 ) {
            if (ratings != null) {
                for (int i = 0; i < ratings.size(); i++) {
                    if (ratings.get(i).getUsername().equals(username)) {
                        isRatedByUser = true;
                        ratings.get(i).setRating(rate);
                    }
                }

                if (!isRatedByUser) {
                    ratings.add(new Rating(username, rate, id));
                }
            }
        }else{
            throw new IllegalArgumentException("The selected rating is out of bounds");
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
    public void setId(String id){

        if(id!=null &&  !id.equals("")){
            Pattern patternId= Pattern.compile("[^0-9 ]");
            if(patternId.matcher(id).find()){
                throw  new IllegalArgumentException("Book ID contains characters or symbols.");
            }
            this.id = id;
        }else{
            throw new IllegalArgumentException("Book id is left empty.");
        }
    }

    /**
     * sets the title
     * @param title title
     */
    public void setTitle(String title) {
        if(title!=null && !title.equals("")) {
            this.title = title;
        }else {
            throw new IllegalArgumentException("Title is empty.");
        }
    }

    /**
     * sets the author
     * @param author author
     */
    public void setAuthor(String author) {
        if (author!=null && !author.equals("")){
            Pattern patternAuthor = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
            if (patternAuthor.matcher(author).find()) {
                throw new IllegalArgumentException("Author name contains numbers or symbols.");
            }
            this.author = author;
        }else {
            throw new IllegalArgumentException("Author field is left empty");}
    }

    /**
     * sets the description
     * @param description description
     */
    public void setDescription(String description) {
        if(description!=null && !description.equals("")){
            if (description.length()<=3000){
                this.description = description;
            }else {
                throw new IllegalArgumentException("Description is over 3000 characters");
            }
        }else {
            throw  new IllegalArgumentException("Description is empty.");
        }
    }


    /**
     * Sets a borrowing date for a book
     * @param borrowDate the date when user borrowed a book
     */
    public void setBorrowDate(Date borrowDate){
        this.borrowDate=borrowDate;
    }

    /**
     * Sets a date when the book was returned.
     * @param returnDate the date when user returned a book
     */
    public void setReturnDate(Date returnDate){
        this.returnDate=returnDate;
    }
    /**
     * sets the year
     * @param year year
     */
    public void setYear(int year){
        if (year<0){
            throw new IllegalArgumentException("Entered year is lower than 0");
        }
        this.year=year;
    }

    /**
     * returns the borrow date
     * @return borrow date
     */
    public String getBorrowDate(){

        return borrowDate.toString();

    }

    /**
     * returns the return date
     * @return return date
     */
    public String getReturnDate(){
        if(returned){

            return returnDate.toString();
        }
        return "";
    }

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
     * Returns the genre of the book.
     * @return genre - book genre.
     */
    public String getGenre(){return genre;}

    /**
     * Sets the genre in the book, after checking if it is not an empty field.
     * @param genre - book genre.
     */
    public void setGenre(String genre){
        if(genre!=null && !genre.equals("")){
            this.genre = genre;
        }else{
            throw new IllegalArgumentException("Empty field left!");
        }
    }
    /**
     * Returning a string with the information about the book
     * @return string
     */
    @Override public String toString(){
        return "Id: "+ id + " Title: "+ title + " Author: " + author + " Year: " + year;
    }
}
