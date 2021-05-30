package mercadoLivre.entities.buyProduct.entities;

import mercadoLivre.core.gateway.Gateway;
import mercadoLivre.entities.finishBuyProduct.entities.Transaction;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class BuyProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull
    @Column(nullable = false)
    private Integer quantidade;

    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gateway gateway;

    @NotNull
    @ManyToOne
    private Product produto;

    @NotNull
    @ManyToOne
    private User cliente;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusBuy statusBuy = StatusBuy.iniciada;

    @NotNull
    @Column(nullable = false)
    private UUID uuidForGateway = UUID.randomUUID();

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transaction> transacoes = new HashSet<>();

    public BuyProduct(Integer quantidade, Gateway gateway, Product produto, User cliente) {
        this.quantidade = quantidade;
        this.preco = produto.getPreco();
        this.gateway = gateway;
        this.produto = produto;
        this.cliente = cliente;
    }

    // only hibernate
    @Deprecated
    public BuyProduct() {
    }

    public Long getId() {
        return id;
    }

    public String getEmailCliente() {
        return cliente.getUsername();
    }

    public String getEmailVendedor() {
        return produto.getUsuario().getUsername();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Long getIdProduto() {
        return produto.getId();
    }

    public String getNomeProduto() {
        return produto.getNome();
    }

    public Gateway getGateway() {
        return gateway;
    }

    public UUID getUuidForGateway() {
        return uuidForGateway;
    }

    public void adicionaTransacao(Transaction transacao) {
        Assert.isTrue(!this.transacoes.contains(transacao), "Já existe uma transação igual a essa processada!");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida com sucesso");

        this.transacoes.add(transacao);
    }

    private Set<Transaction> transacoesConcluidasComSucesso() {
        Set<Transaction> transacoesConcluidas = new HashSet<>();

        this.transacoes.forEach(transaction -> {
                    boolean concluida = transaction.concluidaComSucesso();
                    if (concluida) {
                        transacoesConcluidas.add(transaction);
                    }
                }
        );

        Assert.isTrue(transacoesConcluidas.size() <= 1, "Tem mais de uma transação concluída com a compra: " + this.id);

        return transacoesConcluidas;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }


}
