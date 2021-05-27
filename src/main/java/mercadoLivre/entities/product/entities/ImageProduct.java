package mercadoLivre.entities.product.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @ManyToOne
    private Product produto;

    public ImageProduct(String link, Product produto) {
        this.link = link;
        this.produto = produto;
    }

    // only hibernate
    public ImageProduct() {
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
