package mercadoLivre.entities.category.repository;

import mercadoLivre.entities.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
