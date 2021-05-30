package mercadoLivre.entities.finishBuyProduct.dto;

import mercadoLivre.entities.buyProduct.entities.BuyProduct;

import java.math.BigDecimal;

public class BuyProductDto {

    private Integer quantidade;
    private BigDecimal precoProduto;
    private String formaDePagamento;
    private Long idProduto;
    private String produto;
    private String vendedor;
    private String comprador;

    public BuyProductDto(BuyProduct compra) {
        this.quantidade = compra.getQuantidade();
        this.precoProduto = compra.getPreco();
        this.formaDePagamento = compra.getGateway().toString();
        this.idProduto = compra.getIdProduto();
        this.produto = compra.getNomeProduto();
        this.vendedor = compra.getEmailVendedor();
        this.comprador = compra.getEmailCliente();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public String getVendedor() {
        return vendedor;
    }

    public String getComprador() {
        return comprador;
    }
}
