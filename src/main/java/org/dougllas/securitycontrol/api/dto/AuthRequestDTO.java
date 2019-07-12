package org.dougllas.securitycontrol.api.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AuthRequestDTO {

	@NotEmpty(message = "Campo username é obrigatório.")
    private String username;

    @NotEmpty(message = "Campo password é obrigatório.")
    private String password;
}
