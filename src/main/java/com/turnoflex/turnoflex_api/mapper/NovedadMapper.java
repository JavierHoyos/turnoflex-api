package com.turnoflex.turnoflex_api.mapper;

import com.turnoflex.turnoflex_api.model.Novedad;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface NovedadMapper {

    @Select("SELECT id, id_empleado AS idEmpleado, tipo, fecha_inicio AS fechaInicio, " +
            "fecha_fin AS fechaFin, estado, id_coordinador_aprueba AS idCoordinadorAprueba, observacion " +
            "FROM novedades WHERE id_empleado = #{idEmpleado}")
    List<Novedad> obtenerNovedadesPorEmpleado(Integer idEmpleado);
    @Select("SELECT id, id_empleado AS idEmpleado, tipo, fecha_inicio AS fechaInicio, " +
            "fecha_fin AS fechaFin, estado, id_coordinador_aprueba AS idCoordinadorAprueba, observacion " +
            "FROM novedades")
    List<Novedad> obtenerNovedades();

    @Select("SELECT id, id_empleado AS idEmpleado, tipo, fecha_inicio AS fechaInicio, " +
            "fecha_fin AS fechaFin, estado, id_coordinador_aprueba AS idCoordinadorAprueba, observacion " +
            "FROM novedades WHERE id = #{id}")
    Novedad obtenerNovedadPorId(Integer id);

    @Insert("INSERT INTO novedades(id_empleado, tipo, fecha_inicio, fecha_fin, estado, observacion) " +
            "VALUES(#{idEmpleado}, #{tipo}, #{fechaInicio}, #{fechaFin}, 'PENDIENTE', #{observacion})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertarNovedad(Novedad novedad);

    @Update("UPDATE novedades SET estado=#{estado}, id_coordinador_aprueba=#{idCoordinadorAprueba} WHERE id=#{id}")
    void actualizarEstadoNovedad(Novedad novedad);

    @Delete("DELETE FROM novedades WHERE id = #{id}")
    void eliminarNovedad(Integer id);
}