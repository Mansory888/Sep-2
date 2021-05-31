package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.RegisterViewModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class RegisterViewController {
    @FXML private TextField RegisterUsername;
    @FXML private PasswordField RegisterPassword;
    @FXML private TextField RegisterEmail;
    @FXML private Label ErrorLabel;

    private Region root;
    private RegisterViewModel registerViewModel;
    private ViewHandler viewHandler;

    public RegisterViewController(){

    }

    /**
     * Initializes the Register Controller
     * @param viewHandler view handler
     * @param registerViewModel register view model
     * @param root root
     */
    public void init(ViewHandler viewHandler, RegisterViewModel registerViewModel, Region root){
        this.viewHandler = viewHandler;
        this.registerViewModel = registerViewModel;
        this.root = root;

        RegisterUsername.textProperty().bindBidirectional(registerViewModel.getRegisterUsername());
        RegisterPassword.textProperty().bindBidirectional(registerViewModel.getRegisterPassword());
        RegisterEmail.textProperty().bindBidirectional(registerViewModel.getRegisterEmail());
        ErrorLabel.textProperty().bind(registerViewModel.getErrorLabel());
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
        registerViewModel.clear();
    }

    /**
     * Button to register
     */
    @FXML private void RegisterButton(){
        registerViewModel.Register();
        if(ErrorLabel.textProperty().get().equals("")){
        viewHandler.openView("login");}
    }
}
