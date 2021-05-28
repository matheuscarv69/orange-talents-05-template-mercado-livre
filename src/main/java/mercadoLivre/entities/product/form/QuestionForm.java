package mercadoLivre.entities.product.form;

import com.fasterxml.jackson.annotation.JsonCreator;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.product.entities.Question;
import mercadoLivre.entities.user.entities.User;

import javax.validation.constraints.NotBlank;

public class QuestionForm {

    @NotBlank
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public QuestionForm(String titulo) {
        this.titulo = titulo;
    }

    public Question toModel(User userLogged, Product product){
        return new Question(this.titulo,
                userLogged,
                product);
    }


}
