package com.sasf.loginpantillabackend.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuarioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioRolService {

    @Autowired
    IUsuarioRol iUsuarioRol;

    public void deleteRolUsuario(Integer userId, Integer rolId) {
        iUsuarioRol.deleteRolUsuario(userId, rolId);
    }

    public List<Rol> getRolNoAsignado(Integer idUsuario) {
        return iUsuarioRol.getRolNoAsignado(idUsuario);
    }

    public ResponseEntity<?> getAllUsuarios(Pageable pageable) {
        Page<Usuario> usersPage =  iUsuarioRol.getAllUsuario(pageable);
        return ResponseEntity.ok(usersPage);
    }

    public ResponseEntity<?> getAllUsuariosList() {
       List<Usuario> usersPage =  iUsuarioRol.getAllUsuarioList();
        return ResponseEntity.ok(usersPage);
    }

}
