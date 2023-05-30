package com.sasf.loginpantillabackend.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuario;

@Service
public class UsuarioService {

    @Autowired
    IUsuario iUsuario;
    private Integer id;

    public List<Usuario> getAllUsuarios() {
        return iUsuario.findAll();
    }

    public Usuario createUsuario(Usuario usuario) {
        return iUsuario.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario, Integer id) {
        Optional<Usuario> usuarioExtraido = iUsuario.findById(id);

        if (usuarioExtraido.isPresent()) {
            return iUsuario.save(usuario);
        } else {
            throw new NoSuchElementException("No se encontró el usuario con el ID especificado: " + id);
        }
    }

    public void deleteUsuario(Integer id) {
        iUsuario.deleteUsuarioById(id);
    }


}
