package mercadoLivre.controllers.form;

import mercadoLivre.configs.validation.exists.ExistsId;
import mercadoLivre.configs.validation.uniqueValue.UniqueValue;
import mercadoLivre.entities.Categoria;
import mercadoLivre.repositories.CategoriaRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaForm {

    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @Positive
    @ExistsId(domainClass = Categoria.class, message = "{field.validation.categoria.not-exists}")
    private Long idCategoriaMae;

    public CategoriaForm(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converter(CategoriaRepository categoriaRepository){
        if(idCategoriaMae != null){
            Categoria categoriaMae = categoriaRepository.findById(idCategoriaMae).get();

            Categoria categoria = new Categoria(this.nome);
            categoria.setCategoriaMae(categoriaMae);

            return categoria;
        }

        Categoria categoria = new Categoria(this.nome);

        return categoria;
    }

}
