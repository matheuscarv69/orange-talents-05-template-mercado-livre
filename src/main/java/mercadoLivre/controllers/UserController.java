package mercadoLivre.controllers;

import mercadoLivre.controllers.form.UserForm;
import mercadoLivre.entities.User;
import mercadoLivre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UserForm userForm) {
        User usuario = userForm.toModel();

        userRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
