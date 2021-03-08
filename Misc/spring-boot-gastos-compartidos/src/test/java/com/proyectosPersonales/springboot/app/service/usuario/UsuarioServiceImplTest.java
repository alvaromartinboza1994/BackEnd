package com.proyectosPersonales.springboot.app.service.usuario;

import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.crearUsuarioCorrecto;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.impl.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    
    @Mock
    private UsuarioDaoI usuarioDao;

    @Test
    public void guardarUsuario_Correcto() throws Exception {
    	when(usuarioDao.save(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.guardarUsuario(crearUsuarioCorrecto());
        assertNotNull(usuario);
    }


}
