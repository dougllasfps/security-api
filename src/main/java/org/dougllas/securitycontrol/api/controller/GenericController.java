package org.dougllas.securitycontrol.api.controller;

import org.dougllas.securitycontrol.api.response.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public abstract class GenericController {

    public ResponseEntity handleErrors(BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ApiError errors = new ApiError();
            bindingResult.getAllErrors().forEach( e -> errors.addError(e.getDefaultMessage()) );
            return ResponseEntity.badRequest().body( errors );
        }

        return null;
    }
}
