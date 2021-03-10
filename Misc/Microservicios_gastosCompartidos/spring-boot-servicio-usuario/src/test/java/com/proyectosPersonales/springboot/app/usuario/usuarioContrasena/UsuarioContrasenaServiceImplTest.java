package com.proyectosPersonales.springboot.app.usuario.usuarioContrasena;

import static com.proyectosPersonales.springboot.app.usuario.deuda.DeudaServiceImplTestUtil.crearDeudaCorrecto;
import static com.proyectosPersonales.springboot.app.usuario.deuda.DeudaServiceImplTestUtil.crearDeudaCorrecto2;
import static com.proyectosPersonales.springboot.app.usuario.deuda.DeudaServiceImplTestUtil.crearDeudaVacio;
import static com.proyectosPersonales.springboot.app.usuario.usuario.UsuarioServiceImplTestUtil.*;
import static com.proyectosPersonales.springboot.app.usuario.usuarioContrasena.UsuarioContrasenaServiceImplTestUtil.*;
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

import com.proyectosPersonales.springboot.app.commons.dto.UsuarioContrasena;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.UsuarioContrasenaDaoI;
import com.proyectosPersonales.springboot.app.usuario.dao.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.usuario.service.impl.UsuarioContrasenaServiceImpl;
import com.proyectosPersonales.springboot.app.usuario.service.impl.UsuarioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UsuarioContrasenaServiceImplTest {

    @InjectMocks
    private UsuarioContrasenaServiceImpl usuarioContrasenaService;
    
    @Mock
    private UsuarioContrasenaDaoI usuarioContrasenaDao;
    
    @Test
    public void guardarUsuarioContrasena_Correcto() throws Exception {
    	when(usuarioContrasenaDao.save(any())).thenReturn(crearUsuarioContrasenaCorrecto());
    	UsuarioContrasena usuarioContrasena = usuarioContrasenaService.guardarUsuarioContrasena(crearUsuarioContrasenaCorrecto());
        assertEquals(usuarioContrasena, crearUsuarioContrasenaCorrecto());
    }

	@Test
    public void buscarPorUsuarioCodUsuario_Correcto() throws Exception {
    	when(usuarioContrasenaDao.findByUsuarioCodUsuario(any())).thenReturn(crearUsuarioContrasenaCorrecto());
    	UsuarioContrasena usuarioContrasena = usuarioContrasenaService.buscarPorUsuarioCodUsuario("");
        assertEquals(usuarioContrasena, crearUsuarioContrasenaCorrecto());
    }
    
    @Test
    public void guardarUsuarioContrasena_ApiException() throws Exception {
    	when(usuarioContrasenaDao.save(any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> usuarioContrasenaService.guardarUsuarioContrasena(crearUsuarioContrasenaCorrecto()));
    }
    
    @Test
    public void buscarPorUsuarioCodUsuario_ApiException() throws Exception {
    	when(usuarioContrasenaDao.findByUsuarioCodUsuario(any())).thenReturn(null);
        assertThrows(ApiException.class, () -> usuarioContrasenaService.buscarPorUsuarioCodUsuario(""));
    }

}
