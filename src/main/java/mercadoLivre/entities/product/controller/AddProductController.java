package mercadoLivre.entities.product.controller;

import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.product.form.ProductForm;
import mercadoLivre.entities.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class AddProductController {

    @Autowired
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProductForm productForm, @AuthenticationPrincipal User userLogged) {
        Product produto = productForm.toModel(manager, userLogged);

        manager.persist(produto);

        return ResponseEntity.ok().build();
    }

}
