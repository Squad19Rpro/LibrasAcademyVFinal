package br.com.academy.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                		.requestMatchers("/css/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/sass/**").permitAll()
                        .requestMatchers("/scripts/**").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/sobre").permitAll()
                        .requestMatchers("/contato").permitAll()
                        .requestMatchers("/cadastro").permitAll()
                        .requestMatchers("/curso").permitAll()
                        .requestMatchers("/**/cadastroSite").permitAll()
                        .requestMatchers("/**/cadastroMsg").permitAll()
                        .requestMatchers("/templates/**").permitAll()
                        .requestMatchers("/**/cadastrar").hasAuthority(Perfil.ADMIN.toString())
        	            .requestMatchers("/**/editar").hasAuthority(Perfil.ADMIN.toString())
        	            .requestMatchers("/**/excluir").hasAuthority(Perfil.ADMIN.toString())
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
		
//		@Bean
//		InMemoryUserDetailsManager userDetailsService() {
//			UserDetails usuario = User.withUsername("admin@academy.com").password(passwordEncoder().encode("12345")).authorities(Perfil.ADMIN.toString()).build();
//			return new InMemoryUserDetailsManager(usuario);
//		}
}