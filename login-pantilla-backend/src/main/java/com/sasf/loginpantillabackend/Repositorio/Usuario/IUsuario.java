package com.sasf.loginpantillabackend.Repositorio.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario,Integer> {

    @Query("update Usuario u set u.estado = null  where u.IdUsuario=:id ")
    public Usuario deleteUsuarioById(Integer id);
    
}
