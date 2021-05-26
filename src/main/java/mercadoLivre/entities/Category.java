package mercadoLivre.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String nome;

    @ManyToOne
    private Category categoriaMae;

    public Category(String nome) {
        this.nome = nome;
    }

    // only hibernate
    @Deprecated
    public Category() {
    }

    public void setCategoriaMae(Category categoriaMae) {
        this.categoriaMae = categoriaMae;
    }
}
