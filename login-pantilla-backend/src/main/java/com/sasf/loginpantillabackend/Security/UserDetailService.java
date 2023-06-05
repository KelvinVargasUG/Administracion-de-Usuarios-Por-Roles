package com.sasf.loginpantillabackend.Security;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Entidades.Usuario.UsuarioRol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuario;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private IUsuarioRol iUsuarioRol;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = this.iUsuarioRol.getUsuariosByEmail(email);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        else {
            return usuario;
        }
    }
}
