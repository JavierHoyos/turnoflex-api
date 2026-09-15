package com.turnoflex.turnoflex_api.model;

import java.time.LocalDate;

public class Novedad {
    private Integer id;
    private Integer idEmpleado;
    private String tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private Integer idCoordinadorAprueba;
    private String observacion;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getIdCoordinadorAprueba() { return idCoordinadorAprueba; }
    public void setIdCoordinadorAprueba(Integer idCoordinadorAprueba) { this.idCoordinadorAprueba = idCoordinadorAprueba; }

    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}