package org.dougllas.securitycontrol.api.controller;

import org.dougllas.securitycontrol.api.response.ApiError;
import org.dougllas.securitycontrol.model.dto.ModuleDTO;
import org.dougllas.securitycontrol.model.entity.Module;
import org.dougllas.securitycontrol.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/modules")
public class ModuleController  {

    public static final String MSG_NOT_FOUND = "Entidade não encontrada para o Id informado.";
    private final ModuleService moduleService;

    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ModuleDTO dto, BindingResult result){

        if(result.hasErrors()){
           ApiError errors = new ApiError();
           result.getAllErrors().forEach( e -> errors.addError(e.getDefaultMessage()) );
           return ResponseEntity.badRequest().body(errors);
        }

        Module saved = moduleService.save(convertDTO(dto));
        return new ResponseEntity(saved , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity update( @PathVariable("id") Long id , @Valid @RequestBody ModuleDTO dto , BindingResult result ){

        if(result.hasErrors()){
            ApiError errors = new ApiError();
            result.getAllErrors().forEach( e -> errors.addError(e.getDefaultMessage()) );
            return ResponseEntity.badRequest().body(errors);
        }

        return moduleService.findOne(id).map( module -> {
            module.setId(dto.getId());
            module.setName(dto.getName());
            moduleService.save(module);
            return (ResponseEntity) ResponseEntity.ok(module);
        } ).orElseGet( () ->new ResponseEntity(new ApiError(MSG_NOT_FOUND), HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(moduleService.findAll());
    }

    @GetMapping("{id")
    public ResponseEntity findOne(@PathVariable("id") Long id){
        return moduleService.findOne(id).map( e -> (ResponseEntity) ResponseEntity.ok(e) ).orElseGet( () -> ResponseEntity.badRequest().body(new ApiError("Entidade não encontrada.")));
    }

    private Module convertDTO(ModuleDTO dto){
        return Module.builder().id(dto.getId()).name(dto.getName()).build();
    }
}