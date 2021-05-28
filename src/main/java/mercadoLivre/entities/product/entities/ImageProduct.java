package mercadoLivre.entities.product.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String link;

    @NotNull
    @ManyToOne
    private Product produto;

    public ImageProduct(String link,
                        Product produto) {
        this.link = link;
        this.produto = produto;
    }

    // only hibernate
    public ImageProduct() {
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageProduct)) return false;
        ImageProduct that = (ImageProduct) o;
        return link.equals(that.link) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, produto);
    }
}
