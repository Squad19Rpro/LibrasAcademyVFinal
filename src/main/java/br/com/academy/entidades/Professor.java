package br.com.academy.entidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.academy.dto.ProfessorDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor")
public class Professor extends Pessoa {
	
	@Column(name = "data_admissao", nullable = false)
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataAdmissao;

    @Column(name = "data_demissao")
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataDemissao;
    
    @Column(nullable = false)
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal salario;
	
	@OneToMany(mappedBy = "professor", fetch = FetchType.LAZY)
	private Set<Cursos> cursos = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo_id_fk", nullable = false)
    private Cargo cargo;
	
	public Professor(){};
	public Professor(ProfessorDTO professorDTO){
		BeanUtils.copyProperties(professorDTO, this);
	};

	public Set<Cursos> getCursos() {
		return cursos;
	}

	public void setCursos(Set<Cursos> cursos) {
		this.cursos = cursos;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public LocalDate getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	

	
}
