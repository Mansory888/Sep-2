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


/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class EditBookViewController {
    @FXML private TextField BookIDTextField;
    @FXML private TextField BookTitleTextField;
    @FXML private TextField AuthorTextField;
    @FXML private TextField YearTextField;
    @FXML private TextArea DescriptionTextArea;
    @FXML private ComboBox<Integer> RatingBox;
    private ObservableList<Integer> choiceList;



    private Region root;
    private EditBookViewModel editBookViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;


    /**
     * Initializes the Edit Book Controller
     *
     * @param viewHandler      view handler
     * @param editBookViewModel editBookViewModel
     * @param root             root
     */
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


    /**
     * Returns the root
     *
     * @return root
     */
    public Region getRoot(){return root;}

    /**
     * A method to reset the fields
     */
    public void reset(){
        editBookViewModel.clear(viewState.getSelectedBook());
    }

    /**
     * Cancel button
     */
    @FXML public void CancelButton(){
        if(RatingBox.getValue() != null){
            editBookViewModel.rateBook(viewState.getSelectedBook(), RatingBox.getValue());
        }
        viewHandler.openView("main");
        viewState.setSelectedBook("");
    }

    /**
     * Edit book button
     */
    @FXML public void EditButton(){
        if (RatingBox.getValue() != null) {
            editBookViewModel.rateBook(viewState.getSelectedBook(), RatingBox.getValue());
        }
        editBookViewModel.editBook(viewState.getSelectedBook());
        viewHandler.openView("main");
    }
}
