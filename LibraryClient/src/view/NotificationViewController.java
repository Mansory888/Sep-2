package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import viewmodel.NotificationViewModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class NotificationViewController {
    @FXML private ListView<String>  notificationList;
    private NotificationViewModel notificationViewModel;
    private Region root;
    private ViewHandler viewHandler;

    /**
     * Initializes the NotificationViewController
     * @param viewHandler view Handler
     * @param notificationViewModel notificationViewModel
     * @param root        root
     */
    public void init(ViewHandler viewHandler, NotificationViewModel notificationViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.notificationViewModel = notificationViewModel;
        this.root = root;
        notificationList.setItems(notificationViewModel.getNotifications());
    }

    /**
     * Resets the fields
     */
    public void reset() {
        // empty
    }

    /**
     * Returns the root
     * @return root
     */
    public Region getRoot() {
        return root;
    }

    /**
     * Button to go to profile window
     */
    @FXML private void BackButton(){viewHandler.openView("profile");}
}
