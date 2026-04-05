package com.poliana.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poliana.model.JogoRequest;
import com.poliana.model.JogoResponse;
import com.poliana.services.jogoService;
@RestController
@RequestMapping("/api/jokenpo")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class JogoController {

    @Autowired
    private jogoService jogoService;

    @PostMapping("/iniciar")
    public JogoResponse iniciarJogo(@RequestParam String nomeJogador, @RequestParam int numRodadas) {
        return jogoService.iniciarNovoJogo(nomeJogador, numRodadas);
    }

    @PostMapping("/jogar")
    public JogoResponse fazerJogada(@RequestBody JogoRequest request) {
        return jogoService.jogarRodada(request.getEscolhaJogador());
    }
}

