package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.mapper.TurnoMapper;
import com.turnoflex.turnoflex_api.model.Turno;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TurnoControllerTest {

    @Mock
    private TurnoMapper turnoMapper;

    @InjectMocks
    private TurnoController turnoController;

    @Test
    void listar_sinIdEmpleado_devuelveTodosLosTurnos() {
        Turno turno1 = new Turno();
        turno1.setId(1);
        turno1.setIdEmpleado(1);

        Turno turno2 = new Turno();
        turno2.setId(2);
        turno2.setIdEmpleado(2);

        when(turnoMapper.obtenerTurnos()).thenReturn(List.of(turno1, turno2));

        List<Turno> resultado = turnoController.listar(null);

        assertEquals(2, resultado.size());
        verify(turnoMapper, times(1)).obtenerTurnos();
        verify(turnoMapper, never()).obtenerTurnosPorEmpleado(anyInt());
    }

    @Test
    void listar_conIdEmpleado_devuelveSoloLosDeEseEmpleado() {
        Turno turnoDelEmpleado1 = new Turno();
        turnoDelEmpleado1.setId(1);
        turnoDelEmpleado1.setIdEmpleado(1);

        when(turnoMapper.obtenerTurnosPorEmpleado(1)).thenReturn(List.of(turnoDelEmpleado1));

        List<Turno> resultado = turnoController.listar(1);

        assertEquals(1, resultado.size());
        assertEquals(1, resultado.get(0).getIdEmpleado());
        verify(turnoMapper, times(1)).obtenerTurnosPorEmpleado(1);
        verify(turnoMapper, never()).obtenerTurnos();
    }
}