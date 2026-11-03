// pag web que vamos mandar os dados 
package br.com.senai.duvidas;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model; //modelo do banco 

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class FaqController {
    private DuvidaRepository repository;

    public FaqController(DuvidaRepository repository){
        this.repository = repository;

    }
    // Enviar dados para o HTML
    @GetMapping("/faq")
    public String faq(Model model){
        model.addAttribute("duvidas", repository.findAll());
        return "faq";
    }
    @PostMapping("*/faq/excluir")
    public String excluir(@RequestParam Long id){
        repository.deleteById(id);
        return "redirect:/faq";
    }
    


}
