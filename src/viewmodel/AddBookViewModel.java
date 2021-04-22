package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Book;
import model.Model;

public class AddBookViewModel {
    private StringProperty BookIDTextField;
    private StringProperty TitleTextField;
    private StringProperty AuthorTextField;
    private StringProperty YearTextField;
    private StringProperty DescriptionTextArea;
    private StringProperty errorLabelProperty;
    private Model model;


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

    public void clear() {
        BookIDTextField.set("");
        TitleTextField.set("");
        AuthorTextField.set("");
        YearTextField.set("");
        DescriptionTextArea.set("");
        errorLabelProperty.set("");
    }

    public void addBook() {
        try {
            Book book = new Book(TitleTextField.get(), AuthorTextField.get(), Integer.parseInt(YearTextField.get()),
                    BookIDTextField.get(), DescriptionTextArea.get());
            try {
                if (model.getUserInventory().getBooks().contains(book)) {

                } else {
                    model.addBookToLibrary(book);
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

    public StringProperty getBookIDTextField() {
        return BookIDTextField;
    }

    public StringProperty getTitleTextField() {
        return TitleTextField;
    }

    public StringProperty getAuthorTextField() {
        return AuthorTextField;
    }

    public StringProperty getYearTextField() {
        return YearTextField;
    }

    public StringProperty getDescriptionTextArea() {
        return DescriptionTextArea;
    }

    public StringProperty getErrorLabelProperty() {
        return errorLabelProperty;
    }
}
