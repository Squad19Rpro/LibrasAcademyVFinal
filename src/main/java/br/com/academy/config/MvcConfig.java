package br.com.academy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/sobre").setViewName("pages/sobre");
		registry.addViewController("/contato").setViewName("pages/contato");
		registry.addViewController("/cadastro").setViewName("pages/cadastro");
		registry.addViewController("/curso").setViewName("pages/cursos");
		registry.addViewController("/curso/curso-java").setViewName("pages/cursos/java/introducao");
		registry.addViewController("/curso/curso-java/modulo-1").setViewName("pages/cursos/java/Java");
		registry.addViewController("/curso/curso-php").setViewName("pages/cursos/php/introducao");
		registry.addViewController("/curso/curso-php/modulo-1").setViewName("pages/cursos/php/PHP");
		registry.addViewController("/perfil").setViewName("usuario/perfil");
		
	}

}