package mercadoLivre.core.gateway;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public enum Gateway {

    paypal {
        @Override
        public String efetuaPagamento(UUID uuidCompra, UriComponentsBuilder uriBuilder) {
            String url = uriBuilder.path("/api/buyProducts/{uuidCompra}").buildAndExpand(uuidCompra).toUriString();

            return "paypal.com?buyerId=" + uuidCompra.toString() + "&redirectUrl=" + url;
        }
    },
    pagseguro {
        @Override
        public String efetuaPagamento(UUID uuidCompra, UriComponentsBuilder uriBuilder) {
            String url = uriBuilder.path("/api/buyProducts/{uuidCompra}").buildAndExpand(uuidCompra).toUriString();

            return "pagseguro.co?returnId=" + uuidCompra.toString() + "&redirectUrl=" + url;
        }
    };


    public abstract String efetuaPagamento(UUID uuidCompra, UriComponentsBuilder uriBuilder);
}
