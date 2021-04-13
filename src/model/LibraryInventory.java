package model;

import java.util.ArrayList;

public class LibraryInventory {
    private ArrayList<Book> books;

    public LibraryInventory(){
        this.books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
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


}
