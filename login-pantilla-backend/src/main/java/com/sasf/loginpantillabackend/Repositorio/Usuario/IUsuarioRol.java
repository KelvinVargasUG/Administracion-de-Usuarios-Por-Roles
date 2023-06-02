package com.sasf.loginpantillabackend.Repositorio.Usuario;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Entidades.Usuario.UsuarioRol;
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

    @Query("SELECT r from Rol r where r.IdRol not IN (select ur.IdRol from UsuarioRol ur where ur.IdUsuario =:idUsuario)")
    public List<Rol> getRolNoAsignado(Integer idUsuario);

    @Query("SELECT DISTINCT  u from Usuario u JOIN fetch u.roles r JOIN UsuarioRol ur on ur.IdRol = r.IdRol " +
            "and ur.IdUsuario = u.IdUsuario where ur.estado='A' and u.estado is not null order by u.IdUsuario asc")
    public List<Usuario> getAllUsuario();

}
