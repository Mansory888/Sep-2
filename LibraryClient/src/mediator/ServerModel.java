package mediator;

import model.UserType;
import utility.observer.subject.UnnamedPropertyChangeSubject;

public interface ServerModel {
    boolean Login(String username, String password);
   void Register(UserType User);
}
