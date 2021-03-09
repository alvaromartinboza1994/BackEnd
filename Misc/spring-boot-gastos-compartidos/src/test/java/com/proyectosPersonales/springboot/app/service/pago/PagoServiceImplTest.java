package com.proyectosPersonales.springboot.app.service.pago;

import static com.proyectosPersonales.springboot.app.service.pago.PagoServiceImplTestUtil.*;
import static com.proyectosPersonales.springboot.app.service.usuario.UsuarioServiceImplTestUtil.*;
import static com.proyectosPersonales.springboot.app.service.grupo.GrupoServiceImplTestUtil.*;
import static com.proyectosPersonales.springboot.app.service.balance.BalanceServiceImplTestUtil.*;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.dto.Grupo;
import com.proyectosPersonales.springboot.app.gastos.dto.Usuario;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioDeuda;
import com.proyectosPersonales.springboot.app.gastos.dto.UsuarioPago;
import com.proyectosPersonales.springboot.app.gastos.repository.PagoDaoI;
import com.proyectosPersonales.springboot.app.gastos.repository.UsuarioDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.impl.PagoServiceImpl;
import com.proyectosPersonales.springboot.app.gastos.service.impl.UsuarioServiceImpl;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.BalanceService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.GrupoService;
import com.proyectosPersonales.springboot.app.gastos.service.interfaces.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class PagoServiceImplTest {

    @InjectMocks
    private PagoServiceImpl pagoService;
    
    @Mock
    private PagoDaoI pagoDao;
    
    @Mock
    private UsuarioService usuarioService;
    
    @Mock
    private GrupoService grupoService;
    
    @Mock
    private BalanceService balanceService;

    @Test
    public void añadirUsuarioPago_Correcto()  {
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto());
    	when(grupoService.buscarGrupo(any())).thenReturn(crearGrupoCorrecto_ConParticipante(crearUsuarioCorrecto()));
    	when(usuarioService.guardarUsuario(any())).thenReturn(crearUsuarioCorrecto_ConGrupo());
    	Usuario usuario = pagoService.añadirUsuarioPago(crearUsuarioPago_Correcto(crearUsuarioCorrecto2()));
    	assertFalse(usuario.getMisPagos().isEmpty());
    }
    
    @Test
    public void consultarPagosCompartidos_Correcto() {
    	when(grupoService.buscarGrupo(any())).thenReturn(crearGrupoCorrecto_ConParticipante(crearUsuarioCorrecto()));
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto_ConPago());
    	List<UsuarioPago> usuariosPagos = pagoService.consultarPagosCompartidos("");
    	assertFalse(usuariosPagos.isEmpty());
    }
    
    @Test
    public void calcularBalance_Correcto() {
    	when(grupoService.buscarGrupo(any())).thenReturn(crearGrupoCorrecto_ConParticipante(crearUsuarioCorrecto()));
    	when(usuarioService.buscarPorCodUsuario(any())).thenReturn(crearUsuarioCorrecto_ConPago());
    	when(balanceService.añadirBalance(any())).thenReturn(crearBalanceCorrecto());
    	List<Balance> listaBalances = pagoService.calcularBalance("");
    	assertFalse(listaBalances.isEmpty());
    }
    
    @Test
    public void calcularMinimoPagos_Correcto() {
    	Grupo grupo = crearGrupoCorrecto_ConParticipantes(crearListaUsuarios());
    	when(grupoService.buscarGrupo(any())).thenReturn(grupo);
    	List<UsuarioDeuda> listaUsuariosDeudas = pagoService.calcularMinimoPagos(grupo.getId().getNombreGrupo());
    	assertFalse(listaUsuariosDeudas.isEmpty());
    }
}
