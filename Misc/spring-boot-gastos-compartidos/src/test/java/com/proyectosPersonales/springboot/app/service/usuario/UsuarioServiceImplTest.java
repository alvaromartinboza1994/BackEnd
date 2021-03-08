package com.proyectosPersonales.springboot.app.service.usuario;

import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.crearUsuarioCorrecto;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.service.impl.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    public void guardarUsuario_Correcto() throws Exception {
        Usuario usuario = usuarioService.guardarUsuario(crearUsuarioCorrecto());
        assertNotNull(usuario);
    }


}
