package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

/**
 * @author the other two
 * @version 1.0
 */
public class UserListViewModel {
  private StringProperty search_textfield;
  private StringProperty username_spot;
  private ObservableList<UserModel> Userlist;
  private Model model;

  /**
   * Creates a view model for user list window
   * @param model model
   */
  public UserListViewModel(Model model){
    this.model = model;
    search_textfield =  new SimpleStringProperty();
    username_spot = new SimpleStringProperty();
    Userlist = FXCollections.observableArrayList();


  }

  /**
   * method to update the table
   */
  public void update(){
    Userlist.clear();
    for(int i = 0; i < model.getUserListSize(); i++){
      Userlist.add(new UserModel(model.getUserListByIndex(i)));
    }
  }

  /**
   * method to set name in the username spot
   */
  public void setName(){
    username_spot.set(model.getUsername());
  }

  /**
   * method to clear the fields
   */
  public void clear(){
    update();
    search_textfield.set("");
  }

  /**
   * returns the observable list
   * @return list
   */
  public ObservableList<UserModel> getList(){
    return Userlist;
  }

  /**
   * returns the search text field
   * @return search text field
   */
  public StringProperty getSearch_textfield(){return search_textfield;}

  /**
   * returns the username spot
   * @return username spot
   */
  public StringProperty getUsername_spot(){return username_spot;}

}

