package mercadoLivre.core.externalSystem.feignClients;

import mercadoLivre.core.externalSystem.form.NotaFiscalForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@FeignClient(url = "localhost:8080/external/notas-fiscais", name = "NotaFiscal")
public interface NotaFiscalClient {

    @PostMapping
    void criaNotaFiscal(@Valid NotaFiscalForm notaFiscalForm);

}
