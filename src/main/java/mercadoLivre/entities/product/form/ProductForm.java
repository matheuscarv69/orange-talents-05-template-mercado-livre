package mercadoLivre.entities.product.form;

import mercadoLivre.configs.validation.exists.ExistsId;
import mercadoLivre.entities.category.entity.Category;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ProductForm {

    @NotBlank
    private String nome;

    @Positive
    @NotNull
    private BigDecimal preco;

    @Positive
    @NotNull
    private Integer quantidade;

    @Valid
    @Size(min = 3, message = "{field.validadtion.quantity-features-products-min}")
    private Set<FeatureProductForm> caracteristicas = new HashSet<>();

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @Positive
    @ExistsId(domainClass = Category.class)
    private Long categoriaId;

    public ProductForm(String nome,
                       BigDecimal preco,
                       Integer quantidade,
                       Set<FeatureProductForm> caracteristicas,
                       String descricao,
                       Long categoriaId) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Product toModel(EntityManager manager, User userLogged) {
        Category categoria = manager.find(Category.class, categoriaId);

        Product product = new Product(this.nome,
                this.preco,
                this.quantidade,
                caracteristicas,
                this.descricao,
                categoria,
                userLogged
        );

        return product;
    }

}
