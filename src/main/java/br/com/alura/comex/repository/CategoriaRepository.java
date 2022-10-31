package br.com.alura.comex.repository;

import br.com.alura.comex.dto.CategoriaPedidoProjecao;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = """
            SELECT cat.nome, SUM(ip.quantidade) AS quantidade, SUM(p.preco_unitario*ip.quantidade) AS montante
            FROM comex.categorias cat
            JOIN comex.produtos p ON cat.id = p.categoria_id
            JOIN comex.itens_pedido ip ON p.id = ip.produto_id
            GROUP BY cat.nome
            """,
            nativeQuery = true)
    List<CategoriaPedidoProjecao> listByCategoriaPedido();
}
