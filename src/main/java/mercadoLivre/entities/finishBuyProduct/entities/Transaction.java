package mercadoLivre.entities.finishBuyProduct.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String transactionGatewayId;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusTransaction status;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadaEm;

    @NotNull
    @ManyToOne
    private BuyProduct compra;

    public Transaction(String transactionGatewayId,
                       StatusTransaction status,
                       BuyProduct compra) {
        this.status = status;
        this.transactionGatewayId = transactionGatewayId;
        this.criadaEm = LocalDateTime.now();
        this.compra = compra;
    }

    // only hibernate
    @Deprecated
    public Transaction() {
    }

    public boolean concluidaComSucesso() {
        return this.status.equals(StatusTransaction.SUCESSO);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return transactionGatewayId.equals(that.transactionGatewayId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionGatewayId);
    }
}
