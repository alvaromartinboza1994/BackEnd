package com.proyectosPersonales.springboot.app.service.deuda;

import static com.proyectosPersonales.springboot.app.service.deuda.DeudaServiceImplTestUtil.*;
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

import com.proyectosPersonales.springboot.app.gastos.dto.Deuda;
import com.proyectosPersonales.springboot.app.gastos.repository.DeudaDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.impl.DeudaServiceImpl;

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
    public void eliminarBalance() throws Exception {
    	when(deudaDao.save(any())).thenReturn(crearDeudaCorrecto());
        Deuda deuda = deudaService.guardarDeuda(crearDeudaCorrecto());
        deudaService.eliminarDeuda(deuda);
        when(deudaDao.findByIdDeuda(any())).thenReturn(null);
        assertThrows(HttpClientErrorException.class, () -> {
        	deudaService.buscarDeudaPorIdDeuda(deuda.getIdDeuda());
        });
    }
    
    @Test
    public void buscarDeudaPorIdDeuda_DeudaCorrecta() throws Exception {
    	when(deudaDao.findByIdDeuda(any())).thenReturn(crearDeudaCorrecto());
        Deuda deuda = deudaService.buscarDeudaPorIdDeuda(1);
        assertNotEquals(deuda, crearDeudaCorrecto());
    }
}
