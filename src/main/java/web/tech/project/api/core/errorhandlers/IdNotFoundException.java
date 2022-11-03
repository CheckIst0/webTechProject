package web.tech.project.api.core.errorhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNotFoundException extends EntityNotFoundException {
    public IdNotFoundException(Long id) {
        super("Entity is not found, id = " + id);
    }
}