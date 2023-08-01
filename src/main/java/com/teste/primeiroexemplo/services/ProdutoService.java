package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.ProdutoRepository;
import com.teste.primeiroexemplo.repository.ProdutoRepository_old;
import com.teste.primeiroexemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

     /**
     * Método para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<ProdutoDTO> obterTodos() {
       // return produtoRepository.obterTodos();
       // Retorna uma lista de produto model.
       List<Produto> produtos = produtoRepository.findAll();

       return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect((Collectors.toList()));
    }

    /**
     * Método para retornar um produto pelo seu id.
     * @param id do produto que será localizado.
     * @return Retorna um produto caso seja encontrado.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {

       // return produtoRepository.obterPorId(id);
        // return produtoRepository.findById(id);
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        return Optional.of(dto);

    }

    /**
     * Metodo para adicionar produto na lista
     * @param produto que será adicionado na lista
     * @return Retorna o produto que foi adicionado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
        
        // return produtoRepository.adicionar(produto);
        produtoDTO.setId(null);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDTO, Produto.class);

        produto = produtoRepository.save(produto);

        produtoDTO.setId((produto.getId()));

        return produtoDTO;
    }

    /**
     * Método para deletar o produto por id.
     * @param id id do produto que será deletado
     */
    public void deletar(Integer id) {
        // Verificar se o produto existe

        Optional<Produto> produto = produtoRepository.findById((id));

        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o id: "+ id +" - Produto id");
        }

        produtoRepository.deleteById(id);
        // produtoRepository.deletar(id);
    }


    /**
     * Método paraatualizar o produto na lista.
     * @param produto que será atualizado.
     * @param id do produto.
     * @return Retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
       
        // Passar o id para o produtoDto
        produtoDto.setId(id);
        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // converter o ProdutoDTO em Produto

        Produto produto = mapper.map(produtoDto, Produto.class);


        // Atualizar o produto no banco de dados.
        produtoRepository.save(produto);



        // Retorna o produto atualizado 
        return produtoDto;

        // return produtoRepository.atualizar(produto);
    }
}
