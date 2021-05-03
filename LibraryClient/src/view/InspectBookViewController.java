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


    public Region getRoot(){return root;}

    public void reset(){
        inspectBookViewModel.clear(viewState.getSelectedBook());
    }

    @FXML public void CancelButton(){
        viewHandler.openView("main");
        viewState.setSelectedBook("");
    }
}
