package br.com.academy.entidades;

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
	    	Perfil perfil = (aluno != null) ? Perfil.COMUM : Perfil.COMUM;
	        authorities.add(new SimpleGrantedAuthority(perfil.toString()));
	    }

	    return authorities;
//		Perfil perfil = funcionario.getCargo().getNome().equals("Gerente") ? Perfil.ADMIN :	Perfil.USER;
//		//Perfil perfil2 = professor.getCargo().getNome().equals("Professor") ?	Perfil.USER : Perfil.USER;
//		return AuthorityUtils.createAuthorityList(perfil.toString());
    }


    @Override
    public String getPassword() {
      if (userType == UserType.FUNCIONARIO) {
      // Retorna a senha do funcionário
      return funcionario.getSenha();
  } else if (userType == UserType.PROFESSOR) {
      // Retorna a senha do professor
      return professor.getSenha();
  } else if (userType == UserType.ALUNO) {
      // Retorna a senha do professor
      return aluno.getSenha();
  }
  return null;

  //      return funcionario.getSenha();
    }

	@Override
	public String getUsername() {
	      if (userType == UserType.FUNCIONARIO) {
	          // Retorna o email do funcionário
	          return funcionario.getEmail();
	      } else if (userType == UserType.PROFESSOR) {
	          // Retorna o email do professor
	          return professor.getEmail();
	      } else if (userType == UserType.ALUNO) {
	          // Retorna a senha do professor
	          return aluno.getSenha();
	      }
	      return null;
	//	return funcionario.getEmail();
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
