package view;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.BookModel;
import viewmodel.MainViewModel;

import java.time.LocalDateTime;


/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class MainViewController {
    @FXML private Label username_spot;
    @FXML private TextField search_textfield;
    @FXML private TableView<BookModel> main_table;
    @FXML private TableColumn<BookModel, String> BookIDCollum;
    @FXML private TableColumn<BookModel, String> BookNameCollum;
    @FXML private TableColumn<BookModel, String> BookAuthorCollum;
    @FXML private TableColumn<BookModel, String> YearPublishedCollum;
    @FXML private TableColumn<BookModel, String> RatingCollum;
    @FXML private ComboBox<String> genresBox;
    @FXML private ComboBox<Integer> publishingYearBox;



    private Region root;
    private MainViewModel mainViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;
    private ObservableList<String> genres;
    private ObservableList<Integer> publishingYears;
    public MainViewController (){

    }

    /**
     * Initializes the add book controller
     * @param viewHandler view handler
     * @param mainViewModel main view model
     * @param root root
     * @param viewState view state
     */
    public void init(ViewHandler viewHandler, MainViewModel mainViewModel, Region root,ViewState viewState){
        this.viewHandler = viewHandler;
        this.mainViewModel = mainViewModel;
        this.root = root;
        this.viewState=viewState;
        search_textfield.textProperty().bindBidirectional(mainViewModel.getSearch_textfield());
        username_spot.textProperty().bind(mainViewModel.getUsername_spot());

        BookIDCollum.setCellValueFactory(celldata -> celldata.getValue().getBookID());
        BookNameCollum.setCellValueFactory(celldata -> celldata.getValue().getBookName());
        BookAuthorCollum.setCellValueFactory(celldata -> celldata.getValue().getBookAuthor());
        YearPublishedCollum.setCellValueFactory(celldata -> celldata.getValue().getYearPublished());
        RatingCollum.setCellValueFactory(celldata -> celldata.getValue().getRating());

        // Search function
        FilteredList<BookModel> filteredData = new FilteredList<>(mainViewModel.getList(), b -> true);
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


        //Double click to open book
        main_table.setRowFactory( tv -> {
            TableRow<BookModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (! row.isEmpty())){
                    BookModel rowData = row.getItem();
                    viewState.setSelectedBook(rowData.getBookID().get());
                    if(mainViewModel.isAdmin()){
                        viewHandler.openView("edit");
                    } else {
                        viewHandler.openView("inspect");
                    }

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
        for(int i=LocalDateTime.now().getYear();i>=0;i--){
            publishingYears.add(i);
        }
        publishingYearBox.getItems().addAll(publishingYears);

    }

    /**
     * Returns the root
     * @return root
     */
    public Region getRoot(){return root;}

    /**
     * A method to set name
     */
    public void setName(){
        mainViewModel.setName();
    }

    /**
     * A method to reset the fields
     */
    public void reset(){mainViewModel.clear();}

    /**
     * A method to load the table
     */
    public void load(){mainViewModel.update();}

    /**
     * Button that goes to main window
     */
    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    /**
     * Button to borrow book
     */
    @FXML public void borrow_button(){
        BookModel selectedItem = main_table.getSelectionModel().getSelectedItem();
        if(selectedItem!=null){
        mainViewModel.BorrowBook(selectedItem.getBookID().get());
    }else{
            throw new IllegalArgumentException("None of the books are selected.");
        }
    }

    /**
     * Button to go to the inventory window
     */
    @FXML public void inventory_button(){
        viewHandler.openView("inventory");
    }

    /**
     * Button to go to the profile window
     */
    @FXML public void Profile_button(){viewHandler.openView("profile");}

    /**
     * Filter books by chosen year/genre or both criterias.
     */

    @FXML private void filterBooks(){
        FilteredList<BookModel> filteredData = new FilteredList<>(mainViewModel.getList(), b -> true);
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
