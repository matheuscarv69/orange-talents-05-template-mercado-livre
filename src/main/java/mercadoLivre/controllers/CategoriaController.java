package mercadoLivre.controllers;

import mercadoLivre.controllers.form.CategoriaForm;
import mercadoLivre.entities.Categoria;
import mercadoLivre.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<?> cadastra(@RequestBody @Valid CategoriaForm categoriaForm) {
        Categoria categoria = categoriaForm.converter(categoriaRepository);

        categoriaRepository.save(categoria);

        return ResponseEntity.ok().build();
    }

}
