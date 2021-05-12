package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;


/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class LoginViewController {
    @FXML private TextField LoginUsername;
    @FXML private PasswordField LoginPassword;
    @FXML private Label ErrorLabel;

    private Region root;
    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;
    public LoginViewController(){

    }

    /**
     * Initializes the Login Controller
     * @param viewHandler view handler
     * @param loginViewModel login view model
     * @param root root
     */
    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root){
        this.viewHandler = viewHandler;
        this.loginViewModel = loginViewModel;
        this.root = root;

        LoginUsername.textProperty().bindBidirectional(loginViewModel.getLoginUsername());
        LoginPassword.textProperty().bindBidirectional(loginViewModel.getLoginPassword());
        ErrorLabel.textProperty().bind(loginViewModel.getErrorLabel());
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
    public void reset(){
        loginViewModel.clear();
    }

    /**
     * Button to login
     */
    @FXML public void LoginButton(){
        if (loginViewModel.validateLogin()){
            viewHandler.openView("main");
        }

    }

    /**
     * Button to go to the register window
     */
    @FXML public void RegisterButton(){
        viewHandler.openView("register");
    }


}
