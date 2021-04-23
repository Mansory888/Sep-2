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
    private String bookId;


    private Region root;
    private InspectBookViewModel inspectBookViewModel;
    private ViewHandler viewHandler;


    public void init(ViewHandler viewHandler, InspectBookViewModel inspectBookViewModel, Region root, String bookId) {
        this.viewHandler = viewHandler;
        this.inspectBookViewModel = inspectBookViewModel;
        this.root = root;
        this.bookId = bookId;

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


        //RatingBox = new ComboBox<>(choiceList);
        //When u try to initialize the ComboBox, it doesn't work, but if u don't then it works. Strange shit
        RatingBox.getItems().addAll(choiceList);

        reset();

    }


    public Region getRoot(){return root;}

    public void reset(){
        inspectBookViewModel.clear(bookId);
    }

    @FXML public void CancelButton(){
        viewHandler.openView("main");
        System.out.println(RatingBox.getValue());
    }
}
