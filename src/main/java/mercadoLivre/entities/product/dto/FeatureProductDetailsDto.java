package mercadoLivre.entities.product.dto;

import mercadoLivre.entities.product.entities.FeatureProduct;

public class FeatureProductDetailsDto {

    private String nome;
    private String descricao;

    public FeatureProductDetailsDto(FeatureProduct featureProduct) {
        this.nome = featureProduct.getNome();
        this.descricao = featureProduct.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
