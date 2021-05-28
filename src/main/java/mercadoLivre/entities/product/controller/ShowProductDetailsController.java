package mercadoLivre.entities.product.controller;

import mercadoLivre.entities.product.dto.ProductDetailsDto;
import mercadoLivre.entities.product.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping("/api/products")
public class ShowProductDetailsController {

    @Autowired
    private EntityManager manager;

    @GetMapping("/{id}/details")
    public ResponseEntity<?> detalharProduto(@PathVariable Long id) {
        Product product = manager.find(Product.class, id);

        if (product == null) {
            return ResponseEntity.badRequest().body("Produto informado não existe no banco de dados");
        }

        ProductDetailsDto productDetailsDto = new ProductDetailsDto(product);

        return ResponseEntity.ok().body(productDetailsDto);
    }


}
