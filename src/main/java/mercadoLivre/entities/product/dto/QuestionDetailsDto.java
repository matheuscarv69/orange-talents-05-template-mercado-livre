package mercadoLivre.entities.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import mercadoLivre.entities.product.entities.Question;

import java.time.LocalDateTime;

public class QuestionDetailsDto {

    private String titulo;

    private String cliente;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime criadoEm;

    public QuestionDetailsDto(Question question) {
        this.titulo = question.getTitulo();
        this.cliente = question.getCliente();
        this.criadoEm = question.getCriadoEm();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}
