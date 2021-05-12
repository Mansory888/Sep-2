package view;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Book;
import viewmodel.BookModel;
import viewmodel.UserInventoryViewModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class UserInventoryViewController {
    @FXML private Label username_spot;
    @FXML private TextField search_textfield;
    @FXML private TableView<BookModel> main_table;
    @FXML private TableColumn<BookModel, String> BookIDCollum;
    @FXML private TableColumn<BookModel, String> BookNameCollum;
    @FXML private TableColumn<BookModel, String> BookAuthorCollum;
    @FXML private TableColumn<BookModel, String> YearPublishedCollum;
    @FXML private TableColumn<BookModel, String> RatingCollum;
    @FXML private TableColumn<BookModel, String> BorrowDateCollum;
    @FXML private TableColumn<BookModel, String> ReturnDateCollum;


    private Region root;
    private UserInventoryViewModel userInventoryViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;

    public UserInventoryViewController (){

    }

    /**
     * Initializes the User Inventory Controller
     * @param viewHandler view handler
     * @param userInventoryViewModel user inventory  view model
     * @param root root
     * @param viewState view state
     */
    public void init(ViewHandler viewHandler, UserInventoryViewModel userInventoryViewModel, Region root, ViewState viewState){
        this.viewHandler = viewHandler;
        this.userInventoryViewModel = userInventoryViewModel;
        this.root = root;
        this.viewState=viewState;
        search_textfield.textProperty().bindBidirectional(userInventoryViewModel.getSearch_textfield());
        username_spot.textProperty().bind(userInventoryViewModel.getUsername_spot());

        BookIDCollum.setCellValueFactory(celldata -> celldata.getValue().getBookID());
        BookNameCollum.setCellValueFactory(celldata -> celldata.getValue().getBookName());
        BookAuthorCollum.setCellValueFactory(celldata -> celldata.getValue().getBookAuthor());
        YearPublishedCollum.setCellValueFactory(celldata -> celldata.getValue().getYearPublished());
        RatingCollum.setCellValueFactory(celldata -> celldata.getValue().getRating());
        BorrowDateCollum.setCellValueFactory(celldata -> celldata.getValue().getBorrowDate());
        ReturnDateCollum.setCellValueFactory(celldata -> celldata.getValue().getReturnDate());

       // search function
        FilteredList<BookModel> filteredData = new FilteredList<>(userInventoryViewModel.getList(), b -> true);

        search_textfield.textProperty().addListener(((observable, oldValue, newValue) ->{
            filteredData.setPredicate(book -> {
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if(book.getBookName().get().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (book.getBookID().get().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (book.getBookAuthor().get().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if (book.getYearPublished().get().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else {
                    return false;
                }
            });
        } ));

        SortedList<BookModel> sortedList = new SortedList<>(filteredData);

        sortedList.comparatorProperty().bind(main_table.comparatorProperty());

        main_table.setItems(sortedList);


        main_table.setRowFactory( tv -> {
            TableRow<BookModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (! row.isEmpty())){
                    BookModel rowData = row.getItem();
                    viewState.setSelectedBook(rowData.getBookID().get());

                    viewHandler.openView("inspect");
                }
            });
            return row;
        });



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
    public void reset(){userInventoryViewModel.clear();}

    /**
     * A method to load the table
     */
    public void load(){userInventoryViewModel.update();}

    /**
     * A method to set name
     */
    public void setName(){
        userInventoryViewModel.setName();
    }

    /**
     * Button to go to the main window
     */
    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    /**
     * Button to go to profile window
     */
    @FXML public void Profile_button(){viewHandler.openView("profile");}

    /**
     * Button to return book
     */
    @FXML public void ReturnButton(){
        BookModel selectedItem = main_table.getSelectionModel().getSelectedItem();
        userInventoryViewModel.ReturnBook(selectedItem.getBookID().get());
    }


}
