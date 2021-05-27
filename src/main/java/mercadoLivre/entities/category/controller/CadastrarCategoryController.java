package mercadoLivre.entities.category.controller;

import mercadoLivre.entities.category.entity.Category;
import mercadoLivre.entities.category.form.CategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CadastrarCategoryController {

    @Autowired
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid CategoryForm categoryForm) {
        Category categoria = categoryForm.toModel(manager);

        manager.persist(categoria);

        return ResponseEntity.ok().build();
    }

}
