package org.dougllas.securitycontrol.api.resource;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.dougllas.securitycontrol.api.dto.AuthRequestDTO;
import org.dougllas.securitycontrol.api.dto.AuthResponseDTO;
import org.dougllas.securitycontrol.api.dto.UserDTO;
import org.dougllas.securitycontrol.api.response.BadRequestResponseEntity;
import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.security.jwt.JwtTokenService;
import org.dougllas.securitycontrol.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthResource implements Serializable {

    private final UserService userService;
    private final JwtTokenService tokenService;

    @GetMapping
    public List<User> find(){
        return null;
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken( @RequestParam("token") String token ){
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

    @PostMapping
    public ResponseEntity<?> auth( @RequestBody AuthRequestDTO requestDTO  ){

        Optional<User> user = userService.authenticate(requestDTO.getUsername(), requestDTO.getPassword());

        if(user.isPresent()){
            AuthResponseDTO dto = new AuthResponseDTO();
            dto.setUser(UserDTO.entityToDto(user.get()));
            dto.setToken(tokenService.obterToken(user.get()));
            return ResponseEntity.ok(dto);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário e/ou senha não conferem.");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new BadRequestResponseEntity(bindingResult);
        }

        User user = UserDTO.toEntity(userDTO);
        userService.save(user);
        userDTO = UserDTO.entityToDto(user);
        userDTO.setAuthenticated(true);
        userDTO.setToken(tokenService.obterToken(user));

        return ResponseEntity.ok(userDTO);
    }
}