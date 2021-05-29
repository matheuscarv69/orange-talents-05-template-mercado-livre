package mercadoLivre.core.emailCenter;

import mercadoLivre.entities.buyProduct.dto.NotificationBuyProduct;
import mercadoLivre.entities.product.entities.Question;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class GmailSender implements EmailCenter {

    @Override
    public void sendQuestion(Question question) {
        System.out.println("Email com a pergunta enviado para o vendedor");
    }

    @Override
    public void sendNotificationBuy(NotificationBuyProduct notification) {
        System.out.println("Enviado notificação de compra para email do vendedor: " + notification.getEmailDono());
        System.out.println("Produto: " + notification.getProduto());
    }


}
