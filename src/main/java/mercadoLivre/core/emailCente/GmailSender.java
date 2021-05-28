package mercadoLivre.core.emailCente;

import mercadoLivre.entities.product.entities.Question;
import org.springframework.stereotype.Component;

@Component
public class GmailSender implements EmailCenter {

    @Override
    public void send(Question question) {
        System.out.println("Email com a pergunta enviado para o vendedor");
    }


}
