package mercadoLivre.core.emailCenter;

import mercadoLivre.entities.buyProduct.dto.NotificationFailedBuyClient;
import mercadoLivre.entities.buyProduct.dto.NotificationSucessBuyClient;
import mercadoLivre.entities.buyProduct.dto.NotificationBuyProductSeller;
import mercadoLivre.entities.product.entities.Question;

public interface EmailCenter {

    void sendQuestion(Question question);

    void sendNotificationBuySeller(NotificationBuyProductSeller notification);

    void sendNotificationSuccessBuyClient(NotificationSucessBuyClient notification);

    void sendNotificationFailedBuyClient(NotificationFailedBuyClient notification);


}
