package com.sasf.loginpantillabackend.Repositorio.Usuario;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRol extends JpaRepository<Rol, Integer> {
}
