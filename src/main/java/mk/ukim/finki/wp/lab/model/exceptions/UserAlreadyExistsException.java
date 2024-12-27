package mk.ukim.finki.wp.lab.model.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException()
    {
        super("There already exists an user with those credentials");
    }
}
