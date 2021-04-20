package model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String description;
    private int year;
    private double rating;
    private int ratingCount;


    public Book(String title, String author, int year, String id, String description){
        this.description = description;
        this.title = title;
        this.author = author;
        this.id = id;
        this.year = year;
        ratingCount = 0;
        rating = 0.0;
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
}
