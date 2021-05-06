package mediator;

import model.UserType;

public interface ServerModel {
    void Login(String username, String password);
   void Register(UserType User);
}
