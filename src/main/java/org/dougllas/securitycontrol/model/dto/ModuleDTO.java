package org.dougllas.securitycontrol.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDTO {

    private Long id;

    @NotEmpty(message = "Campo nome é obrigatório.")
    private String name;
}
