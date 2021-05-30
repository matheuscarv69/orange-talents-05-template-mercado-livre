package mercadoLivre.entities.buyProduct.controllers;

import mercadoLivre.core.emailCenter.EmailCenter;
import mercadoLivre.entities.buyProduct.dto.NotificationBuyProductSeller;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.core.gateway.Gateway;
import mercadoLivre.entities.buyProduct.form.BuyProductForm;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/buyProduct")
public class DoBuyProductController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private EmailCenter emailCenter;

    @PostMapping
    @Transactional
    public ResponseEntity<?> efetuaCompra(@RequestBody @Valid BuyProductForm buyProductForm,
                                          @AuthenticationPrincipal User cliente,
                                          UriComponentsBuilder uriBuilder) {

        Product produto = manager.find(Product.class, buyProductForm.getProdutoId());
        boolean abateuEstoque = produto.abateEstoque(buyProductForm.getQuantidade());

        if (abateuEstoque) {
            BuyProduct compra = buyProductForm.toModel(produto, cliente);

            Gateway gateway = compra.getGateway();
            String url = gateway.efetuaPagamento(compra.getUuidForGateway(), uriBuilder);

            // persist em produto para nao deixar implicito que ele tambem eh atualizado
            manager.persist(produto);
            manager.persist(compra);

            emailCenter.sendNotificationBuySeller(new NotificationBuyProductSeller(produto));

            return ResponseEntity.status(HttpStatus.FOUND).body(url);
        }

        return ResponseEntity.badRequest().body("Hummm, algo deu errado na compra do produto: quantidade disponível " + produto.getQuantidade());
    }

}
