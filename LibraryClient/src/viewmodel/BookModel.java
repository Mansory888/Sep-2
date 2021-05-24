package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Book;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class BookModel {
    private StringProperty BookID;
    private StringProperty BookName;
    private StringProperty BookAuthor;
    private StringProperty YearPublished;
    private StringProperty Rating;
    private StringProperty description;
    private StringProperty BorrowDate;
    private StringProperty ReturnDate;
    private StringProperty genre;

    /**
     * Creates a book model
     * @param book book
     */
    public BookModel(Book book){
        BookID = new SimpleStringProperty(book.getId());
        BookName = new SimpleStringProperty(book.getTitle());
        BookAuthor = new SimpleStringProperty(book.getAuthor());
        YearPublished = new SimpleStringProperty(book.getYearOfPublication()+"");
        Rating = new SimpleStringProperty(book.getRating()+"");
        description = new SimpleStringProperty(book.getDescription());
        BorrowDate = new SimpleStringProperty(book.getBorrowDate());
        ReturnDate = new SimpleStringProperty(book.getReturnDate());
        genre = new SimpleStringProperty(book.getGenre());
    }

    /**
     * returns BookID
     * @return BookID
     */
    public StringProperty getBookID(){return BookID;}

    /**
     * returns BookName
     * @return BookName
     */
    public StringProperty getBookName(){return BookName;}

    /**
     * returns BookAuthor
     * @return BookAuthor
     */
    public StringProperty getBookAuthor(){return BookAuthor;}

    /**
     * returns Year Published
     * @return Year Published
     */
    public StringProperty getYearPublished(){return YearPublished;}

    /**
     * returns Rating
     * @return Rating
     */
    public StringProperty getRating(){return Rating;}

    /**
     * returns Description
     * @return Description
     */
    public StringProperty getDescription(){return description;}

    /**
     * returns Borrow Date
     * @return Borrow Date
     */
    public StringProperty getBorrowDate(){return BorrowDate;}

    /**
     * returns Return Date
     * @return Return Date
     */
    public StringProperty getReturnDate(){return ReturnDate;}

    /**
     * returns genre
     * @return genre
     */
    public StringProperty getGenre() {
        return genre;
    }
}
