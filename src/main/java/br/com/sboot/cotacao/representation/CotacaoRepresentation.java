package br.com.sboot.cotacao.representation;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class CotacaoRepresentation {

    private String tipoCotacao;

    private BigDecimal vrCotacao;

    private LocalDate dtCotacao;

}
