package mercadoLivre.core.gateway;

import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.entities.finishBuyProduct.entities.Transaction;

public interface GatewayReturnPayment {

    Transaction toTransacao(BuyProduct compra);
}
