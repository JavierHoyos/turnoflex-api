package com.turnoflex.turnoflex_api.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void generarToken_generaUnTokenNoVacio() {
        String token = jwtUtil.generarToken("ana@turnoflex.com", "COORDINADOR");

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void extraerEmail_devuelveElEmailCorrecto() {
        String token = jwtUtil.generarToken("ana@turnoflex.com", "COORDINADOR");

        String email = jwtUtil.extraerEmail(token);

        assertEquals("ana@turnoflex.com", email);
    }

    @Test
    void extraerRol_devuelveElRolCorrecto() {
        String token = jwtUtil.generarToken("carlos@turnoflex.com", "EMPLEADO");

        String rol = jwtUtil.extraerRol(token);

        assertEquals("EMPLEADO", rol);
    }

    @Test
    void validarToken_conTokenValido_devuelveTrue() {
        String token = jwtUtil.generarToken("ana@turnoflex.com", "COORDINADOR");

        assertTrue(jwtUtil.validarToken(token));
    }

    @Test
    void validarToken_conTokenInvalido_devuelveFalse() {
        String tokenFalso = "esto.no.es.un.token.valido";

        assertFalse(jwtUtil.validarToken(tokenFalso));
    }
}