package br.com.academy.dto;

import org.springframework.beans.BeanUtils;

import br.com.academy.entidades.FaleConosco;

public class FaleConoscoDTO {
	private String nome_msg;
	
	private String email_msg;
	
	private String assunto;
	
	private String mensagem;
	
	public FaleConoscoDTO() {};
	
	public FaleConoscoDTO(FaleConosco faleConosco) {
		BeanUtils.copyProperties(faleConosco, this);
	}

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
