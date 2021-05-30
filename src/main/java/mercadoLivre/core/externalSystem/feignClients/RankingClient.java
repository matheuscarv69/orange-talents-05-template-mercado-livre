package mercadoLivre.core.externalSystem.feignClients;

import mercadoLivre.core.externalSystem.form.RankingForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@FeignClient(url = "localhost:8080/external/ranking", name = "Ranking")
public interface RankingClient {

    @PostMapping
    void criarNoRanking(@Valid RankingForm rankingForm);

}
