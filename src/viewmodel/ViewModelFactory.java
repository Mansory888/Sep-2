package viewmodel;

import model.Model;

public class ViewModelFactory {
    private Model model;
    private MainViewModel mainViewModel;
    private UserInventoryViewModel userInventoryViewModel;
    private ProfileViewModel profileViewModel;
    private AddBookViewModel addBookViewModel;
    private ManagePageViewModel managePageViewModel;

    public ViewModelFactory(Model model){
        this.model = model;
        mainViewModel = new MainViewModel(model);
        userInventoryViewModel = new UserInventoryViewModel(model);
        profileViewModel = new ProfileViewModel(model);
        addBookViewModel = new AddBookViewModel(model);
        managePageViewModel = new ManagePageViewModel(model);
    }

    public MainViewModel getMainViewModel(){return mainViewModel;}
    public ManagePageViewModel getManagePageViewModel(){return managePageViewModel;}
    public UserInventoryViewModel getUserInventoryViewModel(){return userInventoryViewModel;}
    public ProfileViewModel getProfileViewModel(){return profileViewModel;}
    public AddBookViewModel getAddBookViewModel(){return addBookViewModel;}
}
