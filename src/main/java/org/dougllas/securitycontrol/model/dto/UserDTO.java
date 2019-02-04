package org.dougllas.securitycontrol.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.validation.FieldMatch;

import javax.validation.constraints.NotNull;

/**
 * Criado por dougllas.sousa em 14/03/2018.
 */

@FieldMatch(first = "password", second = "passwordMatch", message = "Senhas não conferem.")
@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull(message = "Campo name é obrigatório.")
    private String name;

    @NotNull(message = "Campo username é obrigatório.")
    private String username;

    @JsonIgnore
    @NotNull(message = "Campo password é obrigatório.")
    private String password;

    @JsonIgnore
    @NotNull(message = "Campo passwordMatch é obrigatório.")
    private String passwordMatch;

    @JsonIgnore
    private boolean authenticated;

    @JsonIgnore
    private String token;

    public static UserDTO entityToDto(User user){
        UserDTO dto = new UserDTO();
//        dto.setPassword(user.getPassword());
        dto.setUsername(user.getUsername());
        dto.setAuthenticated(user.getId() != null);
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public static User toEntity(UserDTO dto){
        User user = new User();
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setId(dto.getId());
        user.setName(dto.getName());
        return user;
    }
}