package br.com.sboot.cotacao.service;

import br.com.sboot.cotacao.camel.CotacaoRoute;
import br.com.sboot.cotacao.domain.CotacaoDolar;
import br.com.sboot.cotacao.entity.Cotacao;
import br.com.sboot.cotacao.mapper.CotacaoMapper;
import br.com.sboot.cotacao.redis.Cotacoes;
import br.com.sboot.cotacao.repository.CotacaoRepository;
import br.com.sboot.cotacao.repository.CotacoesRepository;
import br.com.sboot.cotacao.representation.CotacaoRepresentation;
import br.com.sboot.cotacao.utils.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CotacaoService {

    @Autowired
    private ProducerTemplate template;

    @Autowired
    private CotacaoMapper mapper;

    @Autowired
    private CotacaoRepository repository;

    @Autowired
    private CotacoesRepository redisRepository;

    public CotacaoRepresentation obterCotacaoDolar(){
        List<Cotacoes> cotacoes = new ArrayList<>();
        Cotacoes cotacao = obterListaCotacoesRepositorioRedis(cotacoes);
        return cotacao != null ? mapper.toCotacaoRepresentationfromRedis(cotacao) :
                mapper.toCotacaoNaoRegistrada();
    }

    private Cotacoes obterListaCotacoesRepositorioRedis(List<Cotacoes> cotacoes) {
        atualizarCotacoesRedis();
        if(redisRepository.findAll().iterator().hasNext()) {
            redisRepository.findAll().iterator().forEachRemaining(cotacoes::add);
            Optional<Cotacoes> cotacao = cotacoes.stream()
                    .filter(o -> o.getDtCotacao().isEqual(LocalDate.now())).findFirst();
            return cotacao.orElse(null);
        }
        return null;
    }

    private void atualizarCotacoesRedis() {
        limparListaCotacoesRedisEmMemoria();
        selecionarCotacoesExistentes().stream().forEach(c -> redisRepository.save(mapper.toCotacoesRedis(c)));
    }

    private void limparListaCotacoesRedisEmMemoria() {
        redisRepository.deleteAll();
    }

    public CotacaoRepresentation atualizarCotacao() {
        CotacaoDolar cotacaoDolar = verificarAtualizacaoCotacao();
        atualizarCotacaoBaseRelacional(cotacaoDolar);
        return mapper.toCotacaoRepresentation(cotacaoDolar);
    }

    private CotacaoDolar verificarAtualizacaoCotacao() {
        return template.requestBody(CotacaoRoute.ROUTE_ATUALIZAR, null, CotacaoDolar.class);
    }

    private Cotacao atualizarCotacaoBaseRelacional(CotacaoDolar cotacaoDolar) {
        Cotacao cotacao = verificarCotacaoPreExistente(cotacaoDolar);
        return cotacao == null ? salvarCotacao(cotacaoDolar) : atualizarCotacao(cotacao);
    }

    private Cotacao salvarCotacao(CotacaoDolar cotacaoDolar) {
        return repository.save(mapper.toEntityCotacao(cotacaoDolar));
    }

    private Cotacao atualizarCotacao(Cotacao cotacao) {
        return repository.saveAndFlush(cotacao);
    }

    private Cotacao verificarCotacaoPreExistente(CotacaoDolar cotacaoDolar) {
        Optional<Cotacao> cotacao = selecionarCotacoesExistentes().stream()
                .filter(c -> c.getDtCotacao().equals(
                        DataUtils.transformarData(cotacaoDolar.getValoresCotacao().getDtCotacao()))).findFirst();
        return cotacao.orElse(null);
    }

    private List<Cotacao> selecionarCotacoesExistentes() {
        return repository.findAll();
    }

}
