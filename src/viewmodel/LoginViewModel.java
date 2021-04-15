package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class LoginViewModel {
    private StringProperty LoginUsername;
    private StringProperty LoginPassword;
    private StringProperty ErrorLabel;
    private Model model;


    public LoginViewModel(Model model){
        this.model = model;
        LoginUsername = new SimpleStringProperty();
        LoginPassword = new SimpleStringProperty();
        ErrorLabel = new SimpleStringProperty();
    }

    public void clear(){
        LoginPassword.set("");
        LoginUsername.set("");
        ErrorLabel.set("");
    }

    public boolean validateLogin(){
        // validates the login from the database
        return false;
    }

    public StringProperty getLoginUsername(){return LoginUsername;}
    public StringProperty getLoginPassword(){return LoginPassword;}
    public StringProperty getErrorLabel(){return ErrorLabel;}
}
