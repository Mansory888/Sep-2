package view;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.UserListViewModel;
import viewmodel.UserModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class UserListViewController
{
  @FXML private TextField search_textfield;
  @FXML private TableView<UserModel> main_table;
  @FXML private TableColumn<UserModel, String> UserNameCollumn;
  @FXML private TableColumn<UserModel, Boolean> IsAdminCollumn;


  private Region root;
  private UserListViewModel userListViewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;

  public UserListViewController(){

  }

  /**
   * Initializes the User Inventory Controller
   * @param viewHandler view handler
   * @param userListViewModel user inventory  view model
   * @param root root
   * @param viewState view state
   */
  public void init(ViewHandler viewHandler, UserListViewModel userListViewModel, Region root, ViewState viewState){
    this.viewHandler = viewHandler;
    this.userListViewModel = userListViewModel;
    this.root = root;
    this.viewState=viewState;
    search_textfield.textProperty().bindBidirectional(userListViewModel.getSearch_textfield());

    UserNameCollumn.setCellValueFactory(celldata -> celldata.getValue().getUserName());
    IsAdminCollumn.setCellValueFactory(celldata -> celldata.getValue().getIsAdmin());


    // search function
    FilteredList<UserModel> filteredData = new FilteredList<>(userListViewModel.getList(), b -> true);

    search_textfield.textProperty().addListener(((observable, oldValue, newValue) ->{
      filteredData.setPredicate(user -> {
        if(newValue == null || newValue.isEmpty()){
          return true;
        }

        String lowerCaseFilter = newValue.toLowerCase();

        if(user.getUserName().get().toLowerCase().contains(lowerCaseFilter)){
          return true;
        } else {
          return false;
        }
      });
    } ));

    SortedList<UserModel> sortedList = new SortedList<>(filteredData);

    sortedList.comparatorProperty().bind(main_table.comparatorProperty());

    main_table.setItems(sortedList);


    main_table.setRowFactory( tv -> {
      TableRow<UserModel> row = new TableRow<>();
      row.setOnMouseClicked(event -> {
        if(event.getClickCount() == 2 && (! row.isEmpty())){
          UserModel rowData = row.getItem();
          viewState.setSelectedBook(rowData.getUserName().get());

          viewHandler.openView("inspect");
        }
      });
      return row;
    });

    // filters
    // genres


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
  public void reset(){userListViewModel.clear();}

  /**
   * A method to load the table
   */
  public void load(){userListViewModel.update();}

  /**
   * A method to set name
   */
  public void setName(){
    userListViewModel.setName();
  }

  /**
   * Button to go to the main window
   */
  @FXML private void home_button(){
    viewHandler.openView("main");
  }

  /**
   * Button to go to profile window
   */
  @FXML private void Profile_button(){viewHandler.openView("profile");}



  @FXML private void createUser()
  {
  }

  @FXML private void changeUserRole()
  {
  }

  @FXML private void deleteUser()
  {
  }
}