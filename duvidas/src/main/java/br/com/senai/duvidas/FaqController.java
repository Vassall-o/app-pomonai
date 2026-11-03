// Esse Controller agora terá quatro funcionalidades: listar dúvidas, responder, excluir e mostrar o dashboard.
package br.com.senai.duvidas;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Controller
public class FaqController {

    private final DuvidaRepository repository;

    public FaqController(DuvidaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("duvidas", repository.findAll());
        return "faq";
    }

    @PostMapping("/faq/responder")
    public String responder(
            @RequestParam Long id,
            @RequestParam String resposta) {

        Duvida duvida = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Dúvida não encontrada"
            ));

        duvida.setResposta(resposta);
        repository.save(duvida);

        return "redirect:/faq";
    }

    @PostMapping("/faq/excluir")
    public String excluir(@RequestParam Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }

        return "redirect:/faq";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("total", repository.count());
        model.addAttribute(
            "ultimas",
            repository.findTop5ByOrderByIdDesc()
        );
        return "dashboard";
    }
}
