
package br.com.senai.duvidas;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DuvidaRepository
        extends JpaRepository<Duvida, Long> {

    List<Duvida> findTop5ByOrderByIdDesc();
}
