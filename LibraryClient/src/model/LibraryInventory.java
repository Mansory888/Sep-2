package model;

import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class LibraryInventory {
    private ArrayList<Book> books;

    /**
     * Creates a library inventory
     */
    public LibraryInventory(){
        this.books = new ArrayList<>();
    }

    /**
     * adds a book to the library
     * @param book book
     */
    public void addBook(Book book){
        books.add(book);
    }


    /**
     * gets a book by index
     * @param index index
     * @return book
     */
    public Book getBook(int index){
        return books.get(index);
    }

    /**
     * gets the size of the library
     * @return size
     */
    public int getSize(){
        return books.size();
    }

    /**
     *  gets the whole library
     * @return library
     */
    public ArrayList<Book> getBooks(){return books;}

    /**
     * gets the book by id
     * @param id id
     * @return book
     */
    public Book getBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                return books.get(i);
            }
        }
        return null;
    }


    /**
     * removes book by id
     *
     * @param id id
     */
    public void removeBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                books.remove(books.get(i));
            }
        }
    }


}
