package com.sasf.loginpantillabackend.Service;

import java.util.List;
import java.util.Optional;

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

    public Usuario updateUsuario(Usuario usuario, Integer id){
        Optional<Usuario> usuarioExtraido = iUsuario.findById(id);

        Usuario usuarioActual = usuarioExtraido.get();

        usuarioActual.setNombre(usuario.getNombre());
        usuarioActual.setNombre(usuario.getApellido());
        usuarioActual.setEmail(usuario.getEmail());
        usuarioActual.setPassword(usuario.getPassword());
        usuarioActual.setEstado(usuario.getEstado());
        usuarioActual.setRoles(usuario.getRoles());

        return iUsuario.save(usuario);
    }

}
