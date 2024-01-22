package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.ProfessorDTO;
import br.com.academy.entidades.Professor;
import br.com.academy.repository.CursosRepository;
import br.com.academy.repository.ProfessorRepository;
import br.com.academy.service.ProfessorService;
import br.com.academy.utils.SenhaUtils;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private CursosRepository cursosRepository;
    
	@Override
    public void save(ProfessorDTO professorDTO) {
        String senhaEncriptada = SenhaUtils.encode(professorDTO.getSenha());
        professorDTO.setSenha(senhaEncriptada);
        Professor professor = new Professor(professorDTO);


        professorRepository.save(professor);
    }
	
	@Override
    public ProfessorDTO findById(Long id){
        return new ProfessorDTO(professorRepository.findById(id).get());
    }

	@Override
    public List<ProfessorDTO> findAll(){
        List<Professor> professores = professorRepository.findAll();
        return professores.stream().map(ProfessorDTO::new).toList();
    }

	@Override
    public ProfessorDTO update(ProfessorDTO professorUpdate, ProfessorDTO professorAlvo) throws Exception {
        if (professorRepository.equals(professorUpdate.getCpf())) {
            throw new Exception("Cpf já existe");
        }
        if (professorRepository.equals(professorUpdate.getEmail())) {
            throw new Exception("Email já existe");
        }
        if (professorRepository.equals(professorUpdate.getTelefone())) {
            throw new Exception("Telefone já existe");
        }
        professorUpdate.setNome(professorUpdate.getNome() != null ? professorUpdate.getNome() : professorAlvo.getNome());
        professorUpdate.setEmail(professorUpdate.getEmail() != null ? professorUpdate.getEmail() : professorAlvo.getEmail());
        professorUpdate.setSenha(professorUpdate.getSenha() != null ? professorUpdate.getSenha() : professorAlvo.getSenha());
        professorUpdate.setCpf(professorUpdate.getCpf() != null ? professorUpdate.getCpf() : professorAlvo.getCpf());
        professorUpdate.setTelefone(professorUpdate.getTelefone() != null ? professorUpdate.getTelefone() : professorAlvo.getTelefone());
        professorUpdate.setSexo(professorUpdate.getSexo() != null ? professorUpdate.getSexo() : professorAlvo.getSexo());
        professorUpdate.setDataNascimento(professorUpdate.getDataNascimento() != null ? professorUpdate.getDataNascimento() : professorAlvo.getDataNascimento());
        professorUpdate.setDataAdmissao(professorUpdate.getDataAdmissao() != null ? professorUpdate.getDataAdmissao() : professorAlvo.getDataAdmissao());
        professorUpdate.setDataDemissao(professorUpdate.getDataDemissao() != null ? professorUpdate.getDataDemissao() : professorAlvo.getDataDemissao());
        professorUpdate.setSalario(professorUpdate.getSalario() != null ? professorUpdate.getSalario() : professorAlvo.getSalario());
        professorUpdate.setCursos(professorUpdate.getCursos() != null ? professorUpdate.getCursos() : professorAlvo.getCursos());
        professorUpdate.setCargo(professorUpdate.getCargo() != null ? professorUpdate.getCargo() : professorAlvo.getCargo());

        Professor professor = new Professor(professorUpdate);
        return new ProfessorDTO(professorRepository.save(professor));
    }

	@Override
    public void deleteById(Long id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);

        if (optionalProfessor.isPresent()) {
            // O professor foi encontrado, pode deletar
            Professor professor = optionalProfessor.get();
            professorRepository.delete(professor);
        } else {
            // Professor não encontrado, lançar exceção ou lidar de outra forma
            throw new IllegalArgumentException("Professor não encontrado");
        }
    }

}
