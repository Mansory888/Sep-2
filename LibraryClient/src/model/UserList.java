package model;

import java.util.ArrayList;

/**
 * @author the other two
 * @version 1.0
 */

public class UserList {
  private ArrayList<UserType> users;

  /**
   * Creates a user list
   */
  public UserList(){
    this.users = new ArrayList<>();
  }

  /**
   * adds a user to the userlist
   * @param user user
   */
  public void addUser(UserType user){
    users.add(user);
  }


  /**
   * gets a user by index
   * @param index index
   * @return user
   */
  public UserType getUser(int index){
    return users.get(index);
  }

  /**
   * gets the size of the userlist
   * @return size
   */
  public int getSize(){
    return users.size();
  }

  /**
   *  gets the whole userlist
   * @return users
   */
  public ArrayList<UserType> getUsers(){return users;}


  /**
   * removes users by id
   *
   * @param id id
   */
  public void removeUser(String id){
    for (int i = 0; i < users.size(); i++){
      if (users.get(i).getUsername().equals(id)){
        users.remove(users.get(i));
      }
    }
  }


}

