package mercadoLivre.entities.product.repository;

import mercadoLivre.entities.product.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
