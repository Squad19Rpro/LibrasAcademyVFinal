package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.CursoAlunoDTO;
import br.com.academy.entidades.CursoAluno;
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
		CursoAluno relacao = new CursoAluno(cursoAlunoDTO);
		relacao.setAluno(cursoAlunoDTO.getAluno());
		relacao.setCurso(cursoAlunoDTO.getCurso());
		cursoAlunoRepository.save(relacao);
	}

	@Override
	public CursoAlunoDTO findById(Long matricula) {
        return new CursoAlunoDTO(cursoAlunoRepository.findById(matricula).get());
	}

	@Override
	public List<CursoAlunoDTO> findAll() {
		List<CursoAluno> relacao = cursoAlunoRepository.findAll();
        return relacao.stream().map(CursoAlunoDTO::new).toList();
	}

	@Override
	public CursoAlunoDTO update(CursoAlunoDTO cursoAlunoUpdate, CursoAlunoDTO cursoAlunoAlvo) throws Exception {
		if (cursoAlunoRepository.findAll() == cursoAlunoUpdate ) {
			throw new Exception("Relação já existe");
		}
		cursoAlunoUpdate.setAluno(cursoAlunoUpdate.getAluno() != null ? cursoAlunoUpdate.getAluno() : cursoAlunoUpdate.getAluno());
		cursoAlunoUpdate.setCurso(cursoAlunoUpdate.getCurso() != null ? cursoAlunoUpdate.getCurso() : cursoAlunoUpdate.getCurso());
		
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
