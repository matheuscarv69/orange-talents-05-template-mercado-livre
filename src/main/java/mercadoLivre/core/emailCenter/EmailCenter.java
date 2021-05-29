package mercadoLivre.core.emailCenter;

import mercadoLivre.entities.buyProduct.dto.NotificationBuyProduct;
import mercadoLivre.entities.product.entities.Question;

public interface EmailCenter {

    void sendQuestion(Question question);

    void sendNotificationBuy(NotificationBuyProduct notification);
}
