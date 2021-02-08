package com.proyectosPersonales.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //registrarlo como componente spring
public class PreTiempoTranscurridoFilter extends ZuulFilter{
	
	@Override
	public boolean shouldFilter() {
		return true;//si devuelve true, ejecuta el método run. Aquí lo va a ejecutar SIEMPRE
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		
		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute("tiempoInicio", tiempoInicio);

		return null;
	}

	@Override
	public String filterType() {
		return "pre";//palabra clave
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
