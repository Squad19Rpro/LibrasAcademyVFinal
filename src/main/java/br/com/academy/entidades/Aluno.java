package br.com.academy.entidades;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa {
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
	private Set<CursoAluno> estudantes = new HashSet<>();

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Set<CursoAluno> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(Set<CursoAluno> estudantes) {
		this.estudantes = estudantes;
	}

	@Override
	public String toString() {
		return "Aluno [senha=" + senha + ", getSenha()=" + getSenha() + ", getNome()=" + getNome() + ", getCpf()="
				+ getCpf() + ", getSexo()=" + getSexo() + ", getTelefone()=" + getTelefone() + ", getEmail()="
				+ getEmail() + ", getDataNascimento()=" + getDataNascimento() + ", getId()=" + getId() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
	
	
}
