package com.sasf.loginpantillabackend.Service;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Entidades.Usuario.UsuarioRol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IRol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public Optional<Rol> getRolByRol(Integer id) {
        return irol.findById(id);
    }

    public List<Rol> getRolNoAsignado(Integer userId) {
        String nativeQuery = "SELECT r.* FROM rol r WHERE r.id_rol NOT IN " +
                "(SELECT ur.id_rol FROM usuario_rol ur WHERE ur.id_usuario = :userId)";

        Query query = entityManager.createNativeQuery(nativeQuery, Rol.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    public void deleteRol(Integer userId, Integer rolId) {
        String querySolicitud = "UPDATE usuario_rol SET estado = 'I' WHERE id_usuario = :userId AND id_rol = :rolId";
    
        Query query = entityManager.createNativeQuery(querySolicitud);
        query.setParameter("userId", userId);
        query.setParameter("rolId", rolId);    
        query.executeUpdate();
    }

    


}
