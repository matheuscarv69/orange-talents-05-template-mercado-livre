package mercadoLivre.core.externalSystem.controllers;

import mercadoLivre.core.externalSystem.form.NotaFiscalForm;
import mercadoLivre.core.externalSystem.form.RankingForm;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/external")
public class ExternalSystemControllers {

    @PostMapping("/notas-fiscais")
    public void criaNotaFiscal(@RequestBody @Valid NotaFiscalForm notaFiscalForm) {
        System.out.println("Criando nota fiscal para a compra: id " + notaFiscalForm.getCompraId() + " do comprador " + notaFiscalForm.getCliente());
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid RankingForm rankingForm) {
        System.out.println("Criando nota no ranking para a compra: id " + rankingForm.getCompraId() + " do vendedor " + rankingForm.getVendedor());
    }

}
