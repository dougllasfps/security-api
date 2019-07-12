package org.dougllas.securitycontrol.api.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {

    private Long id;

    @NotEmpty(message = "Campo name é obrigatório.")
    private String name;
}
