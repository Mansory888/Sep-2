package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.BookModel;
import viewmodel.MainViewModel;

public class MainViewController {
    @FXML private Label username_spot;
    @FXML private TextField search_textfield;
    @FXML private TableView<BookModel> main_table;
    @FXML private TableColumn<BookModel, String> BookIDCollum;
    @FXML private TableColumn<BookModel, String> BookNameCollum;
    @FXML private TableColumn<BookModel, String> BookAuthorCollum;
    @FXML private TableColumn<BookModel, String> YearPublishedCollum;
    @FXML private TableColumn<BookModel, String> RatingCollum;



    private Region root;
    private MainViewModel mainViewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;
    public MainViewController (){

    }

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

        main_table.setItems(mainViewModel.getList());

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

    public Region getRoot(){return root;}

    public void setName(){
        mainViewModel.setName();
    }

    public void reset(){mainViewModel.clear();}


    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    @FXML public void borrow_button(){
        BookModel selectedItem = main_table.getSelectionModel().getSelectedItem();
        mainViewModel.BorrowBook(selectedItem.getBookID().get());
    }

    @FXML public void inventory_button(){
        viewHandler.openView("inventory");
    }

    @FXML public void Profile_button(){viewHandler.openView("profile");}

    @FXML public void search_button(){

    }


}
