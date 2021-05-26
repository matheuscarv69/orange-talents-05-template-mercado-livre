package mercadoLivre.controllers.form;

import mercadoLivre.configs.validation.exists.ExistsId;
import mercadoLivre.entities.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class AuthForm {

    @NotBlank
    @ExistsId(domainClass = User.class, fieldName = "email", message = "{email.validation.login.not-exists}")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public AuthForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toModelUserNamePassword() {
        return new UsernamePasswordAuthenticationToken(this.email, this.senha);
    }


}
