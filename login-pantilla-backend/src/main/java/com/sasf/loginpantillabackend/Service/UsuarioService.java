package com.sasf.loginpantillabackend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuario;

@Service
public class UsuarioService {
    
    @Autowired
    IUsuario iUsuario;

    public List<Usuario> getAllUsuarios(){
        return iUsuario.findAll();
    }

    public Usuario createUsuario(Usuario usuario){
        return iUsuario.save(usuario);
    }
}
