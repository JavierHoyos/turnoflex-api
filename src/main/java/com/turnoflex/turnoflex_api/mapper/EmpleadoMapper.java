package com.turnoflex.turnoflex_api.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpleadoMapper {

    @Select("SELECT id FROM empleados WHERE id_usuario = #{idUsuario}")
    Integer obtenerIdEmpleadoPorIdUsuario(Integer idUsuario);
}
