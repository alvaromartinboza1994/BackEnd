package com.proyectosPersonales.springboot.app.service.usuario;

import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.crearUsuarioCorrecto;
import static com.proyectosPersonales.springboot.app.service.usuarioContrasena.UsuarioContrasenaServiceImplTestUtil.crearUsuarioContrasenaCorrecto;
import static com.proyectosPersonales.springboot.app.service.usuarioContrasena.UsuarioContrasenaServiceImplTestUtil.crearUsuarioContrasenaCorrecto2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.impl.UsuarioServiceImpl;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioContrasenaService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {

    @InjectMocks
    private UsuarioServiceImpl usuarioService;
    
    @Mock
    private UsuarioDaoI usuarioDao;
    
    @Mock
    private UsuarioContrasenaService usuarioContrasenaService;

    @Test
    public void guardarUsuario_UsuarioCorrecto() throws Exception {
    	when(usuarioDao.save(any())).thenReturn(crearUsuarioCorrecto());
    	when(usuarioContrasenaService.guardarUsuarioContrasena(any())).thenReturn(crearUsuarioContrasenaCorrecto());
        Usuario usuario = usuarioService.guardarUsuario(crearUsuarioContrasenaCorrecto());
        assertEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void guardarUsuario_ApiException() {
    	when(usuarioDao.save(any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> {
        	usuarioService.guardarUsuario(crearUsuarioContrasenaCorrecto());
        });
    }
    
    @Test
    public void accederUsuario_UsuarioCorrecto() throws Exception {
    	when(usuarioContrasenaService.buscarPorUsuarioCodUsuario(any())).thenReturn(crearUsuarioContrasenaCorrecto());
    	ResponseEntity<String> entity = usuarioService.accederUsuario(crearUsuarioContrasenaCorrecto());
        assertEquals(entity.getStatusCode(), HttpStatus.OK);
    }
    
    @Test
    public void accederUsuario_ApiException() {
    	when(usuarioContrasenaService.buscarPorUsuarioCodUsuario(any())).thenReturn(crearUsuarioContrasenaCorrecto2());
    	ResponseEntity<String> entity = usuarioService.accederUsuario(crearUsuarioContrasenaCorrecto());
        assertEquals(entity.getStatusCode(), HttpStatus.FORBIDDEN);
    }
    
    /*@Test
    public void actualizarUsuario_Correcto() {
    	when(usuarioDao.findByCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	when(usuarioDao.save(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario_actualizado = usuarioService.actualizarUsuario(crearUsuarioCorrecto());
        assertEquals(usuario_actualizado, crearUsuarioCorrecto());
    }
    
    @Test
    public void actualizarUsuario_ApiException() {
    	when(usuarioDao.findByCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	when(usuarioDao.save(any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> {
        	usuarioService.actualizarUsuario(crearUsuarioCorrecto());
        });
    }*/

    @Test
    public void buscarPorCodUsuario_UsuarioCorrecto() {
    	when(usuarioDao.findByCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
        Usuario usuario = usuarioService.buscarPorCodUsuario("");
        assertEquals(usuario, crearUsuarioCorrecto());
    }
    
    @Test
    public void buscarPorCodUsuario_UsuarioNoEncontrado() {
    	when(usuarioDao.findByCodUsuario(any())).thenReturn(null);
        assertThrows(ApiException.class, () -> {
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
        assertThrows(ApiException.class, () -> {
        	usuarioService.buscarPorIdUsuario(1);
        });
    }
}
