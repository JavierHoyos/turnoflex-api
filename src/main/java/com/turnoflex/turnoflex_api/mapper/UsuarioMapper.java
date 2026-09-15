package com.turnoflex.turnoflex_api.mapper;

import com.turnoflex.turnoflex_api.model.Usuario;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UsuarioMapper {

    @Select("SELECT id, email, password_hash AS passwordHash, rol, nombre, activo FROM usuarios")
    List<Usuario> obtenerUsuarios();

    @Select("SELECT id, email, password_hash AS passwordHash, rol, nombre, activo FROM usuarios WHERE id = #{id}")
    Usuario obtenerUsuarioPorId(Integer id);

    @Select("SELECT id, email, password_hash AS passwordHash, rol, nombre, activo FROM usuarios WHERE email = #{email}")
    Usuario obtenerUsuarioPorEmail(String email);

    @Insert("INSERT INTO usuarios(email, password_hash, rol, nombre, activo) " +
            "VALUES(#{email}, #{passwordHash}, #{rol}, #{nombre}, TRUE)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertarUsuario(Usuario usuario);

    @Update("UPDATE usuarios SET nombre=#{nombre}, activo=#{activo} WHERE id=#{id}")
    void actualizarUsuario(Usuario usuario);

    @Delete("DELETE FROM usuarios WHERE id = #{id}")
    void eliminarUsuario(Integer id);
}