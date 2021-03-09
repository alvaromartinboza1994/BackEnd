package com.proyectosPersonales.springboot.app.service.balance;

import static com.proyectosPersonales.springboot.app.service.balance.BalanceServiceImplTestUtil.crearBalanceCorrecto;
import static com.proyectosPersonales.springboot.app.service.balance.BalanceServiceImplTestUtil.crearBalanceCorrecto2;
import static com.proyectosPersonales.springboot.app.service.balance.BalanceServiceImplTestUtil.crearBalanceVacio;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.proyectosPersonales.springboot.app.gastos.dto.Balance;
import com.proyectosPersonales.springboot.app.gastos.repository.BalanceDaoI;
import com.proyectosPersonales.springboot.app.gastos.service.impl.BalanceServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BalanceServiceImplTest {

    @InjectMocks
    private BalanceServiceImpl balanceService;
    
    @Mock
    private BalanceDaoI balanceDao;

    @Test
    public void añadirBalance_BalanceCorrecto() throws Exception {
    	when(balanceDao.save(any())).thenReturn(crearBalanceCorrecto());
        Balance balance = balanceService.añadirBalance(crearBalanceCorrecto());
        assertEquals(balance, crearBalanceCorrecto());
    }
    
    @Test
    public void añadirBalance_BalanceVacio() throws Exception {
    	when(balanceDao.save(any())).thenReturn(crearBalanceVacio());
        Balance balance = balanceService.añadirBalance(crearBalanceCorrecto());
        assertNotEquals(balance, crearBalanceCorrecto());
    }
    
    @Test
    public void añadirBalance_BalanceDistinto() throws Exception {
    	when(balanceDao.save(any())).thenReturn(crearBalanceCorrecto());
        Balance balance = balanceService.añadirBalance(crearBalanceCorrecto2());
        assertNotEquals(balance, crearBalanceCorrecto2());
    }
}
