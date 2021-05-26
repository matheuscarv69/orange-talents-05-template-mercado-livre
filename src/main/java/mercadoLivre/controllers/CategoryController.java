package mercadoLivre.controllers;

import mercadoLivre.controllers.form.CategoryForm;
import mercadoLivre.entities.Category;
import mercadoLivre.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid CategoryForm categoryForm) {
        Category categoria = categoryForm.toModel(categoryRepository);

        categoryRepository.save(categoria);

        return ResponseEntity.ok().build();
    }

}
