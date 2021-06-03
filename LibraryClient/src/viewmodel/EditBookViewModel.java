package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class EditBookViewModel {
    private StringProperty BookIDTextField;
    private StringProperty TitleField;
    private StringProperty AuthorTextField;
    private StringProperty YearTextField;
    private StringProperty GenreTextFiled;
    private StringProperty DescriptionTextArea;
    private Model model;


    /**
     * Creates a view model for edit book window
     * @param model model
     */
    public EditBookViewModel(Model model){
        this.model = model;
        BookIDTextField = new SimpleStringProperty();
        TitleField = new SimpleStringProperty();
        AuthorTextField = new SimpleStringProperty();
        YearTextField = new SimpleStringProperty();
        DescriptionTextArea = new SimpleStringProperty();
        GenreTextFiled = new SimpleStringProperty();
    }

    /**
     * method to clear the fields
     * @param bookID bookID of the selected book to edit.
     */
    public void clear(String bookID){
        BookIDTextField.set(model.getLibraryBookByID(bookID).getId());
        TitleField.set(model.getLibraryBookByID(bookID).getTitle());
        AuthorTextField.set(model.getLibraryBookByID(bookID).getAuthor());
        YearTextField.set(model.getLibraryBookByID(bookID).getYearOfPublication()+"");
        DescriptionTextArea.set(model.getLibraryBookByID(bookID).getDescription());
        GenreTextFiled.set(model.getLibraryBookByID(bookID).getGenre());
    }

    /**
     * method to edit book
     * @param bookID book id of the book in editing
     */
    public void editBook(String bookID){
        model.getLibraryInventory().getBookById(bookID).setTitle(TitleField.get());
        model.getLibraryInventory().getBookById(bookID).setAuthor(AuthorTextField.get());
        model.getLibraryInventory().getBookById(bookID).setYear(Integer.parseInt(YearTextField.get()));
        model.getLibraryInventory().getBookById(bookID).setDescription(DescriptionTextArea.get());
        model.getLibraryInventory().getBookById(bookID).setGenre(GenreTextFiled.get());
        model.getServerModel().editBook(BookIDTextField.get(),TitleField.get(), AuthorTextField.get(), YearTextField.get(), DescriptionTextArea.get(), GenreTextFiled.get());
    }

    /**
     * A method to rate a book
     *
     * @param bookID book id
     * @param rating rating
     */
    public void rateBook(String bookID, int rating) {
        model.getLibraryBookByID(bookID).setRating(rating, model.getUser().getUsername());
        if(model.getUserBookByID(bookID)!=null){
            model.getUserInventory().getBookById(bookID).setRating(rating, model.getUser().getUsername());
        }
        model.getServerModel().rateBook(bookID, model.getUser().getUsername(), rating);
    }

    /**
     * returns Book ID TextField
     * @return BookIDTextField
     */
    public StringProperty getBookIDTextField(){return BookIDTextField;}

    /**
     * returns TitleText Field
     * @return TitleText Field
     */
    public StringProperty getTitleField(){return TitleField;}

    /**
     * returns Author TextField
     * @return Author TextField
     */
    public StringProperty getAuthorTextField(){return AuthorTextField;}

    /**
     * returns Year TextField
     * @return Year TextField
     */
    public StringProperty getYearTextField (){ return YearTextField;}

    /**
     * returns genre Text Filed
     * @return genre Text Filed
     */
    public StringProperty getGenreTextFiled(){ return GenreTextFiled; }

    /**
     * returns Description TextArea
     * @return Description TextArea
     */
    public StringProperty getDescriptionTextArea (){return DescriptionTextArea;}
}
