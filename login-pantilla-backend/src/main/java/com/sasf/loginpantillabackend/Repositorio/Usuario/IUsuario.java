package com.sasf.loginpantillabackend.Repositorio.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.estado IS NOT NULL order by u.id ASC")
    public List<Usuario> getAllUsuarios();

    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.estado IS NOT NULL")
    public Usuario getUsuariosById(Integer id);
}
