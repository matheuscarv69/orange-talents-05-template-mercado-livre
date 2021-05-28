package mercadoLivre.entities.product.dto;

import mercadoLivre.entities.product.entities.Opnion;

public class OpnionDetailsDto {

    private String titulo;
    private String descricao;
    private Integer nota;

    public OpnionDetailsDto(Opnion opnion) {
        this.titulo = opnion.getTitulo();
        this.descricao = opnion.getDescricao();
        this.nota = opnion.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }
}
