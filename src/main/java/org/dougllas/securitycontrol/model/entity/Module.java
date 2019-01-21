package org.dougllas.securitycontrol.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder@Getter@Setter
@Entity
@Table(name = "module")
public class Module implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    public Module(String name) {
        this.name = name;
    }
}