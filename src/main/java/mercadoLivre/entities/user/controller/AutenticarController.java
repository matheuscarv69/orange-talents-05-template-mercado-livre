package mercadoLivre.entities.user.controller;

import mercadoLivre.configs.security.TokenService;
import mercadoLivre.entities.user.auth.AuthTokenDto;
import mercadoLivre.entities.user.auth.AuthForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AutenticarController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<AuthTokenDto> autenticar(@RequestBody @Valid AuthForm authForm) {
        UsernamePasswordAuthenticationToken loginData = authForm.toModelUserNamePassword();

        try {
            Authentication auth = authManager.authenticate(loginData);

            String token = tokenService.generateToken(auth);

            return ResponseEntity.ok().body(new AuthTokenDto(token, "Bearer"));

        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }


    }

}
