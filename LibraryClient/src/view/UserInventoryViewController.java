package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.Book;
import viewmodel.BookModel;
import viewmodel.UserInventoryViewModel;

import java.time.LocalDateTime;

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
    @FXML private ComboBox<String> genresBox;
    @FXML private ComboBox<Integer> publishingYearBox;


    private Region root;
    private UserInventoryViewModel userInventoryViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;
    private ObservableList<String> genres;
    private ObservableList<Integer> publishingYears;

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

        // filters
        // genres
        genres = FXCollections.observableArrayList("All","Science","Finance","Fiction");
        genresBox.getItems().addAll(genres);
        //year
        publishingYears = FXCollections.observableArrayList();
        publishingYears.add(null);
        for(int i = LocalDateTime.now().getYear(); i>=0; i--){
            publishingYears.add(i);
        }
        publishingYearBox.getItems().addAll(publishingYears);
        genresBox.getSelectionModel().select(0);
        publishingYearBox.getSelectionModel().select(0);

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
    /**
     * Filter books by chosen year/genre or both criterias.
     */

    @FXML private void filterBooks(){
        FilteredList<BookModel> filteredData = new FilteredList<>(userInventoryViewModel.getList(), b -> true);
        filteredData.setPredicate(book -> {
            if(genresBox.getValue() == null && publishingYearBox.getValue()==null || genresBox.getValue().equals("All") && publishingYearBox.getValue()==null){
                return true;
            }


            if(book.getGenre().get().equals(genresBox.getValue()) && publishingYearBox.getValue()==Integer.parseInt(book.getYearPublished().get())
                    || book.getGenre().get().equals(genresBox.getValue()) && publishingYearBox.getValue()==null
                    || genresBox.getValue().equals("All") && publishingYearBox.getValue()==Integer.parseInt(book.getYearPublished().get())
                    || genresBox.getValue()==null && publishingYearBox.getValue()==Integer.parseInt(book.getYearPublished().get()) ) {
                return true;
            }else{
                return false;
            }
        });
        SortedList<BookModel> sortedList = new SortedList<>(filteredData);

        sortedList.comparatorProperty().bind(main_table.comparatorProperty());

        main_table.setItems(sortedList);
    }

}
