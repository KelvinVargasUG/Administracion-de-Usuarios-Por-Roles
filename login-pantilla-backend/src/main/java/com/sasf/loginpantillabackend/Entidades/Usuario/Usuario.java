package com.sasf.loginpantillabackend.Entidades.Usuario;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer IdUsuario;

    @NotNull
    @NotBlank
    @Column(name = "nombre")
    private String nombre;

    @NotNull
    @NotBlank
    @Column(name = "apellido")
    private String apellido;

    @Email
    @NotNull
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @Column(name = "estado")
    @Pattern(regexp = "[AI]", message = "{app.fiel.estado.error}")
    @Size(max = 1, message = "1 {app.fiel.cantidadCaracteres.error}")
    private String estado;

    @NotNull
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"), 
                                    inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    private List<Rol> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();
        for (Rol rol : this.roles) {
            autoridades.add(new Authority(rol.getNombre()));
        }
            return autoridades;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
