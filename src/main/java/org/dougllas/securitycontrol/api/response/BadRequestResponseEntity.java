package org.dougllas.securitycontrol.api.response;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class BadRequestResponseEntity<List> extends ResponseEntity<List> {

    private List errors;

    public BadRequestResponseEntity( List errors ){
    	super(errors, HttpStatus.BAD_REQUEST);
    }

    public BadRequestResponseEntity(BindingResult result ){
        this((List) result.getAllErrors().stream().map( e -> e.getDefaultMessage() ).collect(Collectors.toList()));
    }

    public List getErrors() {
        return errors;
    }
}