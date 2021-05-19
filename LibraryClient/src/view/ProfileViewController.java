package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.ProfileViewModel;


/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class ProfileViewController {
    @FXML private Label UsernameLabel;
    @FXML private Label RegisterLabel;
    @FXML private Label NrOfBooksLabel;
    @FXML private Label username_spot;
    @FXML private Button ManageLibraryButtonID;

    private Region root;
    private ProfileViewModel profileViewModel;
    private ViewHandler viewHandler;

    public ProfileViewController(){

    }

    /**
     * Initializes the Profile Controller
     * @param viewHandler view handler
     * @param profileViewModel profile view model
     * @param root root
     */
    public void init(ViewHandler viewHandler, ProfileViewModel profileViewModel, Region root){
        this.viewHandler = viewHandler;
        this.profileViewModel = profileViewModel;
        this.root = root;

        username_spot.textProperty().bind(profileViewModel.getUsername_spot());
        UsernameLabel.textProperty().bind(profileViewModel.getUsernameLabel());
        RegisterLabel.textProperty().bind(profileViewModel.getRegisterLabel());
        NrOfBooksLabel.textProperty().bind(profileViewModel.getNrOfBooksLabel());

        if (!profileViewModel.isAdmin()){
            System.out.println(profileViewModel.isAdmin());
            ManageLibraryButtonID.setOnAction((action) -> {
                //Do nothing
            });
            ManageLibraryButtonID.setDisable(true);
        }

    }

    /**
     * Returns the root
     * @return root
     */
    public Region getRoot(){return root;}

    /**
     * A method to reset the fields
     */
    public void reset(){
        profileViewModel.clear();
    }

    /**
     * A method to set name
     */
    public void setName(){
        profileViewModel.setName();
    }

    /**
     * Button to go to profile window
     */
    @FXML public void Profile_button(){viewHandler.openView("profile");}

    /**
     * Button to go to the main window
     */
    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    /**
     * Button to go to the manage window
     */
    @FXML public void ManageButton(){viewHandler.openView("managePage");}

    /**
     * Button to go to the notifications window
     */
    @FXML public void NotificationButton(){viewHandler.openView("notifications");}
}
