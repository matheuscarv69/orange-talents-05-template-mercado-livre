package mercadoLivre.entities.buyProduct.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.product.entities.Product;

import java.time.LocalDateTime;

public class NotificationBuyProductSeller {

    private String emailRemetente;
    private String emailDestinatario;
    private String assunto;
    private String conteudo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadoEm;

    public NotificationBuyProductSeller(Product produto) {
        this.emailRemetente = "mercadolivre@mail.com";
        this.emailDestinatario = produto.getEmailUsuario();
        this.assunto = "Uma nova compra foi feita para o seu produto: " + produto.getNome();
        this.conteudo = "Um cliente espera o envio do produto " + produto.getNome();
        this.criadoEm = LocalDateTime.now();
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    @Override
    public String toString() {
        return "NotificationBuyProductSeller{" +
                "emailRemetente='" + emailRemetente + '\'' +
                ", emailDestinatario='" + emailDestinatario + '\'' +
                ", assunto='" + assunto + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
