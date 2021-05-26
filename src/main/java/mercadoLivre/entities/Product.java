package mercadoLivre.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.controllers.form.FeatureProductForm;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Positive
    @Column(nullable = false)
    private BigDecimal preco;

    @Positive
    @Column(nullable = false)
    private Integer quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<FeatureProduct> caracteristicas = new HashSet<>();

    @Length(max = 1000)
    private String descricao;

    @OneToOne
    private Category categoria;

    @ManyToOne
    private User usuario;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao = LocalDateTime.now();

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

}
