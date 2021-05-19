package viewmodel;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class NotificationViewModel implements PropertyChangeListener {
    private Model model;
    private ObservableList<String> notifications;


    /**
     * Creates a notification view model for the notifications window
     * @param model model
     */
    public NotificationViewModel(Model model) {
        this.model = model;
        model.addListener(this);
        notifications = FXCollections.observableArrayList();
    }

    /**
     * Returns the notifications
     * @return notifications
     */
    public ObservableList<String> getNotifications() { return notifications; }

    /**
     * Implement the property change listener
     * @param evt event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            switch (evt.getPropertyName()){
                case "notification":
                    notifications.add(0, "Book Added: " + evt.getNewValue() + "");
                    break;
            }

        });
    }
}
