package com.sasf.loginpantillabackend.Controller.Usuario;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.sasf.loginpantillabackend.Service.UsuarioRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAllUsuarios(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        return ResponseEntity.ok(usuarioRolService.getAllUsuarios(pageable));
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

    @GetMapping("/prueba")
    @PreAuthorize("hasAuthority('Admin')")
    public void prueba() {
        int[] myArray = {1, 2, -1, 1, 0, 1, 2, -1, -1, -2};
        char[][] grid = new char[4][4];

        // Inicializar la matriz con 'O'
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 'O';
            }
        }

        int x = 0; // Posición inicial en el eje x
        int y = 0; // Posición inicial en el eje y

        // Realizar los movimientos
        for (int i = 0; i < myArray.length; i += 2) {
            int dx = myArray[i];     // Movimiento en el eje horizontal
            int dy = myArray[i + 1]; // Movimiento en el eje vertical

            // Actualizar la posición de la X
            x = Math.max(0, Math.min(x + dx, 3)); // Limitar la posición dentro del rango (0-3)
            y = Math.max(0, Math.min(y + dy, 3)); // Limitar la posición dentro del rango (0-3)

            // Colocar la X en la posición actual
            grid[y][x] = 'X';
        }

        // Imprimir la matriz resultante
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

}
