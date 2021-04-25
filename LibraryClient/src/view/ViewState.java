package view;

public class ViewState {
private String selectedBook;
public ViewState(){
    selectedBook="";
}
public void setSelectedBook(String id){
    selectedBook=id;
}
public String getSelectedBook(){
    return selectedBook;
}
}
