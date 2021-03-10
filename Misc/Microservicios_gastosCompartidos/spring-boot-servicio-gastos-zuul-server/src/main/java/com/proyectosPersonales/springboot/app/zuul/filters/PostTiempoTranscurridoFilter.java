package com.proyectosPersonales.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //registrarlo como componente spring
public class PostTiempoTranscurridoFilter extends ZuulFilter{
	
	@Override
	public boolean shouldFilter() {
		return true;//si devuelve true, ejecuta el método run. Aquí lo va a ejecutar SIEMPRE
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info("Entrando a post filter");
		
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s s", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s ms", tiempoTranscurrido));
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";//palabra clave
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
