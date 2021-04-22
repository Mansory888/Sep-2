package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;
import viewmodel.ProfileViewModel;


public class LoginViewController {
    @FXML private TextField LoginUsername;
    @FXML private PasswordField LoginPassword;
    @FXML private Label ErrorLabel;

    private Region root;
    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    public LoginViewController(){

    }

    public void init(ViewHandler viewHandler, LoginViewModel loginViewModel, Region root){
        this.viewHandler = viewHandler;
        this.loginViewModel = loginViewModel;
        this.root = root;

        LoginUsername.textProperty().bindBidirectional(loginViewModel.getLoginUsername());
        LoginPassword.textProperty().bindBidirectional(loginViewModel.getLoginPassword());
        ErrorLabel.textProperty().bind(loginViewModel.getErrorLabel());
    }

    public Region getRoot(){return root;}

    public void reset(){
        loginViewModel.clear();
    }

    @FXML public void LoginButton(){
        //The login protocol:
//        try {
//            if (loginViewModel.validateLogin()){
//                viewHandler.openView("main");
//            }
//
//        } catch (Exception e){
//
//        }
        // for now
        loginViewModel.validateLogin();
        viewHandler.openView("main");

    }

    @FXML public void RegisterButton(){
        viewHandler.openView("register");
    }


}
