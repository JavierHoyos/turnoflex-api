package com.turnoflex.turnoflex_api.mapper;

import com.turnoflex.turnoflex_api.model.Coordinador;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface CoordinadorMapper {

    @Select("SELECT id, id_usuario AS idUsuario, area_asignada AS areaAsignada FROM coordinadores")
    List<Coordinador> obtenerCoordinadores();

    @Select("SELECT id, id_usuario AS idUsuario, area_asignada AS areaAsignada FROM coordinadores WHERE id = #{id}")
    Coordinador obtenerCoordinadorPorId(Integer id);

    @Insert("INSERT INTO coordinadores(id_usuario, area_asignada) VALUES(#{idUsuario}, #{areaAsignada})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertarCoordinador(Coordinador coordinador);

    @Update("UPDATE coordinadores SET area_asignada=#{areaAsignada} WHERE id=#{id}")
    void actualizarCoordinador(Coordinador coordinador);

    @Delete("DELETE FROM coordinadores WHERE id = #{id}")
    void eliminarCoordinador(Integer id);
}