package com.teste.primeiroexemplo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.teste.primeiroexemplo.model.Produto;

@Repository
public class ProdutoRepository {
    
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;



    /**
     * Método para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos() {
        return produtos;
    }


    /**
     * Método para retornar um produto pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos
            .stream()
            .filter(produto -> produto.getId() == id)
            .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista
     * @param produto que será adicionado na lista
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produtos.add(ultimoId, produto);
        return produto;
    }


    /**
     * Método para deletar o produto por id.
     * @param id id do produto que será deletado
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método paraatualizar o produto na lista.
     * @param produto que será atualizado.
     * @return Retorna o produto após atualizar a lista.
     */
    public Produto atualizar(Produto produto) {
        // Encontrar o produto que deseja atualizar
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if(produtoEncontrado.isEmpty()) {
            throw new InputMismatchException("Produto não encontrado!");
        }

        //Eu tenho que deletare o produto antigo da lista
        deletar(produto.getId());


        // Depois adicionar o novo produto
        produtos.add(produto);

        return produto;
    }
}
