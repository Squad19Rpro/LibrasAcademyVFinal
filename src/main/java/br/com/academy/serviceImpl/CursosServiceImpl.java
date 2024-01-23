package br.com.academy.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.academy.dto.CursosDTO;
import br.com.academy.entidades.Cursos;
import br.com.academy.repository.CursosRepository;
import br.com.academy.service.CursosService;

@Service
public class CursosServiceImpl implements CursosService {
	@Autowired
	private CursosRepository cursosRepository;
	
	@Override
	public void save(CursosDTO cursosDTO) {
		Cursos curso = new Cursos(cursosDTO);
		cursosRepository.save(curso);		
	}

	@Override
	public CursosDTO findById(Long id) {
		return new CursosDTO(cursosRepository.findById(id).get());
	}

	@Override
	public List<CursosDTO> findAll() {
		List<Cursos> cursos = cursosRepository.findAll();
		return cursos.stream().map(CursosDTO::new).toList();
	}

	@Override
	public CursosDTO update(CursosDTO cursosUpdate, CursosDTO cursosAlvo) throws Exception {
		if (cursosRepository.equals(cursosUpdate.getNomeCurso())) {
			throw new Exception("Curso já existe");
		}
		cursosUpdate.setNomeCurso(cursosUpdate.getNomeCurso() != null ? cursosUpdate.getNomeCurso() : cursosAlvo.getNomeCurso());
		cursosUpdate.setAnoLancamento(cursosUpdate.getAnoLancamento() != null ? cursosUpdate.getAnoLancamento() : cursosAlvo.getAnoLancamento());
		cursosUpdate.setCarga_horaria(cursosUpdate.getCarga_horaria() >= 0 ? cursosUpdate.getCarga_horaria() : cursosAlvo.getCarga_horaria());
		cursosUpdate.setNum_aulas(cursosUpdate.getNum_aulas() >= 0 ? cursosUpdate.getNum_aulas() : cursosAlvo.getNum_aulas());
		cursosUpdate.setDescricao(cursosUpdate.getDescricao() != null ? cursosUpdate.getDescricao() : cursosAlvo.getDescricao());
		cursosUpdate.setProfessor(cursosUpdate.getProfessor() != null ? cursosUpdate.getProfessor() : cursosAlvo.getProfessor());

		Cursos curso = new Cursos(cursosUpdate);
		return new CursosDTO(cursosRepository.save(curso));
	}

	@Override
	public void deleteById(Long id) {
		Optional<Cursos> optionalCurso = cursosRepository.findById(id);

		if (optionalCurso.isPresent()) {
			// O curso foi encontrado, pode deletar
			Cursos curso = optionalCurso.get();
			cursosRepository.delete(curso);
		} else {
			// Curso não encontrado, lançar exceção ou lidar de outra forma
			throw new IllegalArgumentException("Curso não encontrado");
		}
	}

}
