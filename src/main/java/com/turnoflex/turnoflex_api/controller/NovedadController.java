package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.mapper.NovedadMapper;
import com.turnoflex.turnoflex_api.model.Novedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/novedades")
public class NovedadController {

    @Autowired
    private NovedadMapper novedadMapper;

    @GetMapping
    public List<Novedad> listar(@RequestParam(required = false) Integer idEmpleado) {
        if (idEmpleado != null) {
            return novedadMapper.obtenerNovedadesPorEmpleado(idEmpleado);
        }
        return novedadMapper.obtenerNovedades();
    }

    @GetMapping("/{id}")
    public Novedad obtenerPorId(@PathVariable Integer id) {
        return novedadMapper.obtenerNovedadPorId(id);
    }

    @PostMapping
    public Novedad crear(@RequestBody Novedad novedad) {
        novedadMapper.insertarNovedad(novedad);
        return novedad;
    }

    @PutMapping("/{id}/estado")
    public void actualizarEstado(@PathVariable Integer id, @RequestBody Novedad novedad) {
        novedad.setId(id);
        novedadMapper.actualizarEstadoNovedad(novedad);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        novedadMapper.eliminarNovedad(id);
    }
}