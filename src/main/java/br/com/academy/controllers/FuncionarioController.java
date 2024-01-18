package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.entidades.Funcionario;
import br.com.academy.repository.CargoRepository;
import br.com.academy.repository.FuncionarioRepository;
//import br.com.academy.utils.SenhaUtils;
import br.com.academy.utils.SenhaUtils;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("funcionario/home");

        modelAndView.addObject("funcionarios", funcionarioRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("funcionario/detalhes");

        modelAndView.addObject("funcionario", funcionarioRepository.getReferenceById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");

        modelAndView.addObject("funcionario", new Funcionario());
        modelAndView.addObject("cargos", cargoRepository.findAll());
      

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("funcionario/editarFunc");
        modelAndView.addObject("funcionario", funcionarioRepository.getReferenceById(id));
        modelAndView.addObject("cargos", cargoRepository.findAll());

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(Funcionario funcionario) {
    	String senhaEncriptada = SenhaUtils.encode(funcionario.getSenha());
        funcionario.setSenha(senhaEncriptada);
        funcionarioRepository.save(funcionario);

        return "redirect:/funcionarios";
    }

    @PostMapping("/{id}/editar")
    public String editar(Funcionario funcionario, @PathVariable Long id) {
    	String senhaAtual = funcionarioRepository.getReferenceById(id).getSenha();
        funcionario.setSenha(senhaAtual);
        funcionarioRepository.save(funcionario);
        
        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        funcionarioRepository.deleteById(id);

        return "redirect:/funcionarios";
    }
    
}

