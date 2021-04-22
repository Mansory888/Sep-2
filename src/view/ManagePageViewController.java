package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ManagePageViewModel;
import viewmodel.ProfileViewModel;



public class ManagePageViewController {
    @FXML private TextField IDTextFiled;
    @FXML private TextField UsernameTextFiled;
    @FXML private Label username_spot;

    private Region root;
    private ManagePageViewModel managePageViewModel;
    private ViewHandler viewHandler;

    public ManagePageViewController(){

    }

    public void init(ViewHandler viewHandler, ManagePageViewModel managePageViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.managePageViewModel = managePageViewModel;
        this.root = root;

        IDTextFiled.textProperty().bindBidirectional(managePageViewModel.getIDTextFiled());
        UsernameTextFiled.textProperty().bindBidirectional(managePageViewModel.getUsernameTextFiled());
        username_spot.textProperty().bind(managePageViewModel.getUsername_spot());
    }

    public Region getRoot(){return root;}

    public void reset(){
        managePageViewModel.clear();
    }

    public void setName(){
        managePageViewModel.setName();
    }

    @FXML public void AddBookButton(){viewHandler.openView("addBook");}

    @FXML public void Profile_button(){viewHandler.openView("profile");}

    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    @FXML public void RemoveSubmitButton(){
        managePageViewModel.RemoveBook();
    }

    @FXML public void AdminSubmitButton(){
        managePageViewModel.giveAdmin();
    }

}
