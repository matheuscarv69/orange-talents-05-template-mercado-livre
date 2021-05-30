package mercadoLivre.entities.finishBuyProduct.entities;

public enum StatusPagSeguroReturn {

    SUCESSO,
    ERRO;

    public StatusTransaction padroniza() {
        if (this.equals(SUCESSO)) {
            return StatusTransaction.SUCESSO;
        }
        return StatusTransaction.FALHA;
    }
}

