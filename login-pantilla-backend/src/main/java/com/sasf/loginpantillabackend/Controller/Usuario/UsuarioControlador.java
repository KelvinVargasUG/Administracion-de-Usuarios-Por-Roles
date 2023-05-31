package com.sasf.loginpantillabackend.Controller.Usuario;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@Valid @RequestBody Usuario usuario, @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.updateUsuario(usuario, id));
    }

    @PutMapping("/delete")
    public ResponseEntity<String> DeleteUsuario(@RequestBody Integer id) {
        return ResponseEntity.ok(usuarioService.deleteUsuario(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@Valid @PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

}
