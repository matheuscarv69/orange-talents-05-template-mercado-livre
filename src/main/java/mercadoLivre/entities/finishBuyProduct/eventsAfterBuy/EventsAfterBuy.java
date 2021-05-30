package mercadoLivre.entities.finishBuyProduct.eventsAfterBuy;

import mercadoLivre.core.emailCenter.EmailCenter;
import mercadoLivre.core.externalSystem.feignClients.NotaFiscalClient;
import mercadoLivre.core.externalSystem.feignClients.RankingClient;
import mercadoLivre.core.externalSystem.form.NotaFiscalForm;
import mercadoLivre.core.externalSystem.form.RankingForm;
import mercadoLivre.entities.buyProduct.dto.NotificationFailedBuyClient;
import mercadoLivre.entities.buyProduct.dto.NotificationSucessBuyClient;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.entities.finishBuyProduct.dto.BuyProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class EventsAfterBuy implements Events{

    @Autowired
    private NotaFiscalClient notaFiscalClient;

    @Autowired
    private RankingClient rankingClient;

    @Autowired
    private EmailCenter emailCenter;

    public void executa(BuyProduct compra) {

        if (compra.processadaComSucesso()) {
            notaFiscalClient.criaNotaFiscal(new NotaFiscalForm(compra.getId(), compra.getEmailCliente()));
            rankingClient.criarNoRanking(new RankingForm(compra.getId(), compra.getEmailVendedor()));

            BuyProductDto buyProductDto = new BuyProductDto(compra);
            emailCenter.sendNotificationSuccessBuyClient(new NotificationSucessBuyClient(buyProductDto));

        } else {
            BuyProductDto buyProductDto = new BuyProductDto(compra);
            emailCenter.sendNotificationFailedBuyClient(new NotificationFailedBuyClient(buyProductDto));
        }

    }

}
