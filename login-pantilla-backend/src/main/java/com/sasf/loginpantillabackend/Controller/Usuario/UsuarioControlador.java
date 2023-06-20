package com.sasf.loginpantillabackend.Controller.Usuario;

import java.util.List;

import javax.validation.Valid;

import com.sasf.loginpantillabackend.Service.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UsuarioRolService usuarioRolService;

    @GetMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioRolService.getAllUsuarios());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario,"Create"));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario, id));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/delete")
    public ResponseEntity<String> DeleteUsuario(@RequestBody Integer id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }

    @PreAuthorize("hasAuthority('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

}
