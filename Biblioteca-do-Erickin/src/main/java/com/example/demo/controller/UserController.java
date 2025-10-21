package com.example.demo.controller;

import com.example.demo.model.Livro;
import com.example.demo.model.Usuario;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService service;


    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario user) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(user));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Long id, @RequestBody Usuario user) {
        return ResponseEntity.ok(service.atualizar(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idUser}/{idLivro}")
    public ResponseEntity<List<Livro>> alugarLivro(@PathVariable Long idUser, @PathVariable Long idLivro){
        return ResponseEntity.ok(service.alugarLivro(idLivro, idUser));
    }


}
