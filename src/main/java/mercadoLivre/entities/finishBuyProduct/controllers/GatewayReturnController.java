package mercadoLivre.entities.finishBuyProduct.controllers;

import io.jsonwebtoken.lang.Assert;
import mercadoLivre.entities.finishBuyProduct.eventsAfterBuy.EventsAfterBuy;
import mercadoLivre.core.gateway.GatewayReturnPayment;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.entities.finishBuyProduct.entities.Transaction;
import mercadoLivre.entities.finishBuyProduct.form.PagSeguroReturnForm;
import mercadoLivre.entities.finishBuyProduct.form.PayPalReturnForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController()
@RequestMapping("/api/compras")
public class GatewayReturnController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private EventsAfterBuy eventosPosCompra;

    @PostMapping("/retorno-pagseguro/{id}")
    @Transactional
    public ResponseEntity<?> processaPagSeguro(@PathVariable("id") @Positive Long compraId,
                                               @RequestBody @Valid PagSeguroReturnForm pagSeguroReturnForm) {

        return processa(compraId, pagSeguroReturnForm);
    }

    @PostMapping("/retorno-paypal/{id}")
    @Transactional
    public ResponseEntity<?> processaPayPal(@PathVariable("id") @Positive Long compraId,
                                            @RequestBody @Valid PayPalReturnForm payPalReturnForm) {

        return processa(compraId, payPalReturnForm);
    }


    private ResponseEntity<?> processa(Long compraId, GatewayReturnPayment gatewayReturnPayment) {
        BuyProduct compra = manager.find(BuyProduct.class, compraId);
        Assert.notNull(compra, "A compra está nula, verifique o id da compra");

        Transaction transacao = gatewayReturnPayment.toTransacao(compra);
        compra.adicionaTransacao(transacao);

        manager.merge(compra);
        eventosPosCompra.executa(compra);

        return ResponseEntity.ok().build();
    }


}
