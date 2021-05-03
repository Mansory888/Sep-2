package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Customer;
import model.Model;
import model.UserType;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class RegisterViewModel {
    private StringProperty RegisterUsername;
    private StringProperty RegisterPassword;
    private StringProperty ErrorLabel;
    private StringProperty RegisterEmail;
    private Model model;


    /**
     * Creates a view model for Register window
     * @param model model
     */
    public RegisterViewModel(Model model){
        this.model = model;
        RegisterUsername = new SimpleStringProperty();
        RegisterPassword = new SimpleStringProperty();
        ErrorLabel = new SimpleStringProperty();
        RegisterEmail = new SimpleStringProperty();
    }

    /**
     * method to clear the fields
     */
    public void clear(){
        RegisterPassword.set("");
        RegisterUsername.set("");
        ErrorLabel.set("");
        RegisterEmail.set("");
    }

    /**
     * method to create a user when he registers
     */
   public void Register(){
       try{
           UserType user = new Customer(RegisterEmail.get(), RegisterUsername.get(), RegisterPassword.get());
           model.addUser(user);
           System.out.println("Email: "+user.getEmail()+ " Username: " +user.getUsername() +" Password: "+ user.getPassword());
           ErrorLabel.set("");
       }catch (IllegalArgumentException e){
           ErrorLabel.set(e.getMessage());
       }
   }

    /**
     * returns RegisterUsername
     * @return RegisterUsername
     */
    public StringProperty getRegisterUsername(){return RegisterUsername;}

    /**
     * returns RegisterPassword
     * @return RegisterPassword
     */
    public StringProperty getRegisterPassword(){return RegisterPassword;}

    /**
     * returns ErrorLabel
     * @return ErrorLabel
     */
    public StringProperty getErrorLabel(){return ErrorLabel;}

    /**
     * returns RegisterEmail
     * @return RegisterEmail
     */
    public StringProperty getRegisterEmail(){return RegisterEmail;}
}
