package br.com.sboot.cotacao.camel;

import br.com.sboot.cotacao.client.CotacaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.camel.builder.RouteBuilder;

@Component
public class CotacaoRoute extends RouteBuilder {

    public static final String ROUTE_ATUALIZAR = "direct: atualizar";

    @Autowired
    private CotacaoClient client;

    @Override
    public void configure() throws Exception {
        from(ROUTE_ATUALIZAR)
                .bean(client ,"consultarApiCotacao")
                .log("${body}")
                .end();
    }
}
