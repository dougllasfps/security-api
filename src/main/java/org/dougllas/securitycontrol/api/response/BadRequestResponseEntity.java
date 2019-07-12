package org.dougllas.securitycontrol.api.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collection;
import java.util.stream.Collectors;

public class BadRequestResponseEntity<T> extends ResponseEntity<T> {

    private Collection<String> errors;

    public BadRequestResponseEntity( Collection<String> errors ){
        super(HttpStatus.BAD_REQUEST);
        this.errors = errors;
    }

    public BadRequestResponseEntity(BindingResult result ){
        this(result.getAllErrors().stream().map( e -> e.getDefaultMessage() ).collect(Collectors.toList()));
    }

    public Collection<String> getErrors() {
        return errors;
    }
}