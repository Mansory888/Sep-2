package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class UserInventoryViewModel implements PropertyChangeListener {
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
        model.addListener(this);

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
        model.returnBook(id);
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

    /**
     * Listens for a property change
     * @param evt event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add book");
            alert.setHeaderText("Added Book:");
            alert.setContentText(evt.getNewValue() + "");
            alert.show();
        });
    }
}
