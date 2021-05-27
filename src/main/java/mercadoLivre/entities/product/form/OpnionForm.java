package mercadoLivre.entities.product.form;

import mercadoLivre.entities.product.entities.Opnion;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpnionForm {

    @NotBlank
    private String titulo;

    @NotBlank
    @Length(max = 500)
    private String descricao;

    @Min(1)
    @Max(5)
    @NotNull
    private Integer nota;

    public OpnionForm(String titulo, String descricao, Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public Opnion toModel(Product produto, User usuario) {
        return new Opnion(this.titulo, this.descricao, this.nota, usuario, produto);
    }

    @Override
    public String toString() {
        return "OpnionForm{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", nota=" + nota +
                '}';
    }
}
