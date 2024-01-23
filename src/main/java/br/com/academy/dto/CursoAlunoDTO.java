package br.com.academy.dto;

import org.springframework.beans.BeanUtils;

import br.com.academy.entidades.Aluno;
import br.com.academy.entidades.CursoAluno;
import br.com.academy.entidades.Cursos;

public class CursoAlunoDTO {
	private Long matricula;

	private Aluno aluno;
	
	private Cursos curso;
	
	public CursoAlunoDTO() {};
	public CursoAlunoDTO(CursoAluno cursoAluno) {
		BeanUtils.copyProperties(cursoAluno, this);
	}
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Cursos getCurso() {
		return curso;
	}
	public void setCurso(Cursos curso) {
		this.curso = curso;
	}
	
	
}
