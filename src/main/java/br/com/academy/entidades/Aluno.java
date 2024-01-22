package br.com.academy.entidades;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import br.com.academy.dto.AlunoDTO
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa {
	
	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
	private Set<CursoAluno> estudantes = new HashSet<>();
	
	public Aluno(AlunoDTO alunoDTO){
		BeanUtils.copyProperties(alunoDTO, this);
	};

	public Aluno(){};
	
	public Set<CursoAluno> getEstudantes() {
		return estudantes;
	}

	public void setEstudantes(Set<CursoAluno> estudantes) {
		this.estudantes = estudantes;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + ", getNome()=" + getNome() + ", getCpf()="
				+ getCpf() + ", getSexo()=" + getSexo() + ", getTelefone()=" + getTelefone() + ", getEmail()="
				+ getEmail() + ", getDataNascimento()=" + getDataNascimento() + ", getId()=" + getId() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
	
	
}
