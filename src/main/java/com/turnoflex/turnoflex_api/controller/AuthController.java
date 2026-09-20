package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.config.JwtUtil;
import com.turnoflex.turnoflex_api.mapper.CoordinadorMapper;
import com.turnoflex.turnoflex_api.mapper.UsuarioMapper;
import com.turnoflex.turnoflex_api.model.Coordinador;
import com.turnoflex.turnoflex_api.model.LoginRequest;
import com.turnoflex.turnoflex_api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CoordinadorMapper coordinadorMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioMapper.obtenerUsuarioPorEmail(loginRequest.getEmail());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }

        boolean passwordCorrecto = passwordEncoder.matches(
                loginRequest.getPassword(), usuario.getPasswordHash()
        );

        if (!passwordCorrecto) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales incorrectas"));
        }

        String token = jwtUtil.generarToken(usuario.getEmail(), usuario.getRol());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("token", token);
        respuesta.put("id", usuario.getId());
        respuesta.put("email", usuario.getEmail());
        respuesta.put("rol", usuario.getRol());
        respuesta.put("nombre", usuario.getNombre());

        if ("COORDINADOR".equals(usuario.getRol())) {
            Coordinador coordinador = coordinadorMapper.obtenerCoordinadorPorIdUsuario(usuario.getId());
            if (coordinador != null) {
                respuesta.put("idCoordinador", coordinador.getId());
            }
        }

        return ResponseEntity.ok(respuesta);
    }
}