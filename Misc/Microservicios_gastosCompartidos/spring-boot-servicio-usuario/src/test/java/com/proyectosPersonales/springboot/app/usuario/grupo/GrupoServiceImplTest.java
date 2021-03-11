package com.proyectosPersonales.springboot.app.usuario.grupo;

import static com.proyectosPersonales.springboot.app.usuario.grupo.GrupoServiceImplTestUtil.crearGrupoCorrecto;
import static com.proyectosPersonales.springboot.app.usuario.grupo.GrupoServiceImplTestUtil.crearGrupoCorrecto_ConParticipante;
import static com.proyectosPersonales.springboot.app.usuario.usuario.UsuarioServiceImplTestUtil.crearUsuarioCorrecto;
import static com.proyectosPersonales.springboot.app.usuario.usuario.UsuarioServiceImplTestUtil.crearUsuarioCorrecto_ConGrupo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyectosPersonales.springboot.app.commons.dto.Grupo;
import com.proyectosPersonales.springboot.app.commons.dto.Usuario;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.GrupoDaoI;
import com.proyectosPersonales.springboot.app.usuario.packages.service.interfaces.UsuarioService;
import com.proyectosPersonales.springboot.app.usuario.service.impl.GrupoServiceImpl;

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
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo().getNuevo());
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
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo().getNuevo());
    	when(grupoDao.save(any())).thenReturn(crearGrupoCorrecto_ConParticipante(crearUsuarioCorrecto()));
    	Grupo grupo = grupoService.añadirParticipante("", "");
        assertFalse(grupo.getParticipantes().isEmpty());
    }
    
    @Test
    public void crearGrupo_ApiException() throws Exception {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	Usuario usuario = usuarioService.buscarPorCodUsuario("");
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo().getNuevo());
    	usuario = usuarioService.actualizarUsuario(crearUsuarioCorrecto_ConGrupo());
    	when(grupoDao.save(any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> grupoService.crearGrupo("", ""));
    }
    
    @Test
    public void añadirParticipante_ApiException() {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	Usuario usuario = usuarioService.buscarPorCodUsuario("");
    	when(grupoDao.findByIdNombreGrupo(any())).thenReturn(crearGrupoCorrecto());
    	when(usuarioService.actualizarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo().getNuevo());
    	when(grupoDao.save(any())).thenThrow(ApiException.class);
    	assertThrows(ApiException.class, () -> grupoService.añadirParticipante("", ""));
    }
}
