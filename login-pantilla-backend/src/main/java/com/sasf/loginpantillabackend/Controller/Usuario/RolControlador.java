package com.sasf.loginpantillabackend.Controller.Usuario;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Repositorio.Usuario.IRol;
import com.sasf.loginpantillabackend.Service.RolService;
import com.sasf.loginpantillabackend.Service.UsuarioRolService;
import com.sasf.loginpantillabackend.Service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rol")
public class RolControlador {

    @Autowired
    RolService rolService;

    @Autowired
    UsuarioRolService usuarioRolService;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRol(){
        return ResponseEntity.ok(rolService.getAllRol());
    }

    @GetMapping("/noAsiganados/{userId}")
    public ResponseEntity<List<Rol>> getRol(@PathVariable Integer userId) {
        return ResponseEntity.ok(usuarioRolService.getRolNoAsignado(userId));
    }

    @Transactional
    @PutMapping("/delete/{userId}/{rolId}")
    public void deleteRol(@PathVariable Integer userId, @PathVariable Integer rolId) {
         usuarioRolService.deleteRolUsuario(userId, rolId);
    }
}
