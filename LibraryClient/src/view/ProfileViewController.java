package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import viewmodel.ProfileViewModel;


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

    public Region getRoot(){return root;}

    public void reset(){
        profileViewModel.clear();
    }

    public void setName(){
        profileViewModel.setName();
    }

    @FXML public void Profile_button(){viewHandler.openView("profile");}

    @FXML public void home_button(){
        viewHandler.openView("main");
    }

    @FXML public void ManageButton(){viewHandler.openView("managePage");}
}
