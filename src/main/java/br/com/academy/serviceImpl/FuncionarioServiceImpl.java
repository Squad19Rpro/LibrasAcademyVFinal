package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.FuncionarioDTO;
import br.com.academy.entidades.Funcionario;
import br.com.academy.repository.FuncionarioRepository;
import br.com.academy.service.FuncionarioService;
import br.com.academy.utils.SenhaUtils;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
	@Autowired
    private FuncionarioRepository funcionarioRepository;


	@Override
	public void save(FuncionarioDTO funcionarioDTO) {
        String senhaEncriptada = SenhaUtils.encode(funcionarioDTO.getSenha());
        funcionarioDTO.setSenha(senhaEncriptada);
        Funcionario funcionario = new Funcionario(funcionarioDTO);


        funcionarioRepository.save(funcionario);
		
	}

	@Override
	public FuncionarioDTO findById(Long id) {
        return new FuncionarioDTO(funcionarioRepository.findById(id).get());
	}

	@Override
	public List<FuncionarioDTO> findAll() {
        List<Funcionario> funcionarios = funcionarioRepository.findAll();
        return funcionarios.stream().map(FuncionarioDTO::new).toList();
	}

	@Override
	public FuncionarioDTO update(FuncionarioDTO funcionarioUpdate, FuncionarioDTO funcionarioAlvo) throws Exception {
        if (funcionarioRepository.equals(funcionarioUpdate.getCpf())) {
            throw new Exception("Cpf já existe");
        }
        if (funcionarioRepository.equals(funcionarioUpdate.getEmail())) {
            throw new Exception("Email já existe");
        }
        if (funcionarioRepository.equals(funcionarioUpdate.getTelefone())) {
            throw new Exception("Telefone já existe");
        }
        funcionarioUpdate.setNome(funcionarioUpdate.getNome() != null ? funcionarioUpdate.getNome() : funcionarioAlvo.getNome());
        funcionarioUpdate.setEmail(funcionarioUpdate.getEmail() != null ? funcionarioUpdate.getEmail() : funcionarioAlvo.getEmail());
        funcionarioUpdate.setSenha(funcionarioUpdate.getSenha() != null ? funcionarioUpdate.getSenha() : funcionarioAlvo.getSenha());
        funcionarioUpdate.setCpf(funcionarioUpdate.getCpf() != null ? funcionarioUpdate.getCpf() : funcionarioAlvo.getCpf());
        funcionarioUpdate.setTelefone(funcionarioUpdate.getTelefone() != null ? funcionarioUpdate.getTelefone() : funcionarioAlvo.getTelefone());
        funcionarioUpdate.setSexo(funcionarioUpdate.getSexo() != null ? funcionarioUpdate.getSexo() : funcionarioAlvo.getSexo());
        funcionarioUpdate.setDataNascimento(funcionarioUpdate.getDataNascimento() != null ? funcionarioUpdate.getDataNascimento() : funcionarioAlvo.getDataNascimento());
        funcionarioUpdate.setDataAdmissao(funcionarioUpdate.getDataAdmissao() != null ? funcionarioUpdate.getDataAdmissao() : funcionarioAlvo.getDataAdmissao());
        funcionarioUpdate.setDataDemissao(funcionarioUpdate.getDataDemissao() != null ? funcionarioUpdate.getDataDemissao() : funcionarioAlvo.getDataDemissao());
        funcionarioUpdate.setSalario(funcionarioUpdate.getSalario() != null ? funcionarioUpdate.getSalario() : funcionarioAlvo.getSalario());
        funcionarioUpdate.setCargo(funcionarioUpdate.getCargo() != null ? funcionarioUpdate.getCargo() : funcionarioAlvo.getCargo());

        Funcionario funcionario = new Funcionario(funcionarioUpdate);
        return new FuncionarioDTO(funcionarioRepository.save(funcionario));
	}

	@Override
	public void deleteById(Long id) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

        if (optionalFuncionario.isPresent()) {
            // O funcionario foi encontrado, pode deletar
        	Funcionario funcionario = optionalFuncionario.get();
            funcionarioRepository.delete(funcionario);
        } else {
            // Funcionario não encontrado, lançar exceção ou lidar de outra forma
            throw new IllegalArgumentException("Funcionario não encontrado");
        }
	}

}
