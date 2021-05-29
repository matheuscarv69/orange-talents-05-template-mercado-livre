package mercadoLivre.entities.buyProduct.entities;

import mercadoLivre.core.gateway.Gateway;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
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

    public Gateway getGateway() {
        return gateway;
    }

    public UUID getUuidForGateway() {
        return uuidForGateway;
    }
}
