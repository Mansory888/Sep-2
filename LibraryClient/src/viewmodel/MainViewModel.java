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
public class MainViewModel {
    private StringProperty search_textfield;
    private StringProperty username_spot;
    private ObservableList<BookModel> list;
    private Model model;



    /**
     * Creates a view model for main window
     * @param model model
     */
    public MainViewModel(Model model){
        this.model = model;
        search_textfield =  new SimpleStringProperty();
        username_spot = new SimpleStringProperty();
        list = FXCollections.observableArrayList();
        setName();

    }

    /**
     * method to update the table
     */
    public void update(){
        list.clear();
        for (int i = 0; i < model.getLibraryBooksSize(); i++ ){
            list.add(new BookModel(model.getLibraryBookByIndex(i)));
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
     * method that borrows selected book by id
     * @param id id
     */
    public void BorrowBook(String id){
        boolean isInInventory=false;
        for(int i=0;i<model.getUserInventory().getSize();i++){
            if(model.getUserInventory().getBook(i).getId().equals(id)){
                isInInventory = true;
            }
        }
        if (isInInventory){
            if (model.getUserInventory().getBookById(id).getIsReturned()){
                model.BorrowBook(id);
            }
        } else {
            model.BorrowBook(id);
        }

    }

    /**
     * returns the observable list
     * @return list
     */
    public ObservableList<BookModel> getList(){
       return list;
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
