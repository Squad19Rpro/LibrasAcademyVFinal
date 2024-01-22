package br.com.academy.dto;

import br.com.academy.entidades.Aluno;
import br.com.academy.entidades.CursoAluno;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class AlunoDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String telefone;

    private String sexo;

    private LocalDate dataNascimento;

    private CursoAluno estudantes;

    public AlunoDTO(){};

    public AlunoDTO(Aluno aluno){
        BeanUtils.copyProperties(aluno, this);
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

    public CursoAluno getEstudantes() {
        return estudantes;
    }
    public void setEstudantes(CursoAluno estudantes) {
        this.estudantes = estudantes;
    }
}