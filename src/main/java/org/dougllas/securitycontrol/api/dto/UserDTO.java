package org.dougllas.securitycontrol.api.dto;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.validation.FieldMatch;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * Criado por dougllas.sousa em 14/03/2018.
 */

@FieldMatch(first = "password", second = "passwordMatch", message = "Senhas não conferem.")
@Getter
@Setter
public class UserDTO {

    private Long id;
    
    private String sinceDate;

    @NotEmpty(message = "Campo name é obrigatório.")
    private String name;

    @NotEmpty(message = "Campo username é obrigatório.")
    private String username;

    @JsonIgnore
    @NotEmpty(message = "Campo password é obrigatório.")
    private String password;

    @JsonIgnore
    @NotEmpty(message = "Campo passwordMatch é obrigatório.")
    private String passwordMatch;
    
    @NotEmpty(message = "Campo email é obrigatório.")
    private String email;

    @JsonIgnore
    private boolean authenticated;

    @JsonIgnore
    private String token;

    public static UserDTO entityToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setAuthenticated(user.getId() != null);
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        Optional.ofNullable(user.getSince()).ifPresent( date -> dto.setSinceDate(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        return dto;
    }

    public static User toEntity(UserDTO dto){
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }
}