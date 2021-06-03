package viewmodel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.UserType;

public class UserModel
{
  private StringProperty email;
  private StringProperty userName;
  private BooleanProperty isAdmin;

  public UserModel(UserType user){
    email = new SimpleStringProperty(user.getEmail());
    userName= new SimpleStringProperty(user.getUsername());
    isAdmin= new SimpleBooleanProperty(user.isAdmin());

  }

  /**
   * returns user name
   * @return username
   */
  public StringProperty getEmail(){return email;}


  public StringProperty getUserName(){return userName;}


  public BooleanProperty getIsAdmin(){return isAdmin;}

}
