package br.com.senai.duvidas;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;

public record DadosCadastroDuvida(String texto) {
@Generated (columnDefinition = "TEXT")
private String id;
    
@Column (columnDefinition = "TEXT")
private String texto;

@Column (columnDefinition = "TEXT")
private String resposta;




}
