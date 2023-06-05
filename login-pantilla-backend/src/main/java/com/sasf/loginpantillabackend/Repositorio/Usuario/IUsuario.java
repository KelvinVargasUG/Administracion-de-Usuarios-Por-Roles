package com.sasf.loginpantillabackend.Repositorio.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.IdUsuario = :id AND u.estado IS NOT NULL")
    public Usuario getUsuariosById(Integer id);

    @Query("SELECT u FROM Usuario u WHERE u.email =:email AND u.estado IS NOT NULL")
    public Usuario getUsuariosByEmail(String email);
}
