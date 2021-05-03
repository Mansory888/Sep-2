package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class ManagePageViewModel {
    private StringProperty username_spot;
    private StringProperty IDTextFiled;
    private StringProperty UsernameTextFiled;
    private Model model;


    /**
     * Creates a view model for manage page window
     * @param model model
     */
    public ManagePageViewModel(Model model){
        this.model = model;
        username_spot = new SimpleStringProperty();
        IDTextFiled = new SimpleStringProperty();
        UsernameTextFiled = new SimpleStringProperty();
    }

    /**
     * method to clear the fields
     */
    public void clear(){
        IDTextFiled.set("");
        UsernameTextFiled.set("");
    }

    /**
     * method to remove a book from the library
     */
    public void RemoveBook(){
        model.getLibraryInventory().removeBookById(IDTextFiled.get());
    }

    /**
     * method to set the username spot
     */
    public void setName(){
        username_spot.set(model.getUsername());
    }

    /**
     * method to give admin privileges to a user
     */
    public void giveAdmin(){

    }

    /**
     * returns username spot
     * @return username spot
     */
    public StringProperty getUsername_spot(){return username_spot;}

    /**
     * returns the id text field
     * @return id text field
     */
    public StringProperty getIDTextFiled(){return IDTextFiled;}

    /**
     * returns the username text field
     * @return username text field
     */
    public StringProperty getUsernameTextFiled(){return UsernameTextFiled;}
}
