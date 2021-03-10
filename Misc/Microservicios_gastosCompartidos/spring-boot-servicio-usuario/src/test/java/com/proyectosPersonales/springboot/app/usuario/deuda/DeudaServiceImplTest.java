package com.proyectosPersonales.springboot.app.usuario.deuda;

import static com.proyectosPersonales.springboot.app.usuario.balance.BalanceServiceImplTestUtil.crearBalanceCorrecto2;
import static com.proyectosPersonales.springboot.app.usuario.deuda.DeudaServiceImplTestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import com.proyectosPersonales.springboot.app.commons.dto.Deuda;
import com.proyectosPersonales.springboot.app.commons.exception.ApiException;
import com.proyectosPersonales.springboot.app.usuario.dao.DeudaDaoI;
import com.proyectosPersonales.springboot.app.usuario.service.impl.DeudaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DeudaServiceImplTest {

    @InjectMocks
    private DeudaServiceImpl deudaService;
    
    @Mock
    private DeudaDaoI deudaDao;

    @Test
    public void añadirBalance_DeudaCorrecto() throws Exception {
    	when(deudaDao.save(any())).thenReturn(crearDeudaCorrecto());
        Deuda deuda = deudaService.guardarDeuda(crearDeudaCorrecto());
        assertEquals(deuda, crearDeudaCorrecto());
    }
    
    @Test
    public void añadirBalance_DeudaVacio() throws Exception {
    	when(deudaDao.save(any())).thenReturn(crearDeudaVacio());
        Deuda deuda = deudaService.guardarDeuda(crearDeudaCorrecto());
        assertNotEquals(deuda, crearDeudaCorrecto());
    }
    
    @Test
    public void añadirBalance_DeudaDistinta() throws Exception {
    	when(deudaDao.save(any())).thenReturn(crearDeudaCorrecto());
        Deuda deuda = deudaService.guardarDeuda(crearDeudaCorrecto2());
        assertNotEquals(deuda, crearDeudaCorrecto2());
    }
    
    @Test
    public void buscarDeudaPorIdDeuda_DeudaCorrecta() throws Exception {
    	when(deudaDao.findByIdDeuda(any())).thenReturn(crearDeudaCorrecto());
        Deuda deuda = deudaService.buscarDeudaPorIdDeuda(1);
        assertEquals(deuda, crearDeudaCorrecto());
    }
    
    @Test
    public void añadirDeuda_ApiException() throws Exception {
    	when(deudaDao.save(any())).thenThrow(ApiException.class);
        assertThrows(ApiException.class, () -> deudaService.guardarDeuda(crearDeudaCorrecto2()));
    }
    
    @Test
    public void buscarDeuda_ApiException() throws Exception {
    	when(deudaDao.findByIdDeuda(any())).thenReturn(null);
        assertThrows(ApiException.class, () -> deudaService.buscarDeudaPorIdDeuda(1));
    }
}
