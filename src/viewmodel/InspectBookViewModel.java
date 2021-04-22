package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class InspectBookViewModel {
    private StringProperty BookIDTextField;
    private StringProperty TitleLabel;
    private StringProperty AuthorTextField;
    private StringProperty YearTextField;
    private StringProperty DescriptionTextArea;
    private Model model;


    public InspectBookViewModel(Model model){
        this.model = model;
        BookIDTextField = new SimpleStringProperty();
        TitleLabel = new SimpleStringProperty();
        AuthorTextField = new SimpleStringProperty();
        YearTextField = new SimpleStringProperty();
        DescriptionTextArea = new SimpleStringProperty();

    }

    public void clear(String bookID){
        BookIDTextField.set(model.getLibraryBookByID(bookID).getId());
        TitleLabel.set(model.getLibraryBookByID(bookID).getTitle());
        AuthorTextField.set(model.getLibraryBookByID(bookID).getAuthor());
        YearTextField.set(model.getLibraryBookByID(bookID).getYearOfPublication()+"");
        DescriptionTextArea.set(model.getLibraryBookByID(bookID).getDescription());
    }


    public StringProperty getBookIDTextField(){return BookIDTextField;}
    public StringProperty getTitleLabel(){return TitleLabel;}
    public StringProperty getAuthorTextField(){return AuthorTextField;}
    public StringProperty getYearTextField (){ return YearTextField;}
    public StringProperty getDescriptionTextArea (){return DescriptionTextArea;}
}
