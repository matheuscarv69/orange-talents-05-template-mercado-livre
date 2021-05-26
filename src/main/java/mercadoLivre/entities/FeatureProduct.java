package mercadoLivre.entities;

import javax.persistence.*;

@Entity
public class FeatureProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    private Product produto;

//    public FeatureProduct(String nome,
//                          String descricao){
//        this.nome = nome;
//        this.descricao = descricao;
//    }


    public FeatureProduct(String nome, String descricao, Product produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

    // only hibernate
    @Deprecated
    public FeatureProduct() {
    }
}
