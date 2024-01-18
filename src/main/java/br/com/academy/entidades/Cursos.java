package br.com.academy.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Cursos extends Entidade {
	
	@Column(name = "nome_curso", nullable = false)
	private String nomeCurso;
	
	@Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
	private String descricao;
	
	@Column(name = "carga_horaria", nullable = false)
	private int carga_horaria;
	
	@Column(name = "num_aulas", nullable = false)
	private int num_aulas;
	
	@Column(name = "ano_lancamento", nullable = false)
	private String anoLancamento;
	
	@OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
	private Set<CursoAluno> cursos = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id_fk")
    private Professor professor;

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

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "Cursos [nomeCurso=" + nomeCurso + ", descricao=" + descricao + ", carga_horaria=" + carga_horaria
				+ ", num_aulas=" + num_aulas + ", anoLancamento=" + anoLancamento + ", professor=" + professor
				+ ", getNomeCurso()=" + getNomeCurso() + ", getDescricao()=" + getDescricao() + ", getCarga_horaria()="
				+ getCarga_horaria() + ", getNum_aulas()=" + getNum_aulas() + ", getAnoLancamento()="
				+ getAnoLancamento() + ", getProfessor()=" + getProfessor() + ", getId()=" + getId() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	public Set<CursoAluno> getCursos() {
		return cursos;
	}

	public void setCursos(Set<CursoAluno> cursos) {
		this.cursos = cursos;
	}



	
	
}
