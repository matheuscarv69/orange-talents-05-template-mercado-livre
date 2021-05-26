package mercadoLivre.controllers;

import mercadoLivre.controllers.form.ProductForm;
import mercadoLivre.entities.Product;
import mercadoLivre.entities.User;
import mercadoLivre.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager manager;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProductForm productForm, @AuthenticationPrincipal User userLogged) {
        Product produto = productForm.toModel(manager, userLogged);

        productRepository.save(produto);

        return ResponseEntity.ok().build();
    }

}
