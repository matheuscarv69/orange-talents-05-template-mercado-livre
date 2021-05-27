package mercadoLivre.entities.category.form;

import mercadoLivre.configs.validation.exists.ExistsId;
import mercadoLivre.configs.validation.uniqueValue.UniqueValue;
import mercadoLivre.entities.category.entity.Category;
import mercadoLivre.entities.category.repository.CategoryRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoryForm {

    @NotBlank
    @UniqueValue(domainClass = Category.class, fieldName = "nome")
    private String nome;

    @Positive
    @ExistsId(domainClass = Category.class, message = "{field.validation.categoria.not-exists}")
    private Long idCategoriaMae;

    public CategoryForm(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Category toModel(CategoryRepository categoryRepository) {
        if (idCategoriaMae != null) {
            Category categoriaMae = categoryRepository.findById(idCategoriaMae).get();

            Category categoria = new Category(this.nome);
            categoria.setCategoriaMae(categoriaMae);

            return categoria;
        }

        Category categoria = new Category(this.nome);

        return categoria;
    }

}
