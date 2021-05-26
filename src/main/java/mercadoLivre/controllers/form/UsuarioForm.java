package mercadoLivre.controllers.form;

import mercadoLivre.configs.security.SenhaLimpa;
import mercadoLivre.configs.validation.uniqueValue.UniqueValue;
import mercadoLivre.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {

    @NotBlank
    @Email
    @UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "{field.validation.email.duplicated}")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario converter() {
        return new Usuario(this.email, new SenhaLimpa(this.senha));
    }


}
