package br.com.sboot.cotacao.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValoresCotacao {

    @JsonProperty("code")
    private String moedaBase;

    @JsonProperty("codein")
    private String moedaDestino;

    @JsonProperty("name")
    private String descricaoCotacao;

    @JsonProperty("high")
    private BigDecimal vrAlta;

    @JsonProperty("low")
    private BigDecimal vrBaixa;

    @JsonProperty("varBid")
    private BigDecimal vrBid;

    @JsonProperty("pctChange")
    private BigDecimal vrPctChange;

    @JsonProperty("bid")
    private BigDecimal vrCotacaoCompra;

    @JsonProperty("ask")
    private BigDecimal vrCotacaoVenda;

    @JsonProperty("timestamp")
    private Integer timestamp;

    @JsonProperty("create_date")
    private String dtCotacao;

}
