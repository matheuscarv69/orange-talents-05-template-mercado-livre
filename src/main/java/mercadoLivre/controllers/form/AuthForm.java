package mercadoLivre.controllers.form;

import mercadoLivre.configs.validation.exists.ExistsId;
import mercadoLivre.entities.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class AuthForm {

    @NotBlank
    @ExistsId(domainClass = Usuario.class, fieldName = "email", message = "{email.validation.login.not-exists}")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public AuthForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }


}
