package org.dougllas.securitycontrol.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {

    private UserDTO user;
    private String token;
}
