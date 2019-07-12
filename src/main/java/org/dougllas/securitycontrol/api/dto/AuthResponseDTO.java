package org.dougllas.securitycontrol.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {

    private UserDTO user;
    private String token;
}
