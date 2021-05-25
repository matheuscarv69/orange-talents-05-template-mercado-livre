package mercadoLivre.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.configs.security.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    // only hibernate
    @Deprecated
    public Usuario() {
    }

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = PasswordEncoder.SenhaLimpa(senha);
    }


}
