package mercadoLivre.entities.product.controller;

import io.jsonwebtoken.lang.Assert;
import mercadoLivre.core.uploadFile.UploaderInterface;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.product.form.NewImagesProductForm;
import mercadoLivre.entities.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/api/products")
public class AddImageController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private UploaderInterface uploaderFile;

    @PostMapping("/{id}/images")
    @Transactional
    public ResponseEntity<?> adicionaImages(@PathVariable Long id,
                                            @Valid NewImagesProductForm newImagesProductForm,
                                            @AuthenticationPrincipal User userLogged) {
        Product produto = manager.find(Product.class, id);
        Assert.notNull(produto, "Produto está nulo");

        if (!produto.isProductOwner(userLogged)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não é dono do Produto");
        }

        Set<String> linksImages = uploaderFile.uploadImages(newImagesProductForm.getImages());
        produto.addImages(linksImages);

        manager.merge(produto);

        return ResponseEntity.ok().build();
    }

}
