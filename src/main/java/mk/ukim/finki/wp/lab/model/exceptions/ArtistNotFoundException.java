// ArtistNotFoundException.java
package mk.ukim.finki.wp.lab.model.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException(String message) {
        super(message);
    }

    public ArtistNotFoundException(Long id) {
        super("Artist with ID " + id + " was not found");
    }
}
