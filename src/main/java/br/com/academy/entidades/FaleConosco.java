package br.com.academy.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fale_conosco")
public class FaleConosco extends Entidade {
	@Column(name = "nome_msg", columnDefinition = "VARCHAR (100) NOT NULL")
	private String nome_msg;
	
	@Column(name = "email_msg", columnDefinition = "VARCHAR (100) NOT NULL")
	private String email_msg;
	
	@Column(name = "assunto", columnDefinition = "VARCHAR (100) NOT NULL")
	private String assunto;
	
	@Column(name = "mensagem", columnDefinition = "TEXT")
	private String mensagem;

	public String getNome_msg() {
		return nome_msg;
	}

	public void setNome_msg(String nome_msg) {
		this.nome_msg = nome_msg;
	}

	public String getEmail_msg() {
		return email_msg;
	}

	public void setEmail_msg(String email_msg) {
		this.email_msg = email_msg;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
