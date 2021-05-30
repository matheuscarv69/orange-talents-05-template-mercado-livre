package mercadoLivre.entities.finishBuyProduct.eventsAfterBuy;

import mercadoLivre.entities.buyProduct.entities.BuyProduct;

public interface Events {

    void executa(BuyProduct compra);

}
