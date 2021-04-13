package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Book;

public class BookModel {
    private StringProperty BookID;
    private StringProperty BookName;
    private StringProperty BookAuthor;
    private StringProperty YearPublished;
    private StringProperty Rating;
    private StringProperty description;

    public BookModel(Book book){
        BookID = new SimpleStringProperty(book.getId());
        BookName = new SimpleStringProperty(book.getTitle());
        BookAuthor = new SimpleStringProperty(book.getAuthor());
        YearPublished = new SimpleStringProperty(book.getYearOfPublication()+"");
        Rating = new SimpleStringProperty(book.getRating()+"");
        description = new SimpleStringProperty(book.getDescription());
    }

    public StringProperty getBookID(){return BookID;}
    public StringProperty getBookName(){return BookName;}
    public StringProperty getBookAuthor(){return BookAuthor;}
    public StringProperty getYearPublished(){return YearPublished;}
    public StringProperty getRating(){return Rating;}
    public StringProperty getDescription(){return description;}

}
