package mercadoLivre.entities.finishBuyProduct.form;

import mercadoLivre.configs.validation.uniqueValue.UniqueValue;
import mercadoLivre.core.gateway.GatewayReturnPayment;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.entities.finishBuyProduct.entities.StatusTransaction;
import mercadoLivre.entities.finishBuyProduct.entities.Transaction;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PayPalReturnForm implements GatewayReturnPayment {

    @NotBlank
    @UniqueValue(domainClass = Transaction.class, fieldName = "transactionGatewayId")
    private String transacaoId;

    @Min(0)
    @Max(1)
    private Integer status;

    public PayPalReturnForm(String transacaoId, Integer status) {
        this.transacaoId = transacaoId;
        this.status = status;
    }

    public Transaction toTransacao(BuyProduct compra) {
        StatusTransaction statusVerificado = this.status == 0
                ? StatusTransaction.FALHA : StatusTransaction.SUCESSO;

        return new Transaction(this.transacaoId, statusVerificado, compra);
    }


}
