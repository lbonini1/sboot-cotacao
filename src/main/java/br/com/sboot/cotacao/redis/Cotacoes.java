package br.com.sboot.cotacao.redis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;
import java.time.LocalDate;

@RedisHash("cotacoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cotacoes {

    @Id @Indexed
    private Long id;

    private String tipoCotacao;

    private LocalDate dtCotacao;

    private BigDecimal vrCotacao;

}
