package mk.ukim.finki.wp.lab.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException()
    {
        super("Passwords do not match");
    }

}
