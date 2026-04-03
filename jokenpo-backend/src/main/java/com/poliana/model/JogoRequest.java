package com.poliana.model;

import com.poliana.enums.Opcoes;

import lombok.Data;

@Data
public class JogoRequest {
//jogoRequest: Define o que esperamos receber do jogador do frontend quando o jogador faz uma jogada, apenas a escolha
	
	private Opcoes escolhaJogador;//A escolha do jogador para a rodada
	
}
