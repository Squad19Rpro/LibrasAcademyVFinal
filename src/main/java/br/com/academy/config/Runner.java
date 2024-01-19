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
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private CargoRepository cR;
	
	@Override
	public void run(String... args) throws Exception{
		Cargo cargo = new Cargo();
		Funcionario funcionario = new Funcionario();
		
		cargo.setId((long) 1);
		cargo.setNome("Gerente");
		funcionario.setId((long) 1);
		funcionario.setNome("Administrador");
		funcionario.setCpf("000.000.000-00");
		funcionario.setSexo("Outro");
		funcionario.setDataNascimento(LocalDate.now());
		funcionario.setTelefone("(00) 00000-0000");
		funcionario.setSalario(new BigDecimal("5000.00"));
		funcionario.setDataAdmissao(LocalDate.now());
		funcionario.setDataDemissao(null);		
		funcionario.setEmail("admin@academy.com");
		String senhaEncriptada = SenhaUtils.encode("12345");
		funcionario.setSenha(senhaEncriptada);
		funcionario.setCargo(cargo);
		
		if(cargo != cR.findAll()) {
			cR.save(cargo);
		}
		if (funcionario != funcionarioRepository.findAll()) {
			funcionarioRepository.save(funcionario);
		}	
	
	
	}
	
}
