package mercadoLivre.controllers;

import mercadoLivre.core.ProdUploaderFile;
import mercadoLivre.core.Uploader;
import mercadoLivre.core.UploaderFile;
import mercadoLivre.controllers.form.NewImagesProductForm;
import mercadoLivre.controllers.form.ProductForm;
import mercadoLivre.entities.Product;
import mercadoLivre.entities.User;
import mercadoLivre.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment env;

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
