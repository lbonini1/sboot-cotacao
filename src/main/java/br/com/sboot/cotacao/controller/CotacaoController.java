package br.com.sboot.cotacao.controller;

import br.com.sboot.cotacao.representation.CotacaoRepresentation;
import br.com.sboot.cotacao.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cotacao")
public class CotacaoController {

    @Autowired
    private CotacaoService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CotacaoRepresentation obterCotacao(){
        return service.obterCotacaoDolar();
    }

    @GetMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public CotacaoRepresentation atualizarCotacao() {
        return service.atualizarCotacao();
    }

}
