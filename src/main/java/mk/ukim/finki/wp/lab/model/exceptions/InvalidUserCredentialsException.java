package mk.ukim.finki.wp.lab.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException{

    public InvalidUserCredentialsException()
    {
        super("Username with those credentials does not exists");
    }
}
