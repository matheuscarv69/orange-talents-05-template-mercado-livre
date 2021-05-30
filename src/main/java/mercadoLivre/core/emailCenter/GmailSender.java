package mercadoLivre.core.emailCenter;

import mercadoLivre.entities.buyProduct.dto.NotificationFailedBuyClient;
import mercadoLivre.entities.buyProduct.dto.NotificationSucessBuyClient;
import mercadoLivre.entities.buyProduct.dto.NotificationBuyProductSeller;
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
    public void sendNotificationBuySeller(NotificationBuyProductSeller notification) {
        System.out.println("Enviada notificação de compra para email do vendedor: " + notification.getEmailDestinatario());
        System.out.println(notification.toString());
    }

    @Override
    public void sendNotificationSuccessBuyClient(NotificationSucessBuyClient notification) {
        System.out.println("Email de notificação de compra concluída com sucesso para o cliente: " + notification.getEmailDestinatario());
        System.out.println(notification.toString());
    }

    @Override
    public void sendNotificationFailedBuyClient(NotificationFailedBuyClient notification) {
        System.out.println("Email de notificação de compra não concluída para o cliente: " + notification.getEmailDestinatario());
        System.out.println(notification.toString());
    }
}
