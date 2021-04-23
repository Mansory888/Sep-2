package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

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

    public ViewHandler(ViewModelFactory viewModelFactory){
        this.viewModelFactory = viewModelFactory;
        this.currentScene = new Scene(new Region());
    }

    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        openView("login");

    }

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

    public void openView(String id, String bookId){
        Region root = null;

        switch (id){
            case "inspect":
                root = loadInspectBookView("InspectBook.fxml", bookId);
                break;
            case "editBook":
                root = loadEditBookView("EditBook.fxml",bookId);


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


    public void closeView(){primaryStage.close();}

    private Region loadMainView(String fxmFile){
        Region root = null;
        if(mainViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, viewModelFactory.getMainViewModel(), root);
                mainViewController.setName();
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            mainViewController.reset();
        }
        return mainViewController.getRoot();

    }

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


    private Region loadUserInventoryView(String fxmFile){
        Region root = null;
        if(userInventoryViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                userInventoryViewController = loader.getController();
                userInventoryViewController.init(this, viewModelFactory.getUserInventoryViewModel(), root);
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

    private Region loadInspectBookView(String fxmFile, String bookId){
        Region root = null;
        if(inspectBookViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                inspectBookViewController = loader.getController();
                inspectBookViewController.init(this, viewModelFactory.getInspectBookViewModel(), root, bookId);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            inspectBookViewController.reset();
        }
        return inspectBookViewController.getRoot();

    }

    private Region loadEditBookView(String fxmFile, String bookId){
        Region root = null;
        if(editBookViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmFile));
                root = loader.load();
                editBookViewController = loader.getController();
                editBookViewController.init(this, viewModelFactory.getEditBookViewModel(), root, bookId);
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            editBookViewController.reset();
        }
        return editBookViewController.getRoot();

    }
}
