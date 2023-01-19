package br.com.sboot.cotacao.client;

import br.com.sboot.cotacao.domain.CotacaoDolar;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class CotacaoClient {

    private final RestTemplate restTemplate;

    @Value("${app.properties.api.cotacao}")
    private String api;

    public CotacaoDolar consultarApiCotacao() {
      return restTemplate.getForObject(api, CotacaoDolar.class);
    }

}
