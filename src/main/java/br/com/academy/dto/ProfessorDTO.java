package br.com.academy.dto;

import br.com.academy.entidades.Cargo;
import br.com.academy.entidades.Cursos;
import br.com.academy.entidades.Professor;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public class ProfessorDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String telefone;

    private String sexo;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataNascimento;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataAdmissao;
    
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate dataDemissao;

    @NumberFormat(style = NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
    private BigDecimal salario;

    private Set<Cursos> cursos;

    private Cargo cargo;

    public ProfessorDTO(){};

    public ProfessorDTO(Professor professor){
        BeanUtils.copyProperties(professor, this);
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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
}