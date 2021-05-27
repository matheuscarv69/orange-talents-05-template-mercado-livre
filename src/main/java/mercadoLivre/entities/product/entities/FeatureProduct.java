package mercadoLivre.entities.product.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class FeatureProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @ManyToOne
    private Product produto;

    public FeatureProduct(String nome,
                          String descricao,
                          Product produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    // only hibernate
    @Deprecated
    public FeatureProduct() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FeatureProduct)) return false;
        FeatureProduct that = (FeatureProduct) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
