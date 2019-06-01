/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.wda.OpenBeerProject.Controller;

import br.com.wda.OpenBeerProject.Entity.Endereco;
import br.com.wda.OpenBeerProject.Repository.EnderecoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Darlan Silva
 * @author Wesley Moura
 * @author Alison Souza
 */

@Controller
@RequestMapping("/OpenBeer/endereco")
public class EnderecoController {
    
    @Autowired
    private EnderecoRepository endRepository;
    
    @GetMapping
    public ModelAndView homeEndereco(){
        return new ModelAndView("/dados-endereco").addObject("endereco", new Endereco());
    }
    
    @GetMapping("/lista-de-endereco")
    public ModelAndView listarLogin(){
        List<Endereco> endereco = endRepository.findAll();
        return new ModelAndView("endereco-lista").addObject("endereco", endereco);
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Endereco> listEndereco = endRepository.findById(id);
        Endereco endereco = listEndereco.get();
        
        return new ModelAndView("dados-endereco").addObject("endereco", endereco);
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("endereco") Endereco endereco, RedirectAttributes redirectAttributes){
        endereco.setDhInclusao(LocalDateTime.now());
        endereco.setInativo(0);
        endereco.setIdCliente(2);
        
        if(endereco.getId() != null){
            endereco.setDhAlteracao(LocalDateTime.now());
        }
        
        endRepository.save(endereco);
        redirectAttributes.addFlashAttribute("menssagemSucesso", "Endereço cadastrado com sucesso");
        
        return new ModelAndView("redirect:/OpenBeer/Home");
    }
    
}
