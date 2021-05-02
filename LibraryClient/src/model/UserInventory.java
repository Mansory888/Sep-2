package model;

import java.util.ArrayList;

/**
 * @author Nick/Rokas
 * @version 1.0
 */

public class UserInventory {
    private ArrayList<Book> books;

    /**
     * Creates a user inventory
     */
    public UserInventory(){
        this.books = new ArrayList<>();
    }

    /**
     *  adds a book to the user inventory
     * @param book
     */
    public void addBook(Book book){
        Book book1 = new Book(book.getTitle(), book.getAuthor(), book.getYearOfPublication(), book.getId(), book.getDescription());
        books.add(book1);
        book1.setBorrowed();
    }

    /**
     * gets a book by index from user inventory
     * @param index index
     * @return book
     */
    public Book getBook(int index){
        return books.get(index);
    }

    /**
     * gets size of the user inventory
     * @return size
     */
    public int getSize(){
        return books.size();
    }

    /**
     * gets the user inventory
     * @return user inventory
     */
    public ArrayList<Book> getBooks(){return books;}

    /**
     * gets a book by id from the user inventory
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
     * removes a book by id from user inventory
     * @param id id
     */
    public void removeBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                books.remove(books.get(i));
            }
        }
    }

    /**
     * Sets a book as returned by id
     * @param id id
     */
    public void returnedBookById(String id){
        for (int i = 0; i < books.size(); i++){
            if (books.get(i).getId().equals(id)){
                books.get(i).setReturned();
            }
        }
    }


}
