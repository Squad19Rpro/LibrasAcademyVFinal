package br.com.academy.config;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.academy.entidades.Cargo;
import br.com.academy.entidades.Funcionario;
import br.com.academy.repository.CargoRepository;
import br.com.academy.repository.FuncionarioRepository;
import br.com.academy.utils.SenhaUtils;

@Configuration
public class Runner implements CommandLineRunner {
	@Autowired
	private FuncionarioRepository fR;
	
	@Autowired
	private CargoRepository cR;
	
	@Override
	public void run(String... args) throws Exception{
		Cargo c = new Cargo();
		Funcionario f = new Funcionario();
		
		c.setId((long) 1);
		c.setNome("Gerente");
		f.setId((long) 1);
		f.setNome("admin");
		f.setCpf("000.000.000-00");
		f.setSexo("Outro");
		f.setDataNascimento(LocalDate.now());
		f.setTelefone("(00) 00000-0000");
		f.setSalario(new BigDecimal("5000.00"));
		f.setDataAdmissao(LocalDate.now());
		f.setDataDemissao(null);		
		f.setEmail("admin@academy.com");
		String senhaEncriptada = SenhaUtils.encode("12345");
		f.setSenha(senhaEncriptada);
		f.setCargo(c);
		
		if(c != cR.findAll()) {
			cR.save(c);
		}
		if (c != fR.findAll()) {
			fR.save(f);
		}	
	
	
	}
	
}
