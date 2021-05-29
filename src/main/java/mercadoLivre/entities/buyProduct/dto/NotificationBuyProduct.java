package mercadoLivre.entities.buyProduct.dto;

import mercadoLivre.entities.product.entities.Product;

public class NotificationBuyProduct {

    private String emailDono;
    private String produto;

    public NotificationBuyProduct(Product produto) {
        this.emailDono = produto.getEmailUsuario();
        this.produto = produto.getNome();
    }

    public String getEmailDono() {
        return emailDono;
    }

    public String getProduto() {
        return produto;
    }
}
