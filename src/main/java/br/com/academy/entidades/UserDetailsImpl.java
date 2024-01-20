package br.com.academy.entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.academy.enums.Perfil;
import br.com.academy.enums.UserType;

public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Funcionario funcionario;
	private Professor professor;
	private Aluno aluno;
	private UserType userType;

    public UserDetailsImpl(Funcionario funcionario, Professor professor, Aluno aluno, UserType userType) {
        this.funcionario = funcionario;
        this.professor = professor;
        this.aluno = aluno;
        this.userType = userType;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();

	    if (userType == UserType.FUNCIONARIO) {
	    	Perfil perfil = (funcionario.getCargo() != null && "Gerente".equals(funcionario.getCargo().getNome())) ? Perfil.ADMIN : Perfil.USER;
	        authorities.add(new SimpleGrantedAuthority(perfil.toString()));
	    } else if (userType == UserType.PROFESSOR) {
	    	Perfil perfil = (professor.getCargo() != null && "Gerente".equals(professor.getCargo().getNome())) ? Perfil.ADMIN : Perfil.USER;
	        authorities.add(new SimpleGrantedAuthority(perfil.toString()));
	    } else if(userType == UserType.ALUNO) {
	        authorities.add(new SimpleGrantedAuthority(Perfil.COMUM.toString()));
	    }

	    return authorities;
    }


	@Override
	public String getPassword() {
		if (userType == UserType.FUNCIONARIO) {
			return funcionario.getSenha();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getSenha();
		} else if (userType == UserType.ALUNO) {

			return aluno.getSenha();
		}
		return null;
	}

	@Override
	public String getUsername() {
		if (userType == UserType.FUNCIONARIO) {

			return funcionario.getEmail();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getEmail();
		} else if (userType == UserType.ALUNO) {

			return aluno.getEmail();
		}
		return null;
	}

	public String getNome() {
		if (userType == UserType.FUNCIONARIO) {

			return funcionario.getNome();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getNome();
		} else if (userType == UserType.ALUNO) {

			return aluno.getNome();
		}
		return null;
	}

	public String getCpf() {
		if (userType == UserType.FUNCIONARIO) {

			return funcionario.getCpf();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getCpf();
		} else if (userType == UserType.ALUNO) {

			return aluno.getCpf();
		}
		return null;
	}

	public String getTelefone() {
		if (userType == UserType.FUNCIONARIO) {

			return funcionario.getTelefone();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getTelefone();
		} else if (userType == UserType.ALUNO) {

			return aluno.getTelefone();
		}
		return null;
	}

	public String getSexo() {
		if (userType == UserType.FUNCIONARIO) {

			return funcionario.getSexo();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getSexo();
		} else if (userType == UserType.ALUNO) {

			return aluno.getSexo();
		}
		return null;
	}

	public String getDataNascimento() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
		if (userType == UserType.FUNCIONARIO) {
			 LocalDate dataNascimento = funcionario.getDataNascimento();
			return dataNascimento.format(formatter);
		} else if (userType == UserType.PROFESSOR) {
			LocalDate dataNascimento = professor.getDataNascimento();
			return dataNascimento.format(formatter);
		} else if (userType == UserType.ALUNO) {
			LocalDate dataNascimento = aluno.getDataNascimento();
			return dataNascimento.format(formatter);
		}
		return null;
	}

	public String getCargo() {
		if (userType == UserType.FUNCIONARIO) {

			return funcionario.getCargo().getNome();
		} else if (userType == UserType.PROFESSOR) {

			return professor.getCargo().getNome();
		}
		return null;
	}

	public String getDataAdmissao() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
		if (userType == UserType.FUNCIONARIO) {
			 LocalDate dataAdmissao = funcionario.getDataAdmissao();
			return dataAdmissao.format(formatter);
		} else if (userType == UserType.PROFESSOR) {
			LocalDate dataAdmissao = professor.getDataAdmissao();
			return dataAdmissao.format(formatter);
		}
		return null;
	}

	public String getDataDemissao() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
		String data = " - ";
		if (userType == UserType.FUNCIONARIO) {
			 LocalDate dataDemissao = funcionario.getDataDemissao();
			 if (dataDemissao != null) {
				 return dataDemissao.format(formatter);			 
			 } else {
				 return data;
			 }
		} else if (userType == UserType.PROFESSOR) {
			LocalDate dataDemissao = professor.getDataDemissao();
			if (dataDemissao != null) {
				 return dataDemissao.format(formatter);			 
			 } else {
				 return data;
			 }
		}
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
