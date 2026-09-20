package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.mapper.TurnoMapper;
import com.turnoflex.turnoflex_api.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoMapper turnoMapper;

    @GetMapping
    public List<Turno> listar(@RequestParam(required = false) Integer idEmpleado) {
        if (idEmpleado != null) {
            return turnoMapper.obtenerTurnosPorEmpleado(idEmpleado);
        }
        return turnoMapper.obtenerTurnos();
    }


    @GetMapping("/{id}")
    public Turno obtenerPorId(@PathVariable Integer id) {
        return turnoMapper.obtenerTurnoPorId(id);
    }

    @PostMapping
    public Turno crear(@RequestBody Turno turno) {
        turnoMapper.insertarTurno(turno);
        return turno;
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Integer id, @RequestBody Turno turno) {
        turno.setId(id);
        turnoMapper.actualizarTurno(turno);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        turnoMapper.eliminarTurno(id);
    }
}
