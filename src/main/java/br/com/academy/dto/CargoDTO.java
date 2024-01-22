package br.com.academy.dto;

import org.springframework.beans.BeanUtils;

import br.com.academy.entidades.Cargo;

public class CargoDTO {
	private Long id;

    private String nome;
    
    public CargoDTO(){};

    public CargoDTO(Cargo cargo){
        BeanUtils.copyProperties(cargo, this);
    }

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
	};
    
    
    
}
