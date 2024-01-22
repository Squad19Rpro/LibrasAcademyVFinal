package br.com.academy.entidades;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import br.com.academy.dto.CargoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cargo")
public class Cargo extends Entidade {

    @Column(nullable = false, length = 40, unique = true)
    private String nome;
    
	@OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
	private Set<Professor> professores = new HashSet<>();
	
	@OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
	private Set<Funcionario> funcionarios = new HashSet<>();
	
	public Cargo(CargoDTO cargoDTO){
		BeanUtils.copyProperties(cargoDTO, this);
	};

	public Cargo(){};

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public Set<Professor> getProfessores() {
		return professores;
	}

	public void setProfessores(Set<Professor> professores) {
		this.professores = professores;
	}

	public Set<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(Set<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
    
}
