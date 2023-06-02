package com.sasf.loginpantillabackend.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioRolService {

    @Autowired
    IUsuarioRol iUsuarioRol;

    public void deleteRolUsuario(Integer userId, Integer rolId) {
        iUsuarioRol.deleteRolUsuario(userId,rolId);
    }

    public List<Rol> getRolNoAsignado(Integer idUsuario){
        return iUsuarioRol.getRolNoAsignado(idUsuario);
    }

    public List<Usuario> getAllUsuarios(){
        return iUsuarioRol.getAllUsuario();
    }

}
