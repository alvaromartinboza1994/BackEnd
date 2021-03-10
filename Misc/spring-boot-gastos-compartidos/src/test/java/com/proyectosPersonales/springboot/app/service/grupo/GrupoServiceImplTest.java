package com.proyectosPersonales.springboot.app.service.grupo;

import static com.proyectosPersonales.springboot.app.service.grupo.GrupoServiceImplTestUtil.*;
import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.exception.ApiException;
import com.proyectosPersonales.springboot.app.gastos.repository.GrupoDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.impl.GrupoServiceImpl;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceImplTest {

    @InjectMocks
    private GrupoServiceImpl grupoService;
    
    @Mock
    private GrupoDaoI grupoDao;
    
    @Mock
    UsuarioService usuarioService;

    @Test
    public void crearGrupo_Correcto() throws Exception {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	Usuario usuario = usuarioService.buscarPorCodUsuario("");
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo());
    	usuario = usuarioService.actualizarUsuario(crearUsuarioCorrecto_ConGrupo());
    	when(grupoDao.save(any())).thenReturn(crearGrupoCorrecto());
    	Grupo grupo = grupoService.crearGrupo("", "");
        assertEquals(grupo.getId().getNombreGrupo(), usuario.getMiGrupo());
    }
    
    @Test
    public void buscarGrupo_GrupoCorrecto() {
    	when(grupoDao.findByIdNombreGrupo(any())).thenReturn(crearGrupoCorrecto());
        Grupo grupo = grupoService.buscarGrupo("");
        assertEquals(grupo, crearGrupoCorrecto());
    }
    
    @Test
    public void buscarGrupo_GrupoNoEncontrado() {
    	when(grupoDao.findByIdNombreGrupo(any())).thenReturn(null);
        assertThrows(ApiException.class, () -> {
        	grupoService.buscarGrupo("");
        });
    }
    
    @Test
    public void añadirParticipante_Correcto() {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	Usuario usuario = usuarioService.buscarPorCodUsuario("");
    	when(grupoDao.findByIdNombreGrupo(any())).thenReturn(crearGrupoCorrecto());
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo());
    	when(grupoDao.save(any())).thenReturn(crearGrupoCorrecto_ConParticipante(crearUsuarioCorrecto()));
    	Grupo grupo = grupoService.añadirParticipante("", "");
        assertFalse(grupo.getParticipantes().isEmpty());
    }
    
    @Test
    public void crearGrupo_ApiException() throws Exception {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	Usuario usuario = usuarioService.buscarPorCodUsuario("");
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo());
    	usuario = usuarioService.actualizarUsuario(crearUsuarioCorrecto_ConGrupo());
    	when(grupoDao.save(any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> grupoService.crearGrupo("", ""));
    }
    
    @Test
    public void añadirParticipante_ApiException() {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	Usuario usuario = usuarioService.buscarPorCodUsuario("");
    	when(grupoDao.findByIdNombreGrupo(any())).thenReturn(crearGrupoCorrecto());
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo());
    	when(grupoDao.save(any())).thenThrow(ApiException.class);
    	assertThrows(ApiException.class, () -> grupoService.añadirParticipante("", ""));
    }
    
}
