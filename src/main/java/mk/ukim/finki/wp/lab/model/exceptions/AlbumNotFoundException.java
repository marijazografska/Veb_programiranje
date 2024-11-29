package mk.ukim.finki.wp.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(Long categoryId) {
        super(String.format("Manufacturer with id %d does not exist.", categoryId));
    }
}
