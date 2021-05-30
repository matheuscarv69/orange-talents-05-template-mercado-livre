package mercadoLivre.core.externalSystem.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RankingForm {

    @NotNull
    private Long compraId;

    @NotBlank
    private String vendedor;

    public RankingForm(Long compraId, String vendedor) {
        this.compraId = compraId;
        this.vendedor = vendedor;
    }

    public Long getCompraId() {
        return compraId;
    }

    public String getVendedor() {
        return vendedor;
    }
}
