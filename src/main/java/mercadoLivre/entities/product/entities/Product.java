package mercadoLivre.entities.product.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.category.entity.Category;
import mercadoLivre.entities.product.form.FeatureProductForm;
import mercadoLivre.entities.user.entities.User;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Positive
    @NotNull
    @Column(nullable = false)
    private BigDecimal preco;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private Integer quantidade;

    @NotNull
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<FeatureProduct> caracteristicas = new HashSet<>();

    @NotBlank
    @Column(length = 1000)
    private String descricao;

    @OneToOne
    private Category categoria;

    @ManyToOne
    private User usuario;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @NotNull
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<ImageProduct> images = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Opnion> opinioes = new HashSet<>();

    @NotNull
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Question> perguntas = new HashSet<>();

    public Product(String nome,
                   BigDecimal preco,
                   Integer quantidade,
                   Set<FeatureProductForm> caracteristicasForm,
                   String descricao,
                   Category categoria,
                   User usuario) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicasForm
                .stream()
                .map(featureProductForm -> featureProductForm.toModel(this))
                .collect(Collectors.toSet());
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    // only hibernate
    @Deprecated
    public Product() {
    }

    public boolean isProductOwner(User usuario) {
        return this.usuario.equals(usuario);
    }

    public void addImages(Set<String> imagesLinks) {
        Set<ImageProduct> imagesProduct = imagesLinks
                .stream()
                .map(link ->
                        new ImageProduct(link, this)
                ).collect(Collectors.toSet());

        this.images.addAll(imagesProduct);
    }

    public void addOpnion(Opnion opnion) {
        opinioes.add(opnion);
    }

    public void addQuestion(Question question) {
        perguntas.add(question);
    }

    public boolean abateEstoque(Integer quantidadeDesejada) {

        if (this.quantidade >= quantidadeDesejada) {
            this.quantidade -= quantidadeDesejada;
            return true;
        }

        Assert.isTrue(quantidadeDesejada > this.quantidade, "A quantidade desejada é superior ao estoque.");

        return false;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<FeatureProduct> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCategoria() {
        return categoria.getNome();
    }

    public String getEmailUsuario() {
        return usuario.getUsername();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Set<ImageProduct> getImages() {
        return images;
    }

    public Set<Opnion> getOpinioes() {
        return opinioes;
    }

    public Set<Question> getPerguntas() {
        return perguntas;
    }

    public Integer getQuantidadeOpnions() {
        return opinioes.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equals(product.id) && nome.equals(product.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}
