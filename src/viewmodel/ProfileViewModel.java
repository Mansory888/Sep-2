package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ProfileViewModel {
    private StringProperty usernameLabel;
    private StringProperty RegisterLabel;
    private StringProperty NrOfBooksLabel;
    private StringProperty username_spot;
    private Model model;

    public ProfileViewModel(Model model){
        this.model = model;
        username_spot = new SimpleStringProperty();
        usernameLabel = new SimpleStringProperty();
        RegisterLabel = new SimpleStringProperty();
        NrOfBooksLabel = new SimpleStringProperty();

        // should be model.get size and username and for register idk
        usernameLabel.set("Username: ");
        RegisterLabel.set("Register: ");
        NrOfBooksLabel.set("Number of Books: ");
    }

    public void clear(){
        username_spot.set("");
        usernameLabel.set("Username: ");
        RegisterLabel.set("Register: ");
        NrOfBooksLabel.set("Number of Books: ");
    }

    public StringProperty getUsernameLabel(){return usernameLabel;}
    public StringProperty getRegisterLabel(){return RegisterLabel;}
    public StringProperty getNrOfBooksLabel(){return NrOfBooksLabel;}
    public StringProperty getUsername_spot(){return username_spot;}
}
