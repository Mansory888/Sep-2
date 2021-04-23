package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class EditBookViewModel {
    private StringProperty BookIDTextField;
    private StringProperty TitleField;
    private StringProperty AuthorTextField;
    private StringProperty YearTextField;
    private StringProperty DescriptionTextArea;
    private Model model;


    public EditBookViewModel(Model model){
        this.model = model;
        BookIDTextField = new SimpleStringProperty();
        TitleField = new SimpleStringProperty();
        AuthorTextField = new SimpleStringProperty();
        YearTextField = new SimpleStringProperty();
        DescriptionTextArea = new SimpleStringProperty();

    }

    public void clear(String bookID){
        BookIDTextField.set(model.getLibraryBookByID(bookID).getId());
        TitleField.set(model.getLibraryBookByID(bookID).getTitle());
        AuthorTextField.set(model.getLibraryBookByID(bookID).getAuthor());
        YearTextField.set(model.getLibraryBookByID(bookID).getYearOfPublication()+"");
        DescriptionTextArea.set(model.getLibraryBookByID(bookID).getDescription());
    }

    public void editBook(String bookID){
        //model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setId(BookIDTextField.get());
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setTitle(TitleField.get());
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setAuthor(AuthorTextField.get());
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setYear(Integer.parseInt(YearTextField.get()));
        model.getLibraryInventory().getBookById(model.getLibraryBookByID(bookID).getId()).setDescription(DescriptionTextArea.get());
    }

    public StringProperty getBookIDTextField(){return BookIDTextField;}
    public StringProperty getTitleField(){return TitleField;}
    public StringProperty getAuthorTextField(){return AuthorTextField;}
    public StringProperty getYearTextField (){ return YearTextField;}
    public StringProperty getDescriptionTextArea (){return DescriptionTextArea;}
}
