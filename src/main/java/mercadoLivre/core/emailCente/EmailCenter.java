package mercadoLivre.core.emailCente;

import mercadoLivre.entities.product.entities.Question;

public interface EmailCenter {

    void send(Question question);

}
