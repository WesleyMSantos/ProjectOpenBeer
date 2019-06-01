package br.com.wda.OpenBeerProject.Controller;

import br.com.wda.OpenBeerProject.Entity.Cliente;
import br.com.wda.OpenBeerProject.Repository.ClienteRepository;
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
@RequestMapping("/OpenBeer/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("pessoais-dados")
    public ModelAndView dadosPessoais(){
        return new ModelAndView("/dados-pessoais").addObject("cliente", new Cliente());
    }
    
    @GetMapping("/lista-de-cliente")
    public ModelAndView listarCliente(){
        List<Cliente> cliente = clienteRepository.findAll();
        return new ModelAndView("cliente-lista").addObject("cliente", cliente);
    }
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Cliente> listCliente = clienteRepository.findById(id);
        Cliente cliente = listCliente.get();
        return new ModelAndView("dados-pessoais").addObject("cliente", cliente);
    }
    
    @PostMapping("/salvar")
    public ModelAndView salvar(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes redirectAttributes){
        cliente.setDhInclusao(LocalDateTime.now());
        cliente.setInativo(0);
        cliente.setLogin(1);
        
        if(cliente.getId() != null){
            cliente.setDhAlteracao(LocalDateTime.now());
        }
        
        clienteRepository.save(cliente);
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Dados cadastrados com sucesso");
        return  new ModelAndView("redirect:/OpenBeer/Home");
    }
}
