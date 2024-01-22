package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.AlunoDTO;
import br.com.academy.entidades.Aluno;
import br.com.academy.repository.AlunoRepository;
import br.com.academy.service.AlunoService;
import br.com.academy.utils.SenhaUtils;

@Service
public class AlunoServiceImpl implements AlunoService {
	@Autowired
	private AlunoRepository alunoRepository;

	public void save(AlunoDTO alunoDTO) {
		Aluno aluno = new Aluno(alunoDTO);
		String senhaEncriptada = SenhaUtils.encode(alunoDTO.getSenha());
		alunoDTO.setSenha(senhaEncriptada);
		alunoRepository.save(aluno);
	}
	
	@Override
	public AlunoDTO findById(Long id) {
		return new AlunoDTO(alunoRepository.findById(id).get());
	}
	
	@Override
	public List<AlunoDTO> findAll() {
		List<Aluno> alunos = alunoRepository.findAll();
		return alunos.stream().map(AlunoDTO::new).toList();
	}
	
	@Override
	public AlunoDTO update(AlunoDTO alunoUpdate, AlunoDTO alunoAlvo) throws Exception {
		if (alunoRepository.equals(alunoUpdate.getCpf())) {
			throw new Exception("Cpf já existe");
		}
		if (alunoRepository.equals(alunoUpdate.getEmail())) {
			throw new Exception("Email já existe");
		}
		if (alunoRepository.equals(alunoUpdate.getTelefone())) {
			throw new Exception("Telefone já existe");
		}
		alunoUpdate.setNome(alunoUpdate.getNome() != null ? alunoUpdate.getNome() : alunoAlvo.getNome());
		alunoUpdate.setCpf(alunoUpdate.getCpf() != null ? alunoUpdate.getCpf() : alunoAlvo.getCpf());
		alunoUpdate.setEmail(alunoUpdate.getEmail() != null ? alunoUpdate.getEmail() : alunoAlvo.getEmail());
		alunoUpdate.setSenha(alunoUpdate.getSenha() != null ? alunoUpdate.getSenha() : alunoAlvo.getSenha());
		alunoUpdate
				.setTelefone(alunoUpdate.getTelefone() != null ? alunoUpdate.getTelefone() : alunoAlvo.getTelefone());
		alunoUpdate.setSexo(alunoUpdate.getSexo() != null ? alunoUpdate.getSexo() : alunoAlvo.getSexo());
		alunoUpdate.setDataNascimento(alunoUpdate.getDataNascimento() != null ? alunoUpdate.getDataNascimento()
				: alunoAlvo.getDataNascimento());

		Aluno aluno = new Aluno(alunoUpdate);
		return new AlunoDTO(alunoRepository.save(aluno));
	}
	
	@Override
	public void deleteById(Long id) {
		Optional<Aluno> optionalAluno = alunoRepository.findById(id);

		if (optionalAluno.isPresent()) {
			// O aluno foi encontrado, pode deletar
			Aluno aluno = optionalAluno.get();
			alunoRepository.delete(aluno);
		} else {
			// Aluno não encontrado, lançar exceção ou lidar de outra forma
			throw new IllegalArgumentException("Aluno não encontrado");
		}
	}
}
