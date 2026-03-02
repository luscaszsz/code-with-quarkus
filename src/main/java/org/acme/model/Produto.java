package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


/**
 * Example JPA entity defined as a Panache Entity.
 * An ID field of Long type is provided, if you want to define your own ID field extends <code>PanacheEntityBase</code> instead.
 *
 * This uses the active record pattern, you can also use the repository pattern instead:
 * {@see https://quarkus.io/guides/hibernate-orm-panache#solution-2-using-the-repository-pattern}.
 *
 * Usage:
 *
 * {@code
 *     public void doSomething() {
 *         MyEntity entity1 = new MyEntity();
 *         entity1.field = "field-1";
 *         entity1.persist();
 *
 *         List<MyEntity> entities = MyEntity.listAll();
 *     }
 * }
 */
@Entity
@Table(name = "produto")
@ApplicationScoped
@Getter
@Setter
public class Produto extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipoProduto;

    @Column(nullable = false)
    private BigDecimal rentabilidadeAnual;

    @Column(nullable = false)
    private String risco;

    @Column(nullable = false) //consideracao - poderia ser int, mas caso o banco de dados mude no futuro para aceitar valores nulos, o codigo nao precisa ser adaptado
    private Integer prazoMinMeses;

    @Column(nullable = false)
    private Integer prazoMaxMeses;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valorMin;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal valorMax;


    @Transactional
    public void criaValor(String valor){
        Produto.persist(valor);
    }

    public List<Produto> buscaNomesProdutos(){
        return Produto.listAll();
    }

    public List<Produto> findByTipoAndValorAndPrazo(String tipoProduto, BigDecimal valor, int prazo){
        return find("tipoProduto = ?1 " +
                "and ?2 between valorMin and valorMax " +
                "and ?3 between prazoMinMeses and prazoMaxMeses",
                tipoProduto, valor, prazo).list();
    }

}
