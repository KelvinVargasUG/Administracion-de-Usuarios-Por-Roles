package com.sasf.loginpantillabackend.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuario;

@Service
public class UsuarioService {

    @Autowired
    IUsuario iUsuario;

    @Autowired
    RolService rolService;
    //private BCryptPasswordEncoder passwordEncoder;

   // public UsuarioService(BCryptPasswordEncoder passwordEncoder) {
        //this.passwordEncoder = passwordEncoder;
    //}

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuario = iUsuario.getAllUsuarios();
        if (usuario != null) {
            return usuario;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay datos");
        }
    }

    public Usuario createUsuario(Usuario usuario) {
        Optional<Rol> rolRespuesta=rolService.getRolByRol(usuario.getRoles().get(0).getIdRol());
        Rol rol = rolRespuesta.get();
        usuario.getRoles().get(0).setNombre(rol.getNombre());
        return iUsuario.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario, Integer id) {
        Optional<Usuario> usuarioExtraido = iUsuario.findById(id);

        if (usuarioExtraido.isPresent() && usuarioExtraido.get().getEstado() != null) {

            Usuario dataActualizar = usuarioExtraido.get();

            dataActualizar.setNombre(usuario.getNombre());
            dataActualizar.setApellido(usuario.getApellido());
            dataActualizar.setEmail(usuario.getEmail());
            dataActualizar.setEstado(usuario.getEstado());
            dataActualizar.setPassword(usuario.getPassword());
            dataActualizar.setRoles(usuario.getRoles());

            return iUsuario.save(dataActualizar);

        }
        
         else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró el usuario con el ID especificado: " + id);
        }
    }

    public String deleteUsuario(Integer id) {
        Optional<Usuario> usuarioExtraido = iUsuario.findById(id);

        if (usuarioExtraido.isPresent() && usuarioExtraido.get().getEstado() != null) {
            Usuario dataActualizar = usuarioExtraido.get();
            dataActualizar.setEstado(null);
            iUsuario.save(dataActualizar);

            throw new ResponseStatusException(HttpStatus.OK, "Eliminado Exitosamente");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró el usuario con el ID especificado: " + id);
        }
    }

    public Usuario getUsuarioById(Integer id) {
        Usuario usuario = iUsuario.getUsuariosById(id);
        if (usuario != null) {
            return usuario;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No se encontró el usuario con el ID especificado: " + id);
        }
    }

}
