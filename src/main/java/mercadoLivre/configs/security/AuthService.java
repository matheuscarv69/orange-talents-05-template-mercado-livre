package mercadoLivre.configs.security;

import mercadoLivre.entities.User;
import mercadoLivre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUsuario = userRepository.findByEmail(username);

        if(optUsuario.isPresent()){
            return optUsuario.get();
        }

        throw new UsernameNotFoundException("Dados de login inválidos, corrija e tente novamente.");
    }

}
