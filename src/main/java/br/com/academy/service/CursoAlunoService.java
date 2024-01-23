package br.com.academy.service;

import java.util.List;

import br.com.academy.dto.CursoAlunoDTO;

public interface CursoAlunoService {
	void save(CursoAlunoDTO cursoAlunoDTO);
	
	CursoAlunoDTO findById(Long id);
	
	List<CursoAlunoDTO> findAll();
	
	CursoAlunoDTO update(CursoAlunoDTO cursoAlunoUpdate, CursoAlunoDTO cursoAlunoAlvo) throws Exception;
	
	void deleteById(Long matricula);
}
