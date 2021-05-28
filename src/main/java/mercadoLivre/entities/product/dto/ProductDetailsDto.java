package mercadoLivre.entities.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.product.entities.Opnion;
import mercadoLivre.entities.product.entities.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductDetailsDto {

    private String nome;
    private BigDecimal preco;
    private Integer quantidade;
    private String categoria;
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao;
    private Integer mediaNotas;
    private Integer quantidadeNotas;

    private Set<FeatureProductDetailsDto> features = new HashSet<>();
    private Set<OpnionDetailsDto> opinioes = new HashSet<>();
    private Set<QuestionDetailsDto> perguntas = new HashSet<>();
    private Set<ImagesProductDto> images = new HashSet<>();

    public ProductDetailsDto(Product product) {
        this.nome = product.getNome();
        this.preco = product.getPreco();
        this.quantidade = product.getQuantidade();
        this.categoria = product.getCategoria();
        this.descricao = product.getDescricao();
        this.dataCriacao = product.getDataCriacao();
        this.mediaNotas = Opnion.averageNota(product.getOpinioes());
        this.quantidadeNotas = product.getQuantidadeOpnions();

        this.features = product.getCaracteristicas()
                .stream()
                .map(FeatureProductDetailsDto::new)
                .collect(Collectors.toSet());

        this.opinioes = product.getOpinioes()
                .stream()
                .map(OpnionDetailsDto::new)
                .collect(Collectors.toSet());

        this.perguntas = product.getPerguntas()
                .stream()
                .map(QuestionDetailsDto::new)
                .collect(Collectors.toSet());

        this.images = product.getImages()
                .stream()
                .map(ImagesProductDto::new)
                .collect(Collectors.toSet());
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

    public String getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Integer getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public Set<FeatureProductDetailsDto> getFeatures() {
        return features;
    }

    public Set<OpnionDetailsDto> getOpinioes() {
        return opinioes;
    }

    public Set<QuestionDetailsDto> getPerguntas() {
        return perguntas;
    }

    public Set<ImagesProductDto> getImages() {
        return images;
    }
}
