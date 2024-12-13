// AlbumNotFoundException.java
package mk.ukim.finki.wp.lab.model.exceptions;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(String message) {
        super(message);
    }

    public AlbumNotFoundException(Long id) {
        super("Album with ID " + id + " was not found");
    }
}
