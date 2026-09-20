package com.turnoflex.turnoflex_api.mapper;

import com.turnoflex.turnoflex_api.model.Turno;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TurnoMapper {


    @Select("SELECT id, id_empleado AS idEmpleado, fecha, hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, estado FROM turnos WHERE id_empleado = #{idEmpleado}")
    List<Turno> obtenerTurnosPorEmpleado(Integer idEmpleado);

    @Select("SELECT id, id_empleado AS idEmpleado, fecha, hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, estado FROM turnos")
    List<Turno> obtenerTurnos();

    @Select("SELECT id, id_empleado AS idEmpleado, fecha, hora_inicio AS horaInicio, " +
            "hora_fin AS horaFin, estado FROM turnos WHERE id = #{id}")
    Turno obtenerTurnoPorId(Integer id);

    @Insert("INSERT INTO turnos(id_empleado, fecha, hora_inicio, hora_fin, estado) " +
            "VALUES(#{idEmpleado}, #{fecha}, #{horaInicio}, #{horaFin}, #{estado})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertarTurno(Turno turno);

    @Update("UPDATE turnos SET fecha=#{fecha}, hora_inicio=#{horaInicio}, " +
            "hora_fin=#{horaFin}, estado=#{estado} WHERE id=#{id}")
    void actualizarTurno(Turno turno);

    @Delete("DELETE FROM turnos WHERE id = #{id}")
    void eliminarTurno(Integer id);
}