package com.turnoflex.turnoflex_api.mapper;

import com.turnoflex.turnoflex_api.model.ReporteEmpleado;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReporteMapper {

    @Select("""
        SELECT
            e.id AS idEmpleado,
            e.nombre AS nombreEmpleado,
            COALESCE(t.totalTurnos, 0) AS totalTurnos,
            COALESCE(t.totalHoras, 0) AS totalHoras,
            COALESCE(n.totalNovedades, 0) AS totalNovedades,
            COALESCE(n.pendientes, 0) AS novedadesPendientes,
            COALESCE(n.aprobadas, 0) AS novedadesAprobadas,
            COALESCE(n.rechazadas, 0) AS novedadesRechazadas
        FROM empleados e
        LEFT JOIN (
            SELECT id_empleado,
                   COUNT(*) AS totalTurnos,
                   SUM(EXTRACT(EPOCH FROM (hora_fin - hora_inicio)) / 3600) AS totalHoras
            FROM turnos
            WHERE fecha BETWEEN #{fechaInicio}::date AND #{fechaFin}::date
            GROUP BY id_empleado
        ) t ON t.id_empleado = e.id
        LEFT JOIN (
            SELECT id_empleado,
                   COUNT(*) AS totalNovedades,
                   COUNT(*) FILTER (WHERE estado = 'PENDIENTE') AS pendientes,
                   COUNT(*) FILTER (WHERE estado = 'APROBADA') AS aprobadas,
                   COUNT(*) FILTER (WHERE estado = 'RECHAZADA') AS rechazadas
            FROM novedades
            WHERE fecha_inicio BETWEEN #{fechaInicio}::date AND #{fechaFin}::date
            GROUP BY id_empleado
        ) n ON n.id_empleado = e.id
        ORDER BY e.nombre
        """)
    List<ReporteEmpleado> generarReporte(String fechaInicio, String fechaFin);
}