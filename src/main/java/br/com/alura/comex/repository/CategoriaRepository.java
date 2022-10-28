package br.com.alura.comex.repository;

import br.com.alura.comex.dto.CategoriaPedidoProjecao;
import br.com.alura.comex.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query(value = "SELECT cat.nome, SUM(ip.quantidade) as quantidade, SUM(p.preco_unitario*ip.quantidade) as montante " +
            "from comex.categorias cat " +
            "join comex.produtos p on cat.id = p.categoria_id " +
            "join comex.itens_pedido ip on p.id = ip.produto_id " +
            "group by cat.nome",
            nativeQuery = true)
    List<CategoriaPedidoProjecao> listByCategoriaPedido();
}
