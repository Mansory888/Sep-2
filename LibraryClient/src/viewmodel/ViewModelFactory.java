package viewmodel;

import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class ViewModelFactory {
    private Model model;
    private MainViewModel mainViewModel;
    private UserInventoryViewModel userInventoryViewModel;
    private ProfileViewModel profileViewModel;
    private AddBookViewModel addBookViewModel;
    private ManagePageViewModel managePageViewModel;
    private LoginViewModel loginViewModel;
    private RegisterViewModel registerViewModel;
    private InspectBookViewModel inspectBookViewModel;
    private EditBookViewModel editBookViewModel;
    private NotificationViewModel notificationViewModel;

    /**
     * Creates a view model factory that creates all view models
     * @param model model
     */
    public ViewModelFactory(Model model){
        this.model = model;
        mainViewModel = new MainViewModel(model);
        userInventoryViewModel = new UserInventoryViewModel(model);
        profileViewModel = new ProfileViewModel(model);
        addBookViewModel = new AddBookViewModel(model);
        managePageViewModel = new ManagePageViewModel(model);
        loginViewModel = new LoginViewModel(model);
        registerViewModel = new RegisterViewModel(model);
        inspectBookViewModel = new InspectBookViewModel(model);
        editBookViewModel = new EditBookViewModel(model);
        notificationViewModel = new NotificationViewModel(model);
    }

    /**
     * returns MainViewModel
     * @return MainViewModel
     */
    public MainViewModel getMainViewModel(){return mainViewModel;}

    /**
     * returns ManagePageViewModel
     * @return ManagePageViewModel
     */
    public ManagePageViewModel getManagePageViewModel(){return managePageViewModel;}

    /**
     * returns UserInventoryViewModel
     * @return UserInventoryViewModel
     */
    public UserInventoryViewModel getUserInventoryViewModel(){return userInventoryViewModel;}

    /**
     * returns ProfileViewModel
     * @return ProfileViewModel
     */
    public ProfileViewModel getProfileViewModel(){return profileViewModel;}

    /**
     * returns AddBookViewModel
     * @return AddBookViewModel
     */
    public AddBookViewModel getAddBookViewModel(){return addBookViewModel;}

    /**
     * returns LoginViewModel
     * @return LoginViewModel
     */
    public LoginViewModel getLoginViewModel(){return loginViewModel;}

    /**
     * returns RegisterViewModel
     * @return RegisterViewModel
     */
    public RegisterViewModel getRegisterViewModel(){return registerViewModel;}

    /**
     * returns InspectBookViewModel
     * @return InspectBookViewModel
     */
    public InspectBookViewModel getInspectBookViewModel(){return inspectBookViewModel;}

    /**
     * returns EditBookViewModel
     * @return EditBookViewModel
     */
    public EditBookViewModel getEditBookViewModel(){return editBookViewModel;}

    /**
     * returns NotificationViewModel
     * @return NotificationViewModel
     */
    public NotificationViewModel getNotificationViewModel(){return notificationViewModel;}
}
