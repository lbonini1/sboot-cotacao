package br.com.sboot.cotacao.repository;

import br.com.sboot.cotacao.entity.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
}
