package model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private int year;
    private int rating;


    public Book(String title, String author, int year, String id, String description){
        this.description = description;
        this.title = title;
        this.author = author;
        this.id = id;
        this.year = year;
        rating = 0;
    }

    public String getTitle(){return title;}

    public String getAuthor(){return author;}

    public int getRating(){return rating;}

    public String getId(){return id;}

    public int getYearOfPublication(){
        return year;
    }

    public String getDescription(){return description;}
}
