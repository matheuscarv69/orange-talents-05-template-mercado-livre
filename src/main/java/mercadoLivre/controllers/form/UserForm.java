package mercadoLivre.controllers.form;

import mercadoLivre.configs.security.CleanPassword;
import mercadoLivre.configs.validation.uniqueValue.UniqueValue;
import mercadoLivre.entities.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserForm {

    @NotBlank
    @Email
    @UniqueValue(domainClass = User.class, fieldName = "email", message = "{field.validation.email.duplicated}")
    private String email;

    @NotBlank
    @Length(min = 6)
    private String senha;

    public UserForm(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public User toModel() {
        return new User(this.email, new CleanPassword(this.senha));
    }


}
