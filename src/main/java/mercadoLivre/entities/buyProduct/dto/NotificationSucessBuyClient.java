package mercadoLivre.entities.buyProduct.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.finishBuyProduct.dto.BuyProductDto;

import java.time.LocalDateTime;

public class NotificationSucessBuyClient {

    private String emailRemetente;
    private String emailDestinatario;
    private String assunto;
    private String conteudo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadoEm;

    private BuyProductDto buyProductDto;

    public NotificationSucessBuyClient(BuyProductDto buyProductDto) {
        this.emailRemetente = buyProductDto.getVendedor();
        this.emailDestinatario = buyProductDto.getComprador();
        this.assunto = "Compra do produto: " + buyProductDto.getProduto() + "realizada com sucesso: ";
        this.conteudo = "Segue em anexo mais detalhes da sua compra: ";
        this.criadoEm = LocalDateTime.now();
        this.buyProductDto = buyProductDto;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    @Override
    public String toString() {
        return "NotificationSucessBuyClient{" +
                "emailRemetente='" + emailRemetente + '\'' +
                ", emailDestinatario='" + emailDestinatario + '\'' +
                ", assunto='" + assunto + '\'' +
                ", conteudo='" + conteudo + '\'' +
                ", criadoEm=" + criadoEm +
                ", buyProductDto=" + buyProductDto +
                '}';
    }
}
