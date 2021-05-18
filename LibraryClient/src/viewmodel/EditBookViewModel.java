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

    }

    /**
     * method to clear the fields
     */
    public void clear(String bookID){
        BookIDTextField.set(model.getLibraryBookByID(bookID).getId());
        TitleField.set(model.getLibraryBookByID(bookID).getTitle());
        AuthorTextField.set(model.getLibraryBookByID(bookID).getAuthor());
        YearTextField.set(model.getLibraryBookByID(bookID).getYearOfPublication()+"");
        DescriptionTextArea.set(model.getLibraryBookByID(bookID).getDescription());
    }

    /**
     * method to edit book
     */
    public void editBook(String bookID){
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setTitle(TitleField.get());
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setAuthor(AuthorTextField.get());
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setYear(Integer.parseInt(YearTextField.get()));
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setDescription(DescriptionTextArea.get());
        model.getServerModel().editBook(BookIDTextField.get(),TitleField.get(), AuthorTextField.get(), YearTextField.get(), DescriptionTextArea.get());
    }

    /**
     * A method to rate a book
     *
     * @param bookID book id
     * @param rating rating
     */
    public void rateBook(String bookID, int rating) {
        model.getLibraryBookByID(bookID).setRating(rating, model.getUser().getUsername());
        model.getUserInventory().getBookById(bookID).setRating(rating, model.getUser().getUsername());
        model.getServerModel().rateBook(bookID, model.getUser().getUsername(), rating);
    }

    /**
     * Returns if the user is admin
     * @return id admin
     */
    public boolean isAdmin() {
        return model.isAdmin();
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
     * returns Description TextArea
     * @return Description TextArea
     */
    public StringProperty getDescriptionTextArea (){return DescriptionTextArea;}
}
