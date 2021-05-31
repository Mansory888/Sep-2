package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ManagePageViewModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class ManagePageViewController {
    @FXML private TextField IDTextFiled;
    @FXML private TextField UsernameTextFiled;
    @FXML private Label username_spot;

    private Region root;
    private ManagePageViewModel managePageViewModel;
    private ViewHandler viewHandler;

    public ManagePageViewController(){

    }

    /**
     * Initializes the Manage Page Controller
     * @param viewHandler view handler
     * @param managePageViewModel mange page view model
     * @param root root
     */
    public void init(ViewHandler viewHandler, ManagePageViewModel managePageViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.managePageViewModel = managePageViewModel;
        this.root = root;

        IDTextFiled.textProperty().bindBidirectional(managePageViewModel.getIDTextFiled());
        UsernameTextFiled.textProperty().bindBidirectional(managePageViewModel.getUsernameTextFiled());
        username_spot.textProperty().bind(managePageViewModel.getUsername_spot());
    }

    /**
     * Returns the root
     *
     * @return root
     */
    public Region getRoot(){return root;}

    /**
     * A method to reset the fields
     */
    public void reset(){
        managePageViewModel.clear();
    }

    /**
     * A method to set name
     */
    public void setName(){
        managePageViewModel.setName();
    }

    /**
     * Button to go to add book window
     */
    @FXML private void AddBookButton(){viewHandler.openView("addBook");}

    /**
     * Button to go to profile window
     */
    @FXML private void Profile_button(){viewHandler.openView("profile");}

    /**
     * Button to go to the main window
     */
    @FXML private void home_button(){
        viewHandler.openView("main");
    }

    /**
     * Button to remove book
     */
    @FXML private void RemoveSubmitButton(){
        managePageViewModel.RemoveBook();
    }

    /**
     * Button to give admin privileges
     */
    @FXML private void AdminSubmitButton(){
        managePageViewModel.giveAdmin();
    }

}
