package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Customer;
import model.Model;
import model.UserType;

public class RegisterViewModel {
    private StringProperty RegisterUsername;
    private StringProperty RegisterPassword;
    private StringProperty ErrorLabel;
    private StringProperty RegisterEmail;
    private Model model;


    public RegisterViewModel(Model model){
        this.model = model;
        RegisterUsername = new SimpleStringProperty();
        RegisterPassword = new SimpleStringProperty();
        ErrorLabel = new SimpleStringProperty();
        RegisterEmail = new SimpleStringProperty();
    }

    public void clear(){
        RegisterPassword.set("");
        RegisterUsername.set("");
        ErrorLabel.set("");
        RegisterEmail.set("");
    }

   public void Register(){
       try{
           UserType user = new Customer(RegisterEmail.get(), RegisterUsername.get(), RegisterPassword.get());
           model.addUser(user);
           System.out.println("Email: "+user.getEmail()+ " Username: " +user.getUsername() +" Password: "+ user.getPassword());
           ErrorLabel.set("");
       }catch (IllegalArgumentException e){
           ErrorLabel.set(e.getMessage());
       }
       //System.out.println("Email: "+user.getEmail()+ " Username: " +user.getUsername() +" Password: "+ user.getPassword());
   }

    public StringProperty getRegisterUsername(){return RegisterUsername;}
    public StringProperty getRegisterPassword(){return RegisterPassword;}
    public StringProperty getErrorLabel(){return ErrorLabel;}
    public StringProperty getRegisterEmail(){return RegisterEmail;}
}
