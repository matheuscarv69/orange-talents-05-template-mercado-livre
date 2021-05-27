package mercadoLivre.entities.user.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CleanPassword {

    private String password;

    public CleanPassword(@NotBlank @Size(min = 6) String password) {
        this.password = password;
    }

    public String hash() {
        return new BCryptPasswordEncoder().encode(this.password);
    }

}
