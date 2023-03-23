package apirest.tutorialapirest.controller;

import apirest.tutorialapirest.model.UsuarioModel;
import apirest.tutorialapirest.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping(path = "/api/usuario/{codigo}")
    public ResponseEntity consultar(@PathVariable("codigo") Integer codigo){
        return repository.findById(codigo) //busca o ID da consulta ou do repositório
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());

        // Chamo o Map para dizer se houve algum retorno da busca,
        // deve retornar um OK e no body retornar o resultado da busca,
        // Chamo o orElse para dizer se não houve algum retorno da busca,
        // deve retornar um notFound e realizar o build em seguida.
    }

    @PostMapping(path = "/api/usuario/salvar")
    public UsuarioModel salvar(@RequestBody UsuarioModel usuario){
        return repository.save(usuario);
    }
}
