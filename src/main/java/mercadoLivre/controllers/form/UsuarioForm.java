package mercadoLivre.controllers.form;

import mercadoLivre.configs.security.SenhaLimpa;
import mercadoLivre.configs.validation.UniqueValue;
import mercadoLivre.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioForm {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "login", message = "{field.validation.email.duplicated}")
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }


}
