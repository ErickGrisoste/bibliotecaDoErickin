package com.example.demo.service;

import com.example.demo.model.Livro;
import com.example.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    public Livro atualizar(Long id, Livro novoLivro) {
        Livro livro = buscarPorId(id);
        livro.setTitulo(novoLivro.getTitulo());
        livro.setAutor(novoLivro.getAutor());
        livro.setGenero(novoLivro.getGenero());
        livro.setAnoPublicacao(novoLivro.getAnoPublicacao());
        livro.setEmprestado(false);
        return repository.save(livro);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
