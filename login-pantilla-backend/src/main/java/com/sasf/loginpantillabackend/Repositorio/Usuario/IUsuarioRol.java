package com.sasf.loginpantillabackend.Repositorio.Usuario;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Entidades.Usuario.UsuarioRol;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioRol extends JpaRepository<UsuarioRol, Integer> {

    @Modifying
    @Query("UPDATE UsuarioRol set estado ='I' where IdUsuario =:idUsuario and IdRol =:idRol")
    public void deleteRolUsuario(Integer idUsuario, Integer idRol);

    @Query("SELECT r from Rol r where r.IdRol not IN (select ur.IdRol from UsuarioRol ur where ur.IdUsuario =:idUsuario AND ur.estado = 'A')")
    public List<Rol> getRolNoAsignado(Integer idUsuario);

    @Query("SELECT DISTINCT  u from Usuario u JOIN fetch u.roles r JOIN UsuarioRol ur on ur.IdRol = r.IdRol " +
            "and ur.IdUsuario = u.IdUsuario where ur.estado='A' and u.email =:email and u.estado is not null order by u.IdUsuario asc")
    public Usuario getUsuariosByEmail(String email);

    @Query("SELECT DISTINCT  u from Usuario u JOIN fetch u.roles r JOIN UsuarioRol ur on ur.IdRol = r.IdRol " +
            "and ur.IdUsuario = u.IdUsuario where ur.estado='A' and u.IdUsuario =:id and u.estado is not null order by u.IdUsuario asc")
    public Usuario getUsuariosById(Integer id);

    @Query(value = "SELECT DISTINCT  u from Usuario u " +
            "JOIN fetch u.roles r " +
            "JOIN UsuarioRol ur on ur.IdRol = r.IdRol " +
            "and ur.IdUsuario = u.IdUsuario " +
            "where ur.estado='A' " +
            "and u.estado is not null order by u.IdUsuario desc")
    public List<Usuario> getAllUsuarioList();


    @Query(value = "SELECT DISTINCT u FROM Usuario u " +
            "JOIN UsuarioRol ur ON u.IdUsuario = ur.IdUsuario " +
            "JOIN Rol r ON r.IdRol = ur.IdRol " +
            "WHERE ur.estado = 'A' AND u.estado IS NOT NULL " +
            "ORDER BY u.IdUsuario DESC",
            countQuery = "SELECT COUNT(DISTINCT u) FROM Usuario u " +
                    "JOIN UsuarioRol ur ON u.IdUsuario = ur.IdUsuario " +
                    "JOIN Rol r ON r.IdRol = ur.IdRol " +
                    "WHERE ur.estado = 'A' AND u.estado IS NOT NULL")
    Page<Usuario> getAllUsuario(Pageable pageable);

}
