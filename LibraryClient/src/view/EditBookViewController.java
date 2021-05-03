package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.AddBookViewModel;
import viewmodel.EditBookViewModel;
import viewmodel.InspectBookViewModel;

public class EditBookViewController {
    @FXML private TextField BookIDTextField;
    @FXML private TextField BookTitleTextField;
    @FXML private TextField AuthorTextField;
    @FXML private TextField YearTextField;
    @FXML private TextArea DescriptionTextArea;
    @FXML private ComboBox<Integer> RatingBox;
    private ObservableList<Integer> choiceList;
    private String bookId;


    private Region root;
    private EditBookViewModel editBookViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;


    public void init(ViewHandler viewHandler, EditBookViewModel editBookViewModel, Region root, ViewState viewState) {
        this.viewHandler = viewHandler;
        this.editBookViewModel = editBookViewModel;
        this.root = root;
        this.viewState=viewState;

        BookIDTextField.textProperty().bindBidirectional(editBookViewModel.getBookIDTextField());
        BookTitleTextField.textProperty().bindBidirectional(editBookViewModel.getTitleField());
        AuthorTextField.textProperty().bindBidirectional(editBookViewModel.getAuthorTextField());
        YearTextField.textProperty().bindBidirectional(editBookViewModel.getYearTextField());
        DescriptionTextArea.textProperty().bindBidirectional(editBookViewModel.getDescriptionTextArea());

        choiceList = FXCollections.observableArrayList(1,2,3,4,5);
        BookIDTextField.setEditable(false);

        RatingBox.getItems().addAll(choiceList);

        reset();

    }


    public Region getRoot(){return root;}

    public void reset(){
        editBookViewModel.clear(viewState.getSelectedBook());
    }

    @FXML public void CancelButton(){
        viewHandler.openView("main");
        viewState.setSelectedBook("");
    }

    @FXML public void EditButton(){
        editBookViewModel.editBook(viewState.getSelectedBook());
        viewHandler.openView("main");
    }
}
