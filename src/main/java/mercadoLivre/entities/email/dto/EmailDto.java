package mercadoLivre.entities.email.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EmailDto {

    private String titulo;

    private String User;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadoEm;

    private String produto;

    // Deveria receber uma Question e ir populando os dados com os getters
    public EmailDto(String titulo,
                    String user,
                    LocalDateTime criadoEm,
                    String produto) {
        this.titulo = titulo;
        User = user;
        this.criadoEm = criadoEm;
        this.produto = produto;
    }
}
