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

public class LogViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<String> logs;

  /**
   * Creates a log view model for the log window
   * @param model model
   */
  public LogViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this);
    logs = FXCollections.observableArrayList();
  }

  /**
   * Returns the logs
   * @return logs
   */
  public ObservableList<String> getLogs()
  {
    return logs;
  }

  /**
   * Implement the property change listener
   * @param evt event
   */
  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      logs.add(0, evt.getNewValue() + "");
    });
  }
}
