package br.com.senai.duvidas;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;

@RestController 
@RequestMapping("/duvida")
@CrossOrigin(origins ="http://localhost:8081") //CORS
public class DuvidaController {
    //Injeção de Dependência
    @Autowired
    private DuvidaRepository repository;   
    
    @GetMapping
    public List<Duvida> listar() {
        return repository.findAll();
    } 
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroDuvida dados){
        repository.save(new Duvida(dados));
    }

    // metodo cadastrar
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Dúvida não encontrada"
            );
        }

        repository.deleteById(id);
    }

    // metodo que permite que o professor responda uma pergunta existente 
    
    @PutMapping("/{id}/resposta")
    @Transactional
    public void responder(
            @PathVariable Long id,
            @RequestBody DadosRespostaDuvida dados) {

        Duvida duvida = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Dúvida não encontrada"
            ));

        duvida.setResposta(dados.resposta());
        repository.save(duvida);
    }

}

