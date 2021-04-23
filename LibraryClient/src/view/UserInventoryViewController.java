package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.BookModel;
import viewmodel.UserInventoryViewModel;

public class UserInventoryViewController {
    @FXML private Label username_spot;
    @FXML private TextField search_textfield;
    @FXML private TableView<BookModel> main_table;
    @FXML private TableColumn<BookModel, String> BookIDCollum;
    @FXML private TableColumn<BookModel, String> BookNameCollum;
    @FXML private TableColumn<BookModel, String> BookAuthorCollum;
    @FXML private TableColumn<BookModel, String> YearPublishedCollum;
    @FXML private TableColumn<BookModel, String> RatingCollum;


    private Region root;
    private UserInventoryViewModel userInventoryViewModel;
    private ViewHandler viewHandler;


    public UserInventoryViewController (){

    }

    public void init(ViewHandler viewHandler, UserInventoryViewModel userInventoryViewModel, Region root){
        this.viewHandler = viewHandler;
        this.userInventoryViewModel = userInventoryViewModel;
        this.root = root;

        search_textfield.textProperty().bindBidirectional(userInventoryViewModel.getSearch_textfield());
        username_spot.textProperty().bind(userInventoryViewModel.getUsername_spot());

        BookIDCollum.setCellValueFactory(celldata -> celldata.getValue().getBookID());
        BookNameCollum.setCellValueFactory(celldata -> celldata.getValue().getBookName());
        BookAuthorCollum.setCellValueFactory(celldata -> celldata.getValue().getBookAuthor());
        YearPublishedCollum.setCellValueFactory(celldata -> celldata.getValue().getYearPublished());
        RatingCollum.setCellValueFactory(celldata -> celldata.getValue().getRating());

        main_table.setItems(userInventoryViewModel.getList());


        main_table.setRowFactory( tv -> {
            TableRow<BookModel> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (! row.isEmpty())){
                    BookModel rowData = row.getItem();


                    viewHandler.openView("inspect", rowData.getBookID().get());
                }
            });
            return row;
        });



    }

    public Region getRoot(){return root;}

    public void reset(){userInventoryViewModel.clear();}
    public void load(){userInventoryViewModel.update();}

    public void setName(){
        userInventoryViewModel.setName();
    }

    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    @FXML public void Profile_button(){viewHandler.openView("profile");}

    @FXML public void search_button(){

    }
}
