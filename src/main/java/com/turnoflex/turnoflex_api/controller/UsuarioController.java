package com.turnoflex.turnoflex_api.controller;

import com.turnoflex.turnoflex_api.mapper.UsuarioMapper;
import com.turnoflex.turnoflex_api.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public List<Usuario> listar() {
        return usuarioMapper.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Integer id) {
        return usuarioMapper.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        usuarioMapper.insertarUsuario(usuario);
        return usuario;
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        usuarioMapper.actualizarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        usuarioMapper.eliminarUsuario(id);
    }
}
