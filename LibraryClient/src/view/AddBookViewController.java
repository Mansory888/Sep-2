package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.AddBookViewModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class AddBookViewController {
    @FXML private TextField BookIDTextField;
    @FXML private TextField TitleTextField;
    @FXML private TextField AuthorTextField;
    @FXML private TextField YearTextField;
    @FXML private TextArea DescriptionTextArea;
    @FXML private TextField GenreTextField;
    @FXML private Label errorLabel;

    private Region root;
    private AddBookViewModel addBookViewModel;
    private ViewHandler viewHandler;


    /**
     * Initializes the add book controller
     * @param viewHandler view handler
     * @param addBookViewModel add book view model
     * @param root root
     */
    public void init(ViewHandler viewHandler, AddBookViewModel addBookViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.addBookViewModel = addBookViewModel;
        this.root = root;

        BookIDTextField.textProperty().bindBidirectional(addBookViewModel.getBookIDTextField());
        TitleTextField.textProperty().bindBidirectional(addBookViewModel.getTitleTextField());
        AuthorTextField.textProperty().bindBidirectional(addBookViewModel.getAuthorTextField());
        YearTextField.textProperty().bindBidirectional(addBookViewModel.getYearTextField());
        DescriptionTextArea.textProperty().bindBidirectional(addBookViewModel.getDescriptionTextArea());
        GenreTextField.textProperty().bindBidirectional(addBookViewModel.getGenreTextField());
        errorLabel.textProperty().bind(addBookViewModel.getErrorLabelProperty());
    }

    /**
     * Returns the root
     * @return root
     */
    public Region getRoot(){return root;}

    /**
     * A method to reset the fields
     */
    public void reset(){
        addBookViewModel.clear();
    }

    /**
     * Cancel button
     */
    @FXML private void CancelButton(){viewHandler.openView("managePage");}

    /**
     * Add book button
     */
    @FXML private void AddButton(){

        if(addBookViewModel.addBook()){
        viewHandler.openView("managePage");
    }}
}
