package mercadoLivre.configs.security;

import mercadoLivre.entities.Usuario;
import mercadoLivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optUsuario = usuarioRepository.findByEmail(username);

        if(optUsuario.isPresent()){
            return optUsuario.get();
        }

        throw new UsernameNotFoundException("{data.validation.login.invalid}");
    }

}
