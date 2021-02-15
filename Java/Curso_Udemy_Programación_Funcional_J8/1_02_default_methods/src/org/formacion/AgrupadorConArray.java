package org.formacion;

import java.util.ArrayList;
import java.util.List;

public class AgrupadorConArray implements Agrupador {

	private Object[] contenido = new Object[20];
	private int index = 0;
	
	@Override
	public void add(Object elemento) {
		contenido[index++] = elemento;
	}

	@Override
	public int numeroElementos() {
		return index;
	}
	
	

}
