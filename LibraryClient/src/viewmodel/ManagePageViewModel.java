package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;

public class ManagePageViewModel {
    private StringProperty username_spot;
    private StringProperty IDTextFiled;
    private StringProperty UsernameTextFiled;
    private Model model;


    public ManagePageViewModel(Model model){
        this.model = model;
        username_spot = new SimpleStringProperty();
        IDTextFiled = new SimpleStringProperty();
        UsernameTextFiled = new SimpleStringProperty();
    }

    public void clear(){
        IDTextFiled.set("");
        UsernameTextFiled.set("");
    }

    public void RemoveBook(){
        model.getLibraryInventory().removeBookById(IDTextFiled.get());
    }

    public void setName(){
        username_spot.set(model.getUsername());
    }

    public void giveAdmin(){

    }

    public StringProperty getUsername_spot(){return username_spot;}
    public StringProperty getIDTextFiled(){return IDTextFiled;}
    public StringProperty getUsernameTextFiled(){return UsernameTextFiled;}
}
