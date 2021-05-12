package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import viewmodel.LogViewModel;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class LogViewController
{
  @FXML private ListView<String> logList;
  private LogViewModel viewModel;
  private Region root;
  private ViewHandler viewHandler;

  /**
   * Initializes the LogViewController
   * @param viewHandler view Handler
   * @param viewModel view Model
   * @param root root
   */
  public void init(ViewHandler viewHandler, LogViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    logList.setItems(viewModel.getLogs());

  }

  /**
   * Resets the fields
   */
  public void reset()
  {
    // empty
  }

  /**
   * Returns the root
   * @return root
   */
  public Region getRoot()
  {
    return root;
  }

}
