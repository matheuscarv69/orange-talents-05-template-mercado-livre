package mercadoLivre.entities.product.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.user.entities.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String titulo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private User cliente;

    @NotNull
    @ManyToOne
    private Product produto;

    public Question(String titulo,
                    User cliente,
                    Product produto) {
        this.titulo = titulo;
        this.cliente = cliente;
        this.produto = produto;
    }

    // only hibernate
    @Deprecated
    public Question() {
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public String getCliente() {
        return cliente.getUsername();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return titulo.equals(question.titulo) && cliente.equals(question.cliente) && produto.equals(question.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, cliente, produto);
    }
}
