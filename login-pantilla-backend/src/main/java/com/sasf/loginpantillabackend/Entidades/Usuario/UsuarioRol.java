package com.sasf.loginpantillabackend.Entidades.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario_rol")
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_rol")
    private Integer idUsuarioRol;

    @Pattern(regexp = "[AI]", message = "{app.fiel.estado.error}")
    @Column(name = "estado")
    private String estado;

    @Column(name = "id_rol")
    private Integer IdRol;

    @Column(name = "id_usuario")
    private Integer IdUsuario;
}
