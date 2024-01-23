package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.CursoAlunoDTO;
import br.com.academy.entidades.Aluno;
import br.com.academy.entidades.CursoAluno;
import br.com.academy.entidades.Cursos;
import br.com.academy.repository.AlunoRepository;
import br.com.academy.repository.CursoAlunoRepository;
import br.com.academy.repository.CursosRepository;
import br.com.academy.service.CursoAlunoService;

@Service
public class CursoAlunoServiceImpl implements CursoAlunoService{
	@Autowired
	private CursoAlunoRepository cursoAlunoRepository;
	
	@Autowired
	private CursosRepository cursosRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Override
	public void save(CursoAlunoDTO cursoAlunoDTO) {
		Long alunoId = cursoAlunoDTO.getAluno().getId();
	    Long cursoId = cursoAlunoDTO.getCurso().getId();
		Aluno aluno = alunoRepository.findById(alunoId).orElse(null);
		Cursos curso = cursosRepository.findById(cursoId).orElse(null);
		CursoAluno relacao = new CursoAluno(cursoAlunoDTO);
		relacao.setAluno(aluno);
		relacao.setCurso(curso);
		cursoAlunoRepository.save(relacao);
	}

	@Override
	public CursoAlunoDTO findById(Long id) {
        return new CursoAlunoDTO(cursoAlunoRepository.findById(id).get());
	}

	@Override
	public List<CursoAlunoDTO> findAll() {
		List<CursoAluno> relacao = cursoAlunoRepository.findAll();
        return relacao.stream().map(CursoAlunoDTO::new).toList();
	}

	@Override
	public CursoAlunoDTO update(CursoAlunoDTO cursoAlunoUpdate, CursoAlunoDTO cursoAlunoAlvo) throws Exception {
	    cursoAlunoUpdate.setAluno(cursoAlunoUpdate.getAluno() != null ? cursoAlunoUpdate.getAluno() : cursoAlunoAlvo.getAluno());
	    cursoAlunoUpdate.setCurso(cursoAlunoUpdate.getCurso() != null ? cursoAlunoUpdate.getCurso() : cursoAlunoAlvo.getCurso());
        
	    CursoAluno cursoAluno = new CursoAluno(cursoAlunoUpdate);
		return new CursoAlunoDTO(cursoAlunoRepository.save(cursoAluno));
	}

	@Override
	public void deleteById(Long matricula) {
        Optional<CursoAluno> optionalCA = cursoAlunoRepository.findById(matricula);

        if (optionalCA.isPresent()) {
            CursoAluno cursoAluno = optionalCA.get();
            cursoAlunoRepository.delete(cursoAluno);
        } else {
            throw new IllegalArgumentException("Relação não encontrada");
        }
	}

}
