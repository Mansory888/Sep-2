package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class UserInventoryViewModel {
    private StringProperty search_textfield;
    private StringProperty username_spot;
    private ObservableList<BookModel> Userlist;
    private Model model;

    /**
     * Creates a view model for user inventory window
     * @param model model
     */
    public UserInventoryViewModel(Model model){
        this.model = model;
        search_textfield =  new SimpleStringProperty();
        username_spot = new SimpleStringProperty();
        Userlist = FXCollections.observableArrayList();

    }

    /**
     * method to update the table
     */
    public void update(){
        Userlist.clear();
        for(int i = 0; i < model.getUserBooksSize(); i++){
            Userlist.add(new BookModel(model.getUserBookByIndex(i)));
        }
    }

    /**
     * method to set name in the username spot
     */
    public void setName(){
        username_spot.set(model.getUsername());
    }

    /**
     * method to clear the fields
     */
    public void clear(){
        update();
        search_textfield.set("");
    }

    /**
     * method to return the book to the library
     */
    public void ReturnBook(String id){
        model.getUserInventory().returnedBookById(id);
        update();
    }

    /**
     * returns the observable list
     * @return list
     */
    public ObservableList<BookModel> getList(){
        return Userlist;
    }

    /**
     * returns the search text field
     * @return search text field
     */
    public StringProperty getSearch_textfield(){return search_textfield;}

    /**
     * returns the username spot
     * @return username spot
     */
    public StringProperty getUsername_spot(){return username_spot;}
}
