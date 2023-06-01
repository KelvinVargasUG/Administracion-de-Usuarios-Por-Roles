package com.sasf.loginpantillabackend.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    IRol irol;

    public List<Rol> getAllRol(){
        return irol.findAll();
    }
}
