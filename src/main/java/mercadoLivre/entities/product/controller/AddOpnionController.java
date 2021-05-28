package mercadoLivre.entities.product.controller;

import mercadoLivre.entities.product.entities.Opnion;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.product.form.OpnionForm;
import mercadoLivre.entities.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class AddOpnionController {

    @Autowired
    private EntityManager manager;

    @PostMapping("/{id}/opnions")
    @Transactional
    public ResponseEntity<?> adicionaOpiniao(@PathVariable Long id,
                                            @RequestBody @Valid OpnionForm opnionForm,
                                            @AuthenticationPrincipal User userLogged) {

        Product produto = manager.find(Product.class, id);
        Opnion opniao = opnionForm.toModel(produto, userLogged);

        produto.addOpnion(opniao);

        manager.persist(produto);

        return ResponseEntity.ok().build();
    }


}
