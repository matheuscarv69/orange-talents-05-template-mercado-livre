package mercadoLivre.entities.product.dto;

import mercadoLivre.entities.product.entities.ImageProduct;

public class ImagesProductDto {

    private String link;

    public ImagesProductDto(ImageProduct imageProduct) {
        this.link = imageProduct.getLink();
    }

    public String getLink() {
        return link;
    }
}
