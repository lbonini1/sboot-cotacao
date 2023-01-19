package br.com.sboot.cotacao.mapper;

import br.com.sboot.cotacao.domain.CotacaoDolar;
import br.com.sboot.cotacao.entity.Cotacao;
import br.com.sboot.cotacao.redis.Cotacoes;
import br.com.sboot.cotacao.representation.CotacaoRepresentation;
import br.com.sboot.cotacao.utils.DataUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class CotacaoMapper {

    private static final String TIPO_COTACAO = "USD-BRL";

    public Cotacao toEntityCotacao(CotacaoDolar cotacaoDolar) {
        return Cotacao.builder()
                .dtCotacao(
                        DataUtils.transformarData(cotacaoDolar.getValoresCotacao().getDtCotacao()))
                .vrCotacao(cotacaoDolar.getValoresCotacao().getVrCotacaoCompra())
                .tipoCotacao(TIPO_COTACAO)
                .build();
    }

    public Cotacoes toCotacoesRedis(Cotacao cotacao) {
        return Cotacoes.builder()
                .vrCotacao(cotacao.getVrCotacao())
                .tipoCotacao(cotacao.getTipoCotacao())
                .dtCotacao(cotacao.getDtCotacao())
                .id(cotacao.getId())
                .build();
    }

    public CotacaoRepresentation toCotacaoRepresentation(CotacaoDolar cotacaoDolar) {
        return CotacaoRepresentation.builder()
                .dtCotacao(DataUtils.transformarData(cotacaoDolar.getValoresCotacao().getDtCotacao()))
                .tipoCotacao(TIPO_COTACAO)
                .vrCotacao(cotacaoDolar.getValoresCotacao().getVrCotacaoCompra())
                .build();
    }

    public CotacaoRepresentation toCotacaoRepresentationfromRedis(Cotacoes cotacoes) {
        return CotacaoRepresentation.builder()
                .dtCotacao(cotacoes.getDtCotacao())
                .tipoCotacao(TIPO_COTACAO)
                .vrCotacao(cotacoes.getVrCotacao())
                .build();
    }

    public CotacaoRepresentation toCotacaoNaoRegistrada() {
       return CotacaoRepresentation.builder()
                .dtCotacao(LocalDate.now())
                .tipoCotacao("NENHUMA COTAÇÃO REGISTRADA")
                .vrCotacao(BigDecimal.ZERO)
                .build();
    }

}
