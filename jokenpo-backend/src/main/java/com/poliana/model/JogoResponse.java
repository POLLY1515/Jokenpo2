package com.poliana.model;

import com.poliana.enums.Opcoes;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JogoResponse {
	//jogoResponse:Define o que o backend enviara de volta ao frontend apos cada jogada,contendo todas as informacoes atualizadas do jogo
	//Response, o que o sistema responde
	private String mensagem;
	private String nomeJogador;
	private Opcoes escolhaJogador;
	private Opcoes escolhaMaquina;
	private int pontuacaoJogador;
	private int pontuacaoMaquina;
	private String resultadoRodada;
	private String resultadoFinal;
	private int rodadaAtual;
	private int totalRodadas;

	
}


