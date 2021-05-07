package mediator;

import model.UserType;
import utility.observer.subject.UnnamedPropertyChangeSubject;

public interface ServerModel {
    void Login(String username, String password);
   void Register(UserType User);
}
