package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

     /**
     * Método para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtoRepository.obterTodos();
    }

    /**
     * Método para retornar um produto pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {

        return produtoRepository.obterPorId(id);
    }

    /**
     * Metodo para adicionar produto na lista
     * @param produto que será adicionado na lista
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto) {
        
        return produtoRepository.adicionar(produto);
    }

    /**
     * Método para deletar o produto por id.
     * @param id id do produto que será deletado
     */
    public void deletar(Integer id) {
        produtoRepository.deletar(id);
    }


    /**
     * Método paraatualizar o produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Integer id, Produto produto) {
        produto.setId(id);

        return produtoRepository.atualizar(produto);
    }
}
