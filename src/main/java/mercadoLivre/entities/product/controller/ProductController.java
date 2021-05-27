package mercadoLivre.entities.product.controller;

import mercadoLivre.core.uploadFile.Uploader;
import mercadoLivre.entities.product.form.NewImagesProductForm;
import mercadoLivre.entities.product.form.ProductForm;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.user.entities.User;
import mercadoLivre.entities.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private EntityManager manager;

    @Autowired
    private Uploader uploaderFile;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProductForm productForm, @AuthenticationPrincipal User userLogged) {
        Product produto = productForm.toModel(manager, userLogged);

        productRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<?> adicionaImagens(@PathVariable Long id, @Valid NewImagesProductForm newImagesProductForm, @AuthenticationPrincipal User userLogged) {
        Product product = productRepository.findById(id).get();

        if (!product.isProductOwner(userLogged)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não é dono do Produto");
        }

        Set<String> linksImages = uploaderFile.uploadImages(newImagesProductForm.getImages());
        product.addImages(linksImages);

        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

}
