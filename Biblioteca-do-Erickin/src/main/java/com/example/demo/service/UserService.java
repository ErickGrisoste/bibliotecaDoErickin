package com.example.demo.service;

import com.example.demo.model.Livro;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private LivroService livroService;

    public Usuario salvar(Usuario user) {
        return repository.save(user);
    }

    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario novoUser) {
        Usuario user = buscarPorId(id);
        user.setNome(novoUser.getNome());
        user.setLivrosEmprestados(novoUser.getLivrosEmprestados());
        return repository.save(user);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }


    public List<Livro> alugarLivro(Long idLivro, Long idUser){
        Livro livro = livroService.buscarPorId(idLivro);
        Usuario user = buscarPorId(idUser);

        if (livro.isEmprestado()) {
            throw new RuntimeException("Livro indisponível");
        }

        livro.setUser(user);
        livro.setEmprestado(true);

        user.getLivrosEmprestados().add(livro);

        livroService.salvar(livro);
        repository.save(user);

        return user.getLivrosEmprestados();
    }


}
