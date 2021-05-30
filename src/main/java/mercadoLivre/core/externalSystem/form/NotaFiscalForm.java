package mercadoLivre.core.externalSystem.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotaFiscalForm {

    @NotNull
    private Long compraId;

    @NotBlank
    private String cliente;

    public NotaFiscalForm(Long compraId, String cliente) {
        this.compraId = compraId;
        this.cliente = cliente;
    }

    public Long getCompraId() {
        return compraId;
    }

    public String getCliente() {
        return cliente;
    }
}
