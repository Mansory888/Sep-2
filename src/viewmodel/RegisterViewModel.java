package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class RegisterViewModel {
    private StringProperty RegisterUsername;
    private StringProperty RegisterPassword;
    private StringProperty ErrorLabel;
    private Model model;


    public RegisterViewModel(Model model){
        this.model = model;
        RegisterUsername = new SimpleStringProperty();
        RegisterPassword = new SimpleStringProperty();
        ErrorLabel = new SimpleStringProperty();
    }

    public void clear(){
        RegisterPassword.set("");
        RegisterUsername.set("");
        ErrorLabel.set("");
    }

   public void Register(){

   }

    public StringProperty getRegisterUsername(){return RegisterUsername;}
    public StringProperty getRegisterPassword(){return RegisterPassword;}
    public StringProperty getErrorLabel(){return ErrorLabel;}
}
