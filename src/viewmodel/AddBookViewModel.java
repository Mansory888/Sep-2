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
    private Model model;


    public AddBookViewModel(Model model){
        this.model = model;
        BookIDTextField = new SimpleStringProperty();
        TitleTextField = new SimpleStringProperty();
        AuthorTextField = new SimpleStringProperty();
        YearTextField = new SimpleStringProperty();
        DescriptionTextArea = new SimpleStringProperty();
    }

    public void clear(){
        BookIDTextField.set("");
        TitleTextField.set("");
        AuthorTextField.set("");
        YearTextField.set("");
        DescriptionTextArea.set("");
    }

    public void addBook(){
        Book book = new Book(TitleTextField.get(), AuthorTextField.get(), Integer.parseInt(YearTextField.get()),
                BookIDTextField.get(), DescriptionTextArea.get());

        try {
            if(model.getUserInventory().getBooks().contains(book)){

            } else {
                model.addBookToLibrary(book);
            }

        } catch (Exception e){

        }


    }

    public StringProperty getBookIDTextField(){return BookIDTextField;}
    public StringProperty getTitleTextField(){return TitleTextField;}
    public StringProperty getAuthorTextField(){return AuthorTextField;}
    public StringProperty getYearTextField (){ return YearTextField;}
    public StringProperty getDescriptionTextArea (){return DescriptionTextArea;}
}
