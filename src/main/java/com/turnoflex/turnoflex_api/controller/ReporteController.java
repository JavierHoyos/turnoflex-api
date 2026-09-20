package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.mapper.ReporteMapper;
import com.turnoflex.turnoflex_api.model.ReporteEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteMapper reporteMapper;

    @GetMapping("/empleados")
    public List<ReporteEmpleado> reporteEmpleados(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return reporteMapper.generarReporte(fechaInicio, fechaFin);
    }
}