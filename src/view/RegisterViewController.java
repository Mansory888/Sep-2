package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LoginViewModel;
import viewmodel.RegisterViewModel;

public class RegisterViewController {
    @FXML private TextField RegisterUsername;
    @FXML private TextField RegisterPassword;
    @FXML private TextField RegisterEmail;
    @FXML private Label ErrorLabel;

    private Region root;
    private RegisterViewModel registerViewModel;
    private ViewHandler viewHandler;

    public RegisterViewController(){

    }

    public void init(ViewHandler viewHandler, RegisterViewModel registerViewModel, Region root){
        this.viewHandler = viewHandler;
        this.registerViewModel = registerViewModel;
        this.root = root;

        RegisterUsername.textProperty().bindBidirectional(registerViewModel.getRegisterUsername());
        RegisterPassword.textProperty().bindBidirectional(registerViewModel.getRegisterPassword());
        RegisterEmail.textProperty().bindBidirectional(registerViewModel.getRegisterEmail());
        ErrorLabel.textProperty().bind(registerViewModel.getErrorLabel());
    }

    public Region getRoot(){return root;}

    public void reset(){
        registerViewModel.clear();
    }

    @FXML public void RegisterButton(){
        registerViewModel.Register();
        if(ErrorLabel.textProperty().get().equals("")){
        viewHandler.openView("login");}
    }
}
