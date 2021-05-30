package mercadoLivre.entities.buyProduct.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.finishBuyProduct.dto.BuyProductDto;

import java.time.LocalDateTime;

public class NotificationFailedBuyClient {

    private String emailRemetente;
    private String emailDestinatario;
    private String assunto;
    private String conteudo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadoEm;

    public NotificationFailedBuyClient(BuyProductDto buyProductDto) {
        this.emailRemetente = buyProductDto.getVendedor();
        this.emailDestinatario = buyProductDto.getComprador();
        this.assunto = "Falha na compra do Produto: " + buyProductDto.getProduto();
        this.conteudo = "Link do produto: http://localhost:8080/api/products/" + buyProductDto.getIdProduto() + "/details";
        this.criadoEm = LocalDateTime.now();
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    @Override
    public String toString() {
        return "NotificationFailedBuyClient{" +
                "emailRemetente='" + emailRemetente + '\'' +
                ", emailDestinatario='" + emailDestinatario + '\'' +
                ", assunto='" + assunto + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", criadoEm=" + criadoEm +
                '}';
    }
}
