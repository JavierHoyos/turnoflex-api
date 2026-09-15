package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.mapper.CoordinadorMapper;
import com.turnoflex.turnoflex_api.model.Coordinador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/coordinadores")
public class CoordinadorController {

    @Autowired
    private CoordinadorMapper coordinadorMapper;

    @GetMapping
    public List<Coordinador> listar() {
        return coordinadorMapper.obtenerCoordinadores();
    }

    @GetMapping("/{id}")
    public Coordinador obtenerPorId(@PathVariable Integer id) {
        return coordinadorMapper.obtenerCoordinadorPorId(id);
    }

    @PostMapping
    public Coordinador crear(@RequestBody Coordinador coordinador) {
        coordinadorMapper.insertarCoordinador(coordinador);
        return coordinador;
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Integer id, @RequestBody Coordinador coordinador) {
        coordinador.setId(id);
        coordinadorMapper.actualizarCoordinador(coordinador);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        coordinadorMapper.eliminarCoordinador(id);
    }
}