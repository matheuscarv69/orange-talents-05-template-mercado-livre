package mercadoLivre.configs.security;

import mercadoLivre.entities.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    EntityManager manager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = manager.createQuery("select user from User user where user.email = :username");
        query.setParameter("username", username);
        User user1 = (User) query.getSingleResult();

        if (user1 != null) {
            return user1;
        }

        throw new UsernameNotFoundException("Dados de login inválidos, corrija e tente novamente.");
    }

}
