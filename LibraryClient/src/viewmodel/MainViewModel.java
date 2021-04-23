package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

public class MainViewModel {
    private StringProperty search_textfield;
    private StringProperty username_spot;
    private ObservableList<BookModel> list;
    private Model model;



    public MainViewModel(Model model){
        this.model = model;
        search_textfield =  new SimpleStringProperty();
        username_spot = new SimpleStringProperty();
        list = FXCollections.observableArrayList();
        setName();

    }

    public void update(){
        list.clear();
        for (int i = 0; i < model.getLibraryBooksSize(); i++ ){
            list.add(new BookModel(model.getLibraryBookByIndex(i)));
        }
    }

    public void setName(){
        username_spot.set(model.getUsername());
    }

    public void clear(){
        update();
        search_textfield.set("");
    }

    public void BorrowBook(String id){
        model.BorrowBook(id);
    }

    public ObservableList<BookModel> getList(){
       return list;
    }

    public StringProperty getSearch_textfield(){return search_textfield;}
    public StringProperty getUsername_spot(){return username_spot;}

}
