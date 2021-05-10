package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class ProfileViewModel {
    private StringProperty usernameLabel;
    private StringProperty RegisterLabel;
    private StringProperty NrOfBooksLabel;
    private StringProperty username_spot;
    private Model model;

    /**
     * Creates a view model for profile view window
     * @param model model
     */
    public ProfileViewModel(Model model){
        this.model = model;
        username_spot = new SimpleStringProperty();
        usernameLabel = new SimpleStringProperty();
        RegisterLabel = new SimpleStringProperty();
        NrOfBooksLabel = new SimpleStringProperty();

        // should be model.get size and username and for register idk
        RegisterLabel.set("Register: ");
    }

    /**
     * method to clear the fields
     */
    public void clear(){
        NrOfBooksLabel.set("Number of Books: "+ model.getUserInventory().getSize());
    }

    /**
     * method to set the name and other information about the user
     */
    public void setName(){
        RegisterLabel.set("Registered On: "+ model.getUserRegistrationDate());
        usernameLabel.set("Username: "+ model.getUsername());
        NrOfBooksLabel.set("Number of Books: "+ model.getUserInventory().getSize());
        username_spot.set(model.getUsername());
    }

    /**
     * Returns if the user is admin
     * @return id admin
     */
    public boolean isAdmin(){
        return model.isAdmin();
    }

    /**
     * returns username label
     * @return username label
     */
    public StringProperty getUsernameLabel(){return usernameLabel;}

    /**
     * returns register label
     * @return register label
     */
    public StringProperty getRegisterLabel(){return RegisterLabel;}

    /**
     * returns the number of book label
     * @return the number of book label
     */
    public StringProperty getNrOfBooksLabel(){return NrOfBooksLabel;}

    /**
     * returns the username spot
     * @return username spot
     */
    public StringProperty getUsername_spot(){return username_spot;}
}
