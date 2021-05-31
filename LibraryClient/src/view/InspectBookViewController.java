package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.InspectBookViewModel;


/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class InspectBookViewController {
    @FXML private TextField BookIDTextField;
    @FXML private Label BookTitle;
    @FXML private TextField AuthorTextField;
    @FXML private TextField YearTextField;
    @FXML private TextArea DescriptionTextArea;
    @FXML private ComboBox<Integer> RatingBox;
    private ObservableList<Integer> choiceList;


    private Region root;
    private InspectBookViewModel inspectBookViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;


    /**
     *  Initializes the Inspect Book Controller
     * @param viewHandler view handler
     * @param inspectBookViewModel inspect book view model
     * @param root root
     * @param viewState view state
     */
    public void init(ViewHandler viewHandler, InspectBookViewModel inspectBookViewModel, Region root, ViewState viewState) {
        this.viewHandler = viewHandler;
        this.inspectBookViewModel = inspectBookViewModel;
        this.root = root;
        this.viewState=viewState;

        BookIDTextField.textProperty().bindBidirectional(inspectBookViewModel.getBookIDTextField());
        BookTitle.textProperty().bindBidirectional(inspectBookViewModel.getTitleLabel());
        AuthorTextField.textProperty().bindBidirectional(inspectBookViewModel.getAuthorTextField());
        YearTextField.textProperty().bindBidirectional(inspectBookViewModel.getYearTextField());
        DescriptionTextArea.textProperty().bindBidirectional(inspectBookViewModel.getDescriptionTextArea());

        choiceList = FXCollections.observableArrayList(1,2,3,4,5);


        BookIDTextField.setEditable(false);
        AuthorTextField.setEditable(false);
        YearTextField.setEditable(false);
        DescriptionTextArea.setEditable(false);



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
        inspectBookViewModel.clear(viewState.getSelectedBook());
    }


    /**
     * Cancel button
     */
    @FXML private void CancelButton(){
        if(RatingBox.getValue() != null){
            inspectBookViewModel.rateBook(viewState.getSelectedBook(), RatingBox.getValue());
        }
        viewHandler.openView("main");
        viewState.setSelectedBook("");
    }
}
