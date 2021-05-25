package mercadoLivre.configs.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static String SenhaLimpa(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }

}
