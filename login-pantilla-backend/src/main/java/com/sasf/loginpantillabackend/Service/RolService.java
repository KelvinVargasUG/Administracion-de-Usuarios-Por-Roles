package com.sasf.loginpantillabackend.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IRol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RolService {

    @Autowired
    IRol irol;

    @Autowired
    IUsuario iUsuario;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Rol> getAllRol() {
        return irol.findAll();
    }

    public Optional<Rol> getRolById(Integer id) {
        return irol.findById(id);
    }

    }
