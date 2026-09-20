package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.config.JwtUtil;
import com.turnoflex.turnoflex_api.mapper.CoordinadorMapper;
import com.turnoflex.turnoflex_api.mapper.EmpleadoMapper;
import com.turnoflex.turnoflex_api.mapper.UsuarioMapper;
import com.turnoflex.turnoflex_api.model.LoginRequest;
import com.turnoflex.turnoflex_api.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UsuarioMapper usuarioMapper;

    @Mock
    private CoordinadorMapper coordinadorMapper;

    @Mock
    private EmpleadoMapper empleadoMapper;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    private final BCryptPasswordEncoder encoderReal = new BCryptPasswordEncoder();
    private Usuario usuarioDePrueba;

    @BeforeEach
    void setUp() {
        usuarioDePrueba = new Usuario();
        usuarioDePrueba.setId(1);
        usuarioDePrueba.setEmail("ana@turnoflex.com");
        usuarioDePrueba.setPasswordHash(encoderReal.encode("1234"));
        usuarioDePrueba.setRol("COORDINADOR");
        usuarioDePrueba.setNombre("Ana Torres");
    }

    @Test
    void login_conCredencialesCorrectas_devuelveTokenYStatus200() {
        // Usamos un PasswordEncoder real inyectado manualmente vía reflexión no es necesario:
        // en su lugar probamos el flujo con el encoder real a través de un AuthController construido a mano.
        AuthController controller = new AuthController();
        setField(controller, "usuarioMapper", usuarioMapper);
        setField(controller, "coordinadorMapper", coordinadorMapper);
        setField(controller, "empleadoMapper", empleadoMapper);
        setField(controller, "passwordEncoder", encoderReal);
        setField(controller, "jwtUtil", jwtUtil);

        LoginRequest request = new LoginRequest();
        request.setEmail("ana@turnoflex.com");
        request.setPassword("1234");

        when(usuarioMapper.obtenerUsuarioPorEmail("ana@turnoflex.com")).thenReturn(usuarioDePrueba);
        when(jwtUtil.generarToken("ana@turnoflex.com", "COORDINADOR")).thenReturn("token-simulado");

        ResponseEntity<?> respuesta = controller.login(request);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Map<?, ?> body = (Map<?, ?>) respuesta.getBody();
        assertEquals("token-simulado", body.get("token"));
        assertEquals("Ana Torres", body.get("nombre"));
    }

    @Test
    void login_conEmailInexistente_devuelve401() {
        LoginRequest request = new LoginRequest();
        request.setEmail("noexiste@turnoflex.com");
        request.setPassword("1234");

        when(usuarioMapper.obtenerUsuarioPorEmail("noexiste@turnoflex.com")).thenReturn(null);

        ResponseEntity<?> respuesta = authController.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
    }

    @Test
    void login_conPasswordIncorrecto_devuelve401() {
        AuthController controller = new AuthController();
        setField(controller, "usuarioMapper", usuarioMapper);
        setField(controller, "coordinadorMapper", coordinadorMapper);
        setField(controller, "empleadoMapper", empleadoMapper);
        setField(controller, "passwordEncoder", encoderReal);
        setField(controller, "jwtUtil", jwtUtil);

        LoginRequest request = new LoginRequest();
        request.setEmail("ana@turnoflex.com");
        request.setPassword("password-incorrecto");

        when(usuarioMapper.obtenerUsuarioPorEmail("ana@turnoflex.com")).thenReturn(usuarioDePrueba);

        ResponseEntity<?> respuesta = controller.login(request);

        assertEquals(HttpStatus.UNAUTHORIZED, respuesta.getStatusCode());
    }

    // Utilidad para inyectar campos privados manualmente en las pruebas
    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}