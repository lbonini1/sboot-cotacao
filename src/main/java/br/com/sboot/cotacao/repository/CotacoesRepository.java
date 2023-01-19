package br.com.sboot.cotacao.repository;

import br.com.sboot.cotacao.redis.Cotacoes;
import org.springframework.data.repository.CrudRepository;

public interface CotacoesRepository extends CrudRepository<Cotacoes, Long> {
}
