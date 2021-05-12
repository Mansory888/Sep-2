package viewmodel;

import model.Model;

/**
 * @author Nick/Rokas
 * @version 1.0
 */
public class ViewModelFactory
{

  private LogViewModel logViewModel;

  /**
   * Creates a view model factory
   * @param model model
   */
  public ViewModelFactory(Model model)
  {
    logViewModel = new LogViewModel(model);
  }

  /**
   * Returns the log view Model
   * @return log view Model
   */
  public LogViewModel getLogViewModel()
  {
    return logViewModel;
  }
}
