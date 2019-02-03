package org.dougllas.securitycontrol.api.controller;

import org.dougllas.securitycontrol.api.response.ApiError;
import org.dougllas.securitycontrol.model.dto.AuthResponseDTO;
import org.dougllas.securitycontrol.model.dto.UserDTO;
import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.security.jwt.JwtTokenService;
import org.dougllas.securitycontrol.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController implements Serializable {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenService tokenService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/validate")
    public ResponseEntity validaToken(@RequestParam("token") String token){
        boolean valido = tokenService.tokenValido(token);

        if(!valido){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Sessão inválida." );
        }

        String userName = tokenService.getUsernameFromToken(token);
        User user = userService.loadUserByUsername(userName);

        AuthResponseDTO dto = new AuthResponseDTO();
        dto.setUser(UserDTO.entityToDto(user));
        dto.setToken(token);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/auth")
    public ResponseEntity login(
            @RequestParam("username") String login,
            @RequestParam("password") String pass  ){

        Optional<User> user = userService.autenticarUsuario(login, pass);

        if(user.isPresent()){
            AuthResponseDTO dto = new AuthResponseDTO();
            dto.setUser(UserDTO.entityToDto(user.get()));
            dto.setToken(tokenService.obterToken(user.get()));
            return ResponseEntity.ok(dto);
        }

        return new ResponseEntity(new ApiError("Usuário e/ou senha não conferem."), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/signup")
    public ResponseEntity cadastro(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            ApiError errors = new ApiError();
            bindingResult.getAllErrors().forEach( e -> errors.addError(e.getDefaultMessage()) );
            return ResponseEntity.badRequest().body(errors);
        }

        User user = UserDTO.toEntity(userDTO);
        userService.cadastrarUsuario(user);
        userDTO = UserDTO.entityToDto(user);
        userDTO.setAuthenticated(true);
        userDTO.setToken(tokenService.obterToken(user));

        return ResponseEntity.ok(userDTO);
    }
}