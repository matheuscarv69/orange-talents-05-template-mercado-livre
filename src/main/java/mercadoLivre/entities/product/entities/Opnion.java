package mercadoLivre.entities.product.entities;

import mercadoLivre.entities.user.entities.User;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Set;

@Entity
public class Opnion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @NotBlank
    @Column(nullable = false, length = 500)
    private String descricao;

    @Min(1)
    @Max(5)
    @NotNull
    @Column(nullable = false)
    private Integer nota;

    @NotNull
    @ManyToOne
    private User usuario;

    @NotNull
    @ManyToOne
    private Product produto;

    public Opnion(String titulo,
                  String descricao,
                  Integer nota,
                  User usuario,
                  Product produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.usuario = usuario;
        this.produto = produto;
    }

    // only hibernate
    @Deprecated
    public Opnion() {
    }

    public static Integer averageNota(Set<Opnion> opnions) {
        if(opnions.isEmpty()){
            return 0;
        }
        int totalNotas = 0;

        for (Opnion opnion : opnions) {
            totalNotas += opnion.getNota();
        }

        return totalNotas / opnions.size();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opnion)) return false;
        Opnion opnion = (Opnion) o;
        return titulo.equals(opnion.titulo) && descricao.equals(opnion.descricao) && nota.equals(opnion.nota) && usuario.equals(opnion.usuario) && produto.equals(opnion.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao, nota, usuario, produto);
    }
}
