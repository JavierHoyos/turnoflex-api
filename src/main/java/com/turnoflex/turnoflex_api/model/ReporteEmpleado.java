package com.turnoflex.turnoflex_api.model;

public class ReporteEmpleado {
    private Integer idEmpleado;
    private String nombreEmpleado;
    private Long totalTurnos;
    private Double totalHoras;
    private Long totalNovedades;
    private Long novedadesPendientes;
    private Long novedadesAprobadas;
    private Long novedadesRechazadas;

    public Integer getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(Integer idEmpleado) { this.idEmpleado = idEmpleado; }

    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }

    public Long getTotalTurnos() { return totalTurnos; }
    public void setTotalTurnos(Long totalTurnos) { this.totalTurnos = totalTurnos; }

    public Double getTotalHoras() { return totalHoras; }
    public void setTotalHoras(Double totalHoras) { this.totalHoras = totalHoras; }

    public Long getTotalNovedades() { return totalNovedades; }
    public void setTotalNovedades(Long totalNovedades) { this.totalNovedades = totalNovedades; }

    public Long getNovedadesPendientes() { return novedadesPendientes; }
    public void setNovedadesPendientes(Long novedadesPendientes) { this.novedadesPendientes = novedadesPendientes; }

    public Long getNovedadesAprobadas() { return novedadesAprobadas; }
    public void setNovedadesAprobadas(Long novedadesAprobadas) { this.novedadesAprobadas = novedadesAprobadas; }

    public Long getNovedadesRechazadas() { return novedadesRechazadas; }
    public void setNovedadesRechazadas(Long novedadesRechazadas) { this.novedadesRechazadas = novedadesRechazadas; }
}