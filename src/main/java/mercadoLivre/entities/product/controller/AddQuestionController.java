package mercadoLivre.entities.product.controller;

import io.jsonwebtoken.lang.Assert;
import mercadoLivre.core.emailCente.EmailCenter;
import mercadoLivre.core.emailCente.GmailSender;
import mercadoLivre.entities.product.entities.Product;
import mercadoLivre.entities.product.entities.Question;
import mercadoLivre.entities.product.form.QuestionForm;
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
public class AddQuestionController {

    @Autowired
    private EntityManager manager;

    @Autowired
    private GmailSender gmailSender;

    @PostMapping("/{id}/question")
    @Transactional
    public ResponseEntity<?> adicionaPergunta(@PathVariable Long id,
                                              @RequestBody @Valid QuestionForm questionForm,
                                              @AuthenticationPrincipal User userLogged) {
        Product product = manager.find(Product.class, id);

        Assert.notNull(product, "Produto está nulo");

        Question question = questionForm.toModel(userLogged, product);
        gmailSender.send(question);

        manager.persist(question);

        return ResponseEntity.ok().build();
    }


}
