package viewmodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class LoginViewModel {
    private StringProperty LoginUsername;
    private StringProperty LoginPassword;
    private StringProperty ErrorLabel;
    private BooleanProperty loginVerified;
    private Model model;
    private int loginVerifyX2 = 1;




    /**
     * Creates a view model for Login window
     * @param model model
     */
    public LoginViewModel(Model model){
        this.model = model;
        LoginUsername = new SimpleStringProperty();
        LoginPassword = new SimpleStringProperty();
        ErrorLabel = new SimpleStringProperty();
        loginVerified = new SimpleBooleanProperty();
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
    public synchronized boolean validateLogin(){
            loginVerified.set(true);

        model.Login(LoginUsername.get(), LoginPassword.get());
        if(model.getUser()!=null){
            model.setUsername(LoginUsername.get());
            loginVerified.set(true);
            return loginVerified.getValue();
        }
        ErrorLabel.set(model.getErrorLabel());
            loginVerified.set(false);
        return loginVerified.getValue();
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
    public boolean isLoginVerified() {
        return loginVerified.get();
    }

    public BooleanProperty loginVerifiedProperty() {
        return loginVerified;
    }

}
