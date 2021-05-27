package mercadoLivre.entities.user.controller;

import mercadoLivre.entities.user.form.UserForm;
import mercadoLivre.entities.user.entities.User;
import mercadoLivre.entities.user.repository.UserRepository;
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
