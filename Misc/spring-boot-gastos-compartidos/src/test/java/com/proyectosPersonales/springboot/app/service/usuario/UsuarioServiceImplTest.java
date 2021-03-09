package com.proyectosPersonales.springboot.app.service.usuario;

import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

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
    public void guardarUsuario_UsuarioCorrecto() throws Exception {
    	when(usuarioDao.save(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.actualizarUsuario(crearUsuarioCorrecto());
        assertEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void guardarUsuario_UsuarioVacio() throws Exception {
    	when(usuarioDao.save(any())).thenReturn(crearUsuarioVacio());
        Usuario usuario = usuarioService.actualizarUsuario(crearUsuarioCorrecto());
        assertNotEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void guardarUsuario_UsuarioDistinto() throws Exception {
    	when(usuarioDao.save(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.actualizarUsuario(crearUsuarioCorrecto2());
        assertNotEquals(usuario.getNombre(), crearUsuarioCorrecto2().getNombre());
    }
   
    @Test
    public void buscarPorNombreYApellidos_UsuarioCorrecto() {
    	when(usuarioDao.findByNombreAndApellidos(any(), any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.buscarPorNombreYApellidos("", "");
        assertEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void buscarPorNombreYApellidos_UsuarioNoEncontrado() {
    	when(usuarioDao.findByNombreAndApellidos(any(), any())).thenReturn(null);
        assertThrows(HttpClientErrorException.class, () -> {
        	usuarioService.buscarPorNombreYApellidos("", "");
        });
    }

    @Test
    public void buscarPorCodUsuario_UsuarioCorrecto() {
    	when(usuarioDao.findByCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.buscarPorCodUsuario("");
        assertEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void buscarPorCodUsuario_UsuarioNoEncontrado() {
    	when(usuarioDao.findByCodUsuario(any())).thenReturn(null);
        assertThrows(HttpClientErrorException.class, () -> {
        	usuarioService.buscarPorCodUsuario("");
        });
    }
    
    @Test
    public void buscarPorIdUsuario_UsuarioCorrecto() {
    	when(usuarioDao.findByIdUsuario(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.buscarPorIdUsuario(1);
        assertEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void buscarPorIdUsuario_UsuarioNoEncontrado() {
    	when(usuarioDao.findByIdUsuario(any())).thenReturn(null);
        assertThrows(HttpClientErrorException.class, () -> {
        	usuarioService.buscarPorIdUsuario(1);
        });
    }
}
