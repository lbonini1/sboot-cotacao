package br.com.sboot.cotacao.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="TBLCOTACAO")
public class Cotacao implements Serializable {

    private static final Long serialUID = 1L;

    @Column(name="IDCOTACAO")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TPCOTACAO")
    private String tipoCotacao;

    @Column(name="DTCOTACAO")
    private LocalDate dtCotacao;

    @Column(name="VRCOTACAO")
    private BigDecimal vrCotacao;

}
