package mercadoLivre.controllers;

import mercadoLivre.controllers.form.UsuarioForm;
import mercadoLivre.entities.Usuario;
import mercadoLivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm) {
        Usuario usuario = usuarioForm.converter();

        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
