package model;

import java.util.ArrayList;

public class UserInventory {
    private ArrayList<Book> books;

    public UserInventory(){
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
        book.setBorrowed();
    }

    public Book getBook(int index){
        return books.get(index);
    }

    public int getSize(){
        return books.size();
    }

    public ArrayList<Book> getBooks(){return books;}

    public Book getBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                return books.get(i);
            }
        }
        return null;
    }

    public void removeBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                books.remove(books.get(i));
            }
        }
    }

    public void returnedBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                books.get(i).setReturned();
            }
        }
    }


}
