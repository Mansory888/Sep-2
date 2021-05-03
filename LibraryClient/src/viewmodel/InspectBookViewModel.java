package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class InspectBookViewModel {
    private StringProperty BookIDTextField;
    private StringProperty TitleLabel;
    private StringProperty AuthorTextField;
    private StringProperty YearTextField;
    private StringProperty DescriptionTextArea;
    private Model model;


    /**
     * Creates a view model for inspect book window
     * @param model model
     */
    public InspectBookViewModel(Model model){
        this.model = model;
        BookIDTextField = new SimpleStringProperty();
        TitleLabel = new SimpleStringProperty();
        AuthorTextField = new SimpleStringProperty();
        YearTextField = new SimpleStringProperty();
        DescriptionTextArea = new SimpleStringProperty();

    }

    /**
     * method to clear the fields
     */
    public void clear(String bookID){
        BookIDTextField.set(model.getLibraryBookByID(bookID).getId());
        TitleLabel.set(model.getLibraryBookByID(bookID).getTitle());
        AuthorTextField.set(model.getLibraryBookByID(bookID).getAuthor());
        YearTextField.set(model.getLibraryBookByID(bookID).getYearOfPublication()+"");
        DescriptionTextArea.set(model.getLibraryBookByID(bookID).getDescription());
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
    public StringProperty getTitleLabel(){return TitleLabel;}

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
