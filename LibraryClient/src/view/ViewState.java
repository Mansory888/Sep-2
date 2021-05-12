package view;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class ViewState {
    private String selectedBook;

    /**
     * Creates a view state
     */
    public ViewState() {
        selectedBook = "";
    }

    /**
     * Sets the selected book
     * @param id id
     */
    public void setSelectedBook(String id) {
        selectedBook = id;
    }

    /**
     * Returns the selected book
     * @return selected book
     */
    public String getSelectedBook() {
        return selectedBook;
    }
}
