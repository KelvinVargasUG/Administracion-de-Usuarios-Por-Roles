package com.sasf.loginpantillabackend.Controller.Usuario;

import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rol")
public class RolControlador {

    @Autowired
    RolService service;

    @GetMapping
    public ResponseEntity<List<Rol>> getAllRol(){
        return ResponseEntity.ok(service.getAllRol());
    }
}
