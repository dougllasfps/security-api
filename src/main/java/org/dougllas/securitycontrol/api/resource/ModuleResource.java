package org.dougllas.securitycontrol.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.dougllas.securitycontrol.api.dto.ModuleDTO;
import org.dougllas.securitycontrol.api.response.BadRequestResponseEntity;
import org.dougllas.securitycontrol.model.entity.Module;
import org.dougllas.securitycontrol.service.ModuleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/modules")
public class ModuleResource {

	private final ModuleService service;
	
	@PostMapping
	public ResponseEntity save( @Valid @RequestBody ModuleDTO module , BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
            return new BadRequestResponseEntity(bindingResult);
        }
		
		log.info("saving new module {} " , module.getName());
		Module entity = new Module(null, module.getName());
		service.save(entity);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity update( @PathVariable("id") Long id, @Valid @RequestBody ModuleDTO module , BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
            return new BadRequestResponseEntity(bindingResult);
        }
		
		log.info("updating module {} " , id);
		return service.findOne(id).map( m -> {
			m.setName(module.getName());
			service.update(m);
			return ResponseEntity.ok(m);
		}).orElseGet( () -> new ResponseEntity(HttpStatus.NOT_FOUND) );
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete( @PathVariable("id") Long id) {
		log.info("deleting module {} " , id);
		return service.findOne(id).map( m -> {
			service.delete(m);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet( () -> new ResponseEntity(HttpStatus.NOT_FOUND) );
	}
	
	@GetMapping
	public List<Module> find( @RequestParam("name") String name ) {
		log.info("searching module by name {} " , name);
		return service.find(Module.builder().name(name).build());
	}
}