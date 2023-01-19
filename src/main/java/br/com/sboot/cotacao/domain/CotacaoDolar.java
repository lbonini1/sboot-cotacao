package br.com.sboot.cotacao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CotacaoDolar {

    @JsonProperty("USDBRL")
    private ValoresCotacao valoresCotacao;

}
