package mercadoLivre.controllers.form;

import mercadoLivre.entities.FeatureProduct;
import mercadoLivre.entities.Product;

import javax.validation.constraints.NotBlank;

public class FeatureProductForm {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public FeatureProductForm(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public FeatureProduct toModel(Product product) {
        return new FeatureProduct(this.nome, this.descricao, product);
    }
}
