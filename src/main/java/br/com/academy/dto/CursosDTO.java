package br.com.academy.dto;


import org.springframework.beans.BeanUtils;

import br.com.academy.entidades.CursoAluno;
import br.com.academy.entidades.Cursos;
import br.com.academy.entidades.Professor;

public class CursosDTO {
	
	private Long id;
	
	private String nomeCurso;
	
	private String descricao;
	
	private int carga_horaria;
	
	private int num_aulas;
	
	private String anoLancamento;
	
	private CursoAluno cursos;
	
    private Professor professor;
    
    public CursosDTO(){};

    public CursosDTO(Cursos curso){
        BeanUtils.copyProperties(curso, this);
    }    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCarga_horaria() {
		return carga_horaria;
	}

	public void setCarga_horaria(int carga_horaria) {
		this.carga_horaria = carga_horaria;
	}

	public int getNum_aulas() {
		return num_aulas;
	}

	public void setNum_aulas(int num_aulas) {
		this.num_aulas = num_aulas;
	}

	public String getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(String anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public CursoAluno getCursos() {
		return cursos;
	}

	public void setCursos(CursoAluno cursos) {
		this.cursos = cursos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	};
    
    
}
