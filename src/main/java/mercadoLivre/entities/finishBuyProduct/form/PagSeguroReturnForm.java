package mercadoLivre.entities.finishBuyProduct.form;

import mercadoLivre.configs.validation.uniqueValue.UniqueValue;
import mercadoLivre.core.gateway.GatewayReturnPayment;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.entities.finishBuyProduct.entities.StatusPagSeguroReturn;
import mercadoLivre.entities.finishBuyProduct.entities.Transaction;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagSeguroReturnForm implements GatewayReturnPayment {

    @NotBlank
    @UniqueValue(domainClass = Transaction.class, fieldName = "transactionGatewayId")
    private String transacaoId;

    @NotNull
    private StatusPagSeguroReturn status;

    public PagSeguroReturnForm(String transacaoId,
                               StatusPagSeguroReturn status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public Transaction toTransacao(BuyProduct compra) {
        return new Transaction(this.transacaoId, status.padroniza(), compra);
    }

    @Override
    public String toString() {
        return "PagSeguroReturnForm{" +
                "transacaoId='" + transacaoId + '\'' +
                ", status=" + status +
                '}';
    }
}
