package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class UserInventoryViewModel {
    private StringProperty search_textfield;
    private StringProperty username_spot;
    private ObservableList<BookModel> Userlist;
    private Model model;

    public UserInventoryViewModel(Model model){
        this.model = model;
        search_textfield =  new SimpleStringProperty();
        username_spot = new SimpleStringProperty();
        Userlist = FXCollections.observableArrayList();

    }

    public void update(){
        Userlist.clear();
        for(int i = 0; i < model.getUserBooksSize(); i++){
            Userlist.add(new BookModel(model.getUserBookByIndex(i)));
        }
    }

    public void setName(){
        username_spot.set(model.getUsername());
    }

    public void clear(){
        update();
        search_textfield.set("");
    }



    public ObservableList<BookModel> getList(){
        return Userlist;
    }

    public StringProperty getSearch_textfield(){return search_textfield;}
    public StringProperty getUsername_spot(){return username_spot;}
}
