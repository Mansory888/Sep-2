package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class ViewHandler {
    private Stage primaryStage;
    private Scene currentScene;
    private ViewModelFactory viewModelFactory;
    private MainViewController mainViewController;
    private UserInventoryViewController userInventoryViewController;
    private ProfileViewController profileViewController;
    private AddBookViewController addBookViewController;
    private ManagePageViewController managePageViewController;
    private LoginViewController loginViewController;
    private RegisterViewController registerViewController;
    private InspectBookViewController inspectBookViewController;
    private EditBookViewController editBookViewController;
    private ViewState viewState;

    /**
     * Creates a view Handler
     * @param viewModelFactory view model factory
     */
    public ViewHandler(ViewModelFactory viewModelFactory){
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
        viewState=new ViewState();
    }

    /**
     * Starts the primary stage
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        openView("login");

    }

    /**
     * A method to open view by id
     * @param id id
     */
    public void openView(String id){
        Region root = null;

        switch (id){
            case "main":
                root = loadMainView("Main.fxml");
                break;
            case "inventory":
                root = loadUserInventoryView("UserInventory.fxml");
                break;
            case "profile":
                root = loadProfileView("ProfilePage.fxml");
                break;
            case "addBook":
                root = loadAddBookView("AddBook.fxml");
                break;
            case "managePage":
                root = loadManagePageView("ManagePage.fxml");
                break;
            case "login":
                root = loadLoginView("Login.fxml");
                break;
            case "register":
                root = loadRegisterView("Register.fxml");
                break;
            case "inspect":
                root = loadInspectBookView("InspectBook.fxml");
                break;
            case "edit":
                root = loadEditBookView("EditBook.fxml");
                break;


        }
        currentScene.setRoot(root);
        String title ="";
        if(root.getUserData() != null){
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();
    }

    /**
     * A method to close the view
     */
    public void closeView(){primaryStage.close();}

    /**
     * Returns the MainView root
     * @param fxmFile fxmFile
     * @return MainView root
     */
    private Region loadMainView(String fxmFile){
        Region root = null;
        if(mainViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, viewModelFactory.getMainViewModel(), root,viewState);
                mainViewController.setName();
                mainViewController.load();
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            mainViewController.reset();
        }
        return mainViewController.getRoot();

    }

    /**
     * Returns the ProfileView root
     * @param fxmFile fxmFile
     * @return ProfileView root
     */
    private Region loadProfileView(String fxmFile){
        Region root = null;
        if(profileViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                profileViewController = loader.getController();
                profileViewController.init(this, viewModelFactory.getProfileViewModel(), root);
                profileViewController.setName();
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            profileViewController.reset();
        }
        return profileViewController.getRoot();

    }

    /**
     * Returns the UserInventoryView root
     * @param fxmFile fxmFile
     * @return UserInventoryView root
     */
    private Region loadUserInventoryView(String fxmFile){
        Region root = null;
        if(userInventoryViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                userInventoryViewController = loader.getController();
                userInventoryViewController.init(this, viewModelFactory.getUserInventoryViewModel(), root,viewState);
                userInventoryViewController.setName();
                userInventoryViewController.load();
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            userInventoryViewController.reset();
        }
        return userInventoryViewController.getRoot();

    }

    /**
     * Returns the AddBookView root
     * @param fxmFile fxmFile
     * @return AddBookView root
     */
    private Region loadAddBookView(String fxmFile){
        Region root = null;
        if(addBookViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                addBookViewController = loader.getController();
                addBookViewController.init(this, viewModelFactory.getAddBookViewModel(), root);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            addBookViewController.reset();
        }
        return addBookViewController.getRoot();

    }

    /**
     * Returns the ManagePageView root
     * @param fxmFile fxmFile
     * @return ManagePageView root
     */
    private Region loadManagePageView(String fxmFile){
        Region root = null;
        if(managePageViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                managePageViewController = loader.getController();
                managePageViewController.init(this, viewModelFactory.getManagePageViewModel(), root);
                managePageViewController.setName();
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            managePageViewController.reset();
        }
        return managePageViewController.getRoot();

    }

    /**
     * Returns the LoginView root
     * @param fxmFile fxmFile
     * @return LoginView root
     */
    private Region loadLoginView(String fxmFile){
        Region root = null;
        if(loginViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                loginViewController = loader.getController();
                loginViewController.init(this, viewModelFactory.getLoginViewModel(), root);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            loginViewController.reset();
        }
        return loginViewController.getRoot();

    }

    /**
     * Returns the RegisterView root
     * @param fxmFile fxmFile
     * @return RegisterView root
     */
    private Region loadRegisterView(String fxmFile){
        Region root = null;
        if(registerViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                registerViewController = loader.getController();
                registerViewController.init(this, viewModelFactory.getRegisterViewModel(), root);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            registerViewController.reset();
        }
        return registerViewController.getRoot();

    }

    /**
     * Returns the InspectBookView root
     * @param fxmFile fxmFile
     * @return InspectBookView root
     */
    private Region loadInspectBookView(String fxmFile){
        Region root = null;
        if(inspectBookViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                inspectBookViewController = loader.getController();
                inspectBookViewController.init(this, viewModelFactory.getInspectBookViewModel(), root, viewState);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            inspectBookViewController.reset();
        }
        return inspectBookViewController.getRoot();

    }

    /**
     * Returns the EditBookView root
     * @param fxmFile fxmFile
     * @return EditBookView root
     */
    private Region loadEditBookView(String fxmFile){
        Region root = null;
        if(editBookViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                editBookViewController = loader.getController();
                editBookViewController.init(this, viewModelFactory.getEditBookViewModel(), root, viewState);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            editBookViewController.reset();
        }
        return editBookViewController.getRoot();

    }
}
