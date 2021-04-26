package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private int year;
    private double rating;
    private int ratingCount;

    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean borrowed;
    private boolean returned;



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
            this.description = description;
            this.title = title;
            this.author = author;
            this.id = id;
            this.year = year;
            ratingCount = 0;
            rating = 0.0;
            borrowDate = LocalDateTime.now();
            returnDate = LocalDateTime.now();
            borrowed = false;
            returned = false;
        }else{
            throw new IllegalArgumentException("There are empty fields.");
        }
    }

    public String getTitle(){return title;}

    public String getAuthor(){return author;}

    public double getRating(){
        if(ratingCount == 0){
            return 0;
        }
        return rating/ratingCount;
    }

    public void setRating(int i){
        rating =+ i;
        ratingCount++;
    }

    public String getId(){return id;}

    public int getYearOfPublication(){
        return year;
    }

    public String getDescription(){return description;}

    @Override public boolean equals(Object obj){
        if(!(obj instanceof Book)){
            return false;
        }
        Book other =(Book) obj;
        return id.equals(other.id) && title.equals(other.title) && author.equals(other.author) &&
                description.equals(other.description) && year == other.year && rating == other.rating;
    }

    public void setId(String id){ this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setAuthor(String author) {this.author = author;}
    public void setDescription(String description) {this.description = description;}
    public void setYear(int year){this.year = year;}

    public String getBorrowDate(){
        if(borrowed){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return borrowDate.format(formatter);
        }
        return "";
    }

    public String getReturnDate(){
        if(returned){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return returnDate.format(formatter);
        }
       return "";
    }

    public void setBorrowed(){borrowed = true;}
    public void setReturned(){returned = true;}
    public boolean getIsReturned(){return  returned;}
}
