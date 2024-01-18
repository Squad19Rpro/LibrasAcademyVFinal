package br.com.academy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.academy.entidades.Aluno;
import br.com.academy.entidades.Funcionario;
import br.com.academy.entidades.Professor;
import br.com.academy.entidades.UserDetailsImpl;
import br.com.academy.enums.UserType;
import br.com.academy.repository.AlunoRepository;
import br.com.academy.repository.FuncionarioRepository;
import br.com.academy.repository.ProfessorRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Funcionario funcionario = funcionarioRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado"));
////        Professor professor = professorRepository.findByEmail(email)
////        		.orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado"));
//
//            return new UserDetailsImpl(funcionario);
	    Funcionario funcionario = funcionarioRepository.findByEmail(email).orElse(null);
	    Professor professor = professorRepository.findByEmail(email).orElse(null);
	    Aluno aluno = alunoRepository.findByEmail(email).orElse(null);

	    if (funcionario != null) {
	        return new UserDetailsImpl(funcionario, null, null, UserType.FUNCIONARIO);
	    } else if (professor != null) {
	        return new UserDetailsImpl(null, professor, null, UserType.PROFESSOR);
	    } else if(aluno != null) {
	    	return new UserDetailsImpl(null, null, aluno, UserType.ALUNO);
	    } else {
	        throw new UsernameNotFoundException("Usuário não encontrado");
	    }
	}

}
