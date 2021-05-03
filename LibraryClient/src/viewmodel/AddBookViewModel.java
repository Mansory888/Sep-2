package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Book;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class AddBookViewModel {
    private StringProperty BookIDTextField;
    private StringProperty TitleTextField;
    private StringProperty AuthorTextField;
    private StringProperty YearTextField;
    private StringProperty DescriptionTextArea;
    private StringProperty errorLabelProperty;
    private Model model;


    /**
     * Creates a view model for add book window
     * @param model model
     */
    public AddBookViewModel(Model model) {
        this.model = model;
        BookIDTextField = new SimpleStringProperty();
        TitleTextField = new SimpleStringProperty();
        AuthorTextField = new SimpleStringProperty();
        YearTextField = new SimpleStringProperty();
        DescriptionTextArea = new SimpleStringProperty();
        errorLabelProperty = new SimpleStringProperty();
        errorLabelProperty.set("");
    }

    /**
     * method to clear the fields
     */
    public void clear() {
        BookIDTextField.set("");
        TitleTextField.set("");
        AuthorTextField.set("");
        YearTextField.set("");
        DescriptionTextArea.set("");
        errorLabelProperty.set("");
    }

    /**
     * method to add a book to the library
     */
    public void addBook() {
        try {
            Book book = new Book(TitleTextField.get(), AuthorTextField.get(), Integer.parseInt(YearTextField.get()),
                    BookIDTextField.get(), DescriptionTextArea.get());
            try {
                boolean isInInventory=false;
                for(int i=0;i<model.getLibraryInventory().getSize();i++){
                    if(model.getLibraryInventory().getBook(i).getId().equals(book.getId())){
                        isInInventory=true;
                    }
                }
                if(isInInventory){
                    errorLabelProperty.set("Book is already in inventory!");
                } else {
                    model.addBookToLibrary(book);
                    errorLabelProperty.set("");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            errorLabelProperty.set("");
        }catch (NumberFormatException numberFormatException){
                errorLabelProperty.set("Wrong year entered.");
        } catch (IllegalArgumentException illegalArgumentException) {
            errorLabelProperty.set(illegalArgumentException.getMessage());
        }




    }

    /**
     * returns Book ID TextField
     * @return BookIDTextField
     */
    public StringProperty getBookIDTextField() {
        return BookIDTextField;
    }

    /**
     * returns TitleText Field
     * @return TitleText Field
     */
    public StringProperty getTitleTextField() {
        return TitleTextField;
    }

    /**
     * returns Author TextField
     * @return Author TextField
     */
    public StringProperty getAuthorTextField() {
        return AuthorTextField;
    }

    /**
     * returns Year TextField
     * @return Year TextField
     */
    public StringProperty getYearTextField() {
        return YearTextField;
    }

    /**
     * returns Description TextArea
     * @return Description TextArea
     */
    public StringProperty getDescriptionTextArea() {
        return DescriptionTextArea;
    }

    /**
     * returns Error Label Property
     * @return Error Label Property
     */
    public StringProperty getErrorLabelProperty() {
        return errorLabelProperty;
    }
}
