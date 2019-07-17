package org.dougllas.securitycontrol.api.resource;

import javax.validation.Valid;

import org.dougllas.securitycontrol.api.dto.ModuleDTO;
import org.dougllas.securitycontrol.api.response.BadRequestResponseEntity;
import org.dougllas.securitycontrol.model.entity.Module;
import org.dougllas.securitycontrol.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/modules")
public class ModuleResource {

	private final ModuleService service;
	
	@PostMapping
	public ResponseEntity save( @Valid @RequestBody ModuleDTO module , BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
            return new BadRequestResponseEntity(bindingResult);
        }
		
		Module entity = new Module(null, module.getName());
		service.save(entity);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
