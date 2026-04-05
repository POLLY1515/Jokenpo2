package com.poliana.services;

import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.poliana.enums.Opcoes;
import com.poliana.model.JogoResponse;
@Service
@SessionScope
public class jogoService {

    private String nomeJogador;
    private int numRodadas;
    private int pontuacaoJogador;
    private int pontuacaoMaquina;
    private int rodadaAtual;
    private Random random;

    public jogoService() {
        this.random = new Random();
        this.pontuacaoJogador = 0;
        this.pontuacaoMaquina = 0;
        this.rodadaAtual = 0;
    }

    public JogoResponse iniciarNovoJogo(String nomeJogador, int numRodadas) {
        this.nomeJogador = nomeJogador;
        this.numRodadas = numRodadas;
        this.pontuacaoJogador = 0;
        this.pontuacaoMaquina = 0;
        this.rodadaAtual = 0;

        return JogoResponse.builder()
                .mensagem("Bem vindo " + nomeJogador + "! O jogo de " + numRodadas + " rodadas começou.")
                .nomeJogador(this.nomeJogador)
                .pontuacaoJogador(this.pontuacaoJogador)
                .pontuacaoMaquina(this.pontuacaoMaquina)
                .rodadaAtual(this.rodadaAtual)
                .totalRodadas(this.numRodadas)
                .resultadoFinal("EM_ANDAMENTO")
                .build();
    }

    public JogoResponse jogarRodada(Opcoes escolhaJogador) {
        if (rodadaAtual >= numRodadas) {
            return JogoResponse.builder()
                    .mensagem("O jogo já terminou. Inicie um novo jogo.")
                    .nomeJogador(this.nomeJogador)
                    .pontuacaoJogador(this.pontuacaoJogador)
                    .pontuacaoMaquina(this.pontuacaoMaquina)
                    .rodadaAtual(this.rodadaAtual)
                    .totalRodadas(this.numRodadas)
                    .resultadoFinal(determinarResultadoFinal())
                    .build();
        }

        rodadaAtual++;

        Opcoes escolhaMaquina = gerarJogadaMaquina();
        String resultadoRodada = determinarVencedorRodada(escolhaJogador, escolhaMaquina);
        String resultadoFinal = "EM_ANDAMENTO";

        if (rodadaAtual == numRodadas) {
            resultadoFinal = determinarResultadoFinal();
        }

        return JogoResponse.builder()
                .mensagem("Jogada realizada com sucesso")
                .nomeJogador(this.nomeJogador)
                .escolhaJogador(escolhaJogador)
                .escolhaMaquina(escolhaMaquina)
                .pontuacaoJogador(this.pontuacaoJogador)
                .pontuacaoMaquina(this.pontuacaoMaquina)
                .resultadoRodada(resultadoRodada)
                .resultadoFinal(resultadoFinal)
                .rodadaAtual(this.rodadaAtual)
                .totalRodadas(this.numRodadas)
                .build();
    }

    private Opcoes gerarJogadaMaquina() {
        int escolha = random.nextInt(Opcoes.values().length);
        return Opcoes.values()[escolha];
    }

    private String determinarVencedorRodada(Opcoes jogador, Opcoes maquina) {
        if (jogador == maquina) {
            return "EMPATE";
        } else if (
                (jogador == Opcoes.PEDRA && maquina == Opcoes.TESOURA) ||
                (jogador == Opcoes.PAPEL && maquina == Opcoes.PEDRA) ||
                (jogador == Opcoes.TESOURA && maquina == Opcoes.PAPEL)
        ) {
            pontuacaoJogador++;
            return "VITORIA ";
        } else {
            pontuacaoMaquina++;
            return "DERROTA ";
        }
    }

    private String determinarResultadoFinal() {
        if (pontuacaoJogador > pontuacaoMaquina) {
            return "JOGADOR_VENCEU";
        } else if (pontuacaoMaquina > pontuacaoJogador) {
            return "MAQUINA_VENCEU";
        } else {
            return "EMPATE_FINAL";
        }
    }


}


