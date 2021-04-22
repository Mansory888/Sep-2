package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.AddBookViewModel;
import viewmodel.ProfileViewModel;



public class AddBookViewController {
    @FXML private TextField BookIDTextField;
    @FXML private TextField TitleTextField;
    @FXML private TextField AuthorTextField;
    @FXML private TextField YearTextField;
    @FXML private TextArea DescriptionTextArea;
    @FXML private Label errorLabel;

    private Region root;
    private AddBookViewModel addBookViewModel;
    private ViewHandler viewHandler;


    public void init(ViewHandler viewHandler, AddBookViewModel addBookViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.addBookViewModel = addBookViewModel;
        this.root = root;

        BookIDTextField.textProperty().bindBidirectional(addBookViewModel.getBookIDTextField());
        TitleTextField.textProperty().bindBidirectional(addBookViewModel.getTitleTextField());
        AuthorTextField.textProperty().bindBidirectional(addBookViewModel.getAuthorTextField());
        YearTextField.textProperty().bindBidirectional(addBookViewModel.getYearTextField());
        DescriptionTextArea.textProperty().bindBidirectional(addBookViewModel.getDescriptionTextArea());
        errorLabel.textProperty().bind(addBookViewModel.getErrorLabelProperty());
    }

    public Region getRoot(){return root;}

    public void reset(){
        addBookViewModel.clear();
    }

    @FXML public void CancelButton(){viewHandler.openView("managePage");}

    @FXML public void AddButton(){
        addBookViewModel.addBook();
        viewHandler.openView("managePage");
    }
}
