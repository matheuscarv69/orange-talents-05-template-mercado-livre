package mercadoLivre.entities.buyProduct.form;

import mercadoLivre.configs.validation.exists.ExistsId;
import mercadoLivre.entities.buyProduct.entities.BuyProduct;
import mercadoLivre.core.gateway.Gateway;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class BuyProductForm {

    @Positive
    @NotNull
    private Integer quantidade;

    @Positive
    @NotNull
    @ExistsId(domainClass = Product.class, message = "{field.validation.product.not-exists}")
    private Long produtoId;

    @NotNull
    private Gateway gateway;

    public BuyProductForm(Integer quantidade, Long produtoId, Gateway gateway) {
        this.quantidade = quantidade;
        this.produtoId = produtoId;
        this.gateway = gateway;
    }

    public BuyProduct toModel(Product produto, User cliente) {
        return new BuyProduct(this.quantidade, this.gateway, produto, cliente);
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
