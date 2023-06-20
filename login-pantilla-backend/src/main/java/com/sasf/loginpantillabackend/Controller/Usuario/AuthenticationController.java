package com.sasf.loginpantillabackend.Controller.Usuario;

import com.sasf.loginpantillabackend.Configuration.JwtUtils;
import com.sasf.loginpantillabackend.DTO.DTOLogin;
import com.sasf.loginpantillabackend.Entidades.Usuario.JwtRequest;
import com.sasf.loginpantillabackend.Entidades.Usuario.JwtResponse;
import com.sasf.loginpantillabackend.Entidades.Usuario.Rol;
import com.sasf.loginpantillabackend.Entidades.Usuario.Usuario;
import com.sasf.loginpantillabackend.Security.UserDetailService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sasf.loginpantillabackend.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.User;

import javax.validation.Valid;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getEmail(), jwtRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailService.loadUserByUsername(jwtRequest.getEmail());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("Usuario Deshabilitado " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales Invalidas " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario,"Registre"));
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<DTOLogin> obtenerUsuarioActual(Principal principal) {
        User user = (User) this.userDetailService.loadUserByUsername(principal.getName());
        List<DTOLogin.AuthorityDTO> authorityDTOs = user.getAuthorities().stream()
                .map(authority -> new DTOLogin.AuthorityDTO(authority.getAuthority()))
                .collect(Collectors.toList());

        DTOLogin userDTO = new DTOLogin(user.getUsername(), authorityDTOs);

        return ResponseEntity.ok(userDTO);
    }
}
