package com.sasf.loginpantillabackend.Repositorio.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;

@Repository
public interface IUsuario extends JpaRepository<Usuario,Integer> {


    public void deleteUsuarioById(Integer id);
    
}
