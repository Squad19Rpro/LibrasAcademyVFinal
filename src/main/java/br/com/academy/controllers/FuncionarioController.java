package br.com.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.academy.dto.FuncionarioDTO;
import br.com.academy.entidades.Funcionario;
import br.com.academy.service.CargoService;
import br.com.academy.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("funcionario/home");

        modelAndView.addObject("funcionarios", funcionarioService.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("funcionario/detalhes");

        modelAndView.addObject("funcionario", funcionarioService.findById(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("funcionario/formulario");

        modelAndView.addObject("funcionario", new Funcionario());
        modelAndView.addObject("cargos", cargoService.findAll());
      

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("funcionario/editarFunc");
        modelAndView.addObject("funcionario", funcionarioService.findById(id));
        modelAndView.addObject("cargos", cargoService.findAll());

        return modelAndView;
    }
    
    @PostMapping("/cadastrar")
    public String cadastrar(FuncionarioDTO FuncionarioDTO) {
    	funcionarioService.save(FuncionarioDTO);

        return "redirect:/funcionarios";
    }

    @PostMapping("/{id}/editar")
    public String editar(FuncionarioDTO funcionarioUpdate, @PathVariable Long id) throws Exception {
    	FuncionarioDTO funcionarioAlvo = funcionarioService.findById(id);
        funcionarioService.update(funcionarioUpdate, funcionarioAlvo);

        return "redirect:/funcionarios";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        funcionarioService.deleteById(id);

        return "redirect:/funcionarios";
    }
    
}

