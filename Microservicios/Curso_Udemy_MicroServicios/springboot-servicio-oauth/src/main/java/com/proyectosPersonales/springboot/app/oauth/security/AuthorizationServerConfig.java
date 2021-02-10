package com.proyectosPersonales.springboot.app.oauth.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private Environment env;

	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;// hay que registrarlo en AuthorizationServerConfigurerAdapter

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll") //permisos que van a tener los endpoints de oauth para generar y validar el token
		.checkTokenAccess("isAuthenticated()"); //validar el token, metodo de springSecurity que nos permite ver si el usuario está autenticado
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(env.getProperty("config.security.oauth.client.id"))//registrar clientes, necesita del identificador de nuestra aplicacion.
		.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))//Secret = contraseña 
		.scopes("read", "write") //alcance de nuestra aplicacion, permiso
		.authorizedGrantTypes("password", "refresh_token") //tipo de concesion que va a tener nuestra autenticacion, cómo vamos a obtener el token. Utilizamos password cuando usamos credenciales, refresh_token, es una concesion que nos permite obtener un nuevo token de acceso completamente renovado (antes de que caduque el actual) 
		.accessTokenValiditySeconds(3600)//tiempo de validez del token antes de que caduque
		.refreshTokenValiditySeconds(3600);
		/*.and() para separar clientes
		.withClient("androiddapp")//registrar clientes, necesita del identificador de nuestra aplicacion.
		.secret(passwordEncoder.encode("12345"))//Secret = contraseña 
		.scopes("read", "write") //alcance de nuestra aplicacion, permiso
		.authorizedGrantTypes("password", "refresh_token") //tipo de concesion que va a tener nuestra autenticacion, cómo vamos a obtener el token. Utilizamos password cuando usamos credenciales, refresh_token, es una concesion que nos permite obtener un nuevo token de acceso completamente renovado (antes de que caduque el actual) 
		.accessTokenValiditySeconds(3600)//tiempo de validez del token antes de que caduque
		.refreshTokenValiditySeconds(3600);*/
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accesTokenConverter())); //para poder añadir informacion adicional al token
		
		//registrar authenticationManager
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore()) //componente que se encarga de guardar el token, generar el token con los datos del accesTokenConverter
		.accessTokenConverter(accesTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accesTokenConverter());//necesitamos el componente que se encarga de convertir el token en jwt
	}

	@Bean
	public JwtAccessTokenConverter accesTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
		return tokenConverter;
	}
	
	
}
