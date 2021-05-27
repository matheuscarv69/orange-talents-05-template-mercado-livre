package mercadoLivre.entities.user.controller;

import mercadoLivre.entities.user.entities.User;
import mercadoLivre.entities.user.form.UserForm;
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
@RequestMapping("/api/usuarios")
public class CadastrarUserController {

    @Autowired
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UserForm userForm) {
        User usuario = userForm.toModel();

        manager.persist(usuario);

        return ResponseEntity.ok().build();
    }
}
