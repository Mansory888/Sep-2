package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class LoginViewModel {
    private StringProperty LoginUsername;
    private StringProperty LoginPassword;
    private StringProperty ErrorLabel;
    private Model model;


    /**
     * Creates a view model for Login window
     * @param model model
     */
    public LoginViewModel(Model model){
        this.model = model;
        LoginUsername = new SimpleStringProperty();
        LoginPassword = new SimpleStringProperty();
        ErrorLabel = new SimpleStringProperty();
    }

    /**
     * method to clear the fields
     */
    public void clear(){
        LoginPassword.set("");
        LoginUsername.set("");
        ErrorLabel.set("");
    }

    /**
     * method to validate the login
     */
    public boolean validateLogin(){
        model.Login(LoginUsername.get(), LoginPassword.get());
        if()
        model.setUsername(LoginUsername.get());
        return false;
    }

    /**
     * returns loginUsername
     * @return loginUsername
     */
    public StringProperty getLoginUsername(){return LoginUsername;}

    /**
     * returns LoginPassword
     * @return LoginPassword
     */
    public StringProperty getLoginPassword(){return LoginPassword;}

    /**
     * returns ErrorLabel
     * @return ErrorLabel
     */
    public StringProperty getErrorLabel(){return ErrorLabel;}
}
