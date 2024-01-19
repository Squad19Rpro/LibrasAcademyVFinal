package br.com.academy.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.academy.enums.Perfil;
import br.com.academy.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();    
	}

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                		.requestMatchers("/css/**", "/images/**", "/js/**", "/sass/**", "/scripts/**").permitAll()                        
                        .requestMatchers("/", "/sobre", "/contato", "/cadastro", "/curso", "/**/cadastroSite", "/**/cadastroMsg").permitAll()
                        .requestMatchers("/cursos", "/cursoAndamento").hasAnyAuthority(Perfil.USER.toString(), Perfil.ADMIN.toString())
                        .requestMatchers("/alunos/**","/funcionarios/**","/professores/**","/cargos/**","/faleMsg/**").hasAuthority(Perfil.ADMIN.toString())
                        .requestMatchers("/**/cadastrar","/**/excluir", "/**/editar").hasAuthority(Perfil.ADMIN.toString())
                        .anyRequest().authenticated());

        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll());

        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll());
	        
			return http.build();
	    }
	  
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth
			.userDetailsService(userDetailsServiceImpl)
			.passwordEncoder(passwordEncoder());
		}
		
}
