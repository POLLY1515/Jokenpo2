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
		//Esses valores sao iniciais
		this.pontuacaoJogador = 0;
		this.pontuacaoMaquina = 0;
		this.rodadaAtual = 0; //começa em 0 , incrementa para 1 na primeira rodada
		
		
	}
	
	//Metodo para iniciar ou reiniciar o jogo
	public JogoResponse iniciarNovoJogo(String nomeJogador, int numRodadas) {
		this.nomeJogador = nomeJogador;
		this.numRodadas = numRodadas;
		this.pontuacaoJogador = 0;
		this.pontuacaoMaquina = 0;
		this.rodadaAtual = 0; //reseta para 0 para a primeira rodada ser 1
		return JogoResponse.builder()
				.mensagem("Bem vindo " + nomeJogador + "! O jogo de " + numRodadas + "rodadas começou. ")
				.pontuacaoJogador(pontuacaoJogador)
				.pontuacaoMaquina(pontuacaoMaquina)
				.rodadaAtual(rodadaAtual)
				.totalRodadas(numRodadas)
				.resultadoFinal("EM_ANDAMENTO")
				.build();
	}
	
	//Metodo jogarRodada
	//Objetivo:Esse método processa uma rodada do jogo a partir da escolha feita
	//pelo jogador

	public JogoResponse jogarRodada(Opcoes escolhaJogador) {
	if (rodadaAtual >= numRodadas) {
	return JogoResponse.builder()
	.mensagem("O jogo já terminou. Inicie um novo jogo.")
	// ...campos do estado atual
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
			.escolhaJogador(escolhaJogador)
			.escolhaMaquina(escolhaMaquina)
			.pontuacaoJogador(pontuacaoJogador)
			.pontuacaoMaquina(pontuacaoMaquina)
			.resultadoRodada(resultadoRodada)
			.resultadoFinal(resultadoFinal)
			.rodadaAtual(rodadaAtual)
			.totalRodadas(numRodadas)
			.build();
	}
	
	//Metodo gerarJogadaMaquina
	//Objetivo:Gerar uma jogada aleatória para a maquina
	private Opcoes gerarJogadaMaquina() {
		int escolha = random.nextInt(Opcoes.values().length);
		return Opcoes.values()[escolha];
	}
	
	//metodo determinarVencedorRodada
	private String determinarVencedorRodada(Opcoes jogador, Opcoes maquina){
			if (jogador == maquina) {
			return "EMPATE";
			} else if (
			(jogador == Opcoes.PEDRA    && maquina == Opcoes.TESOURA) ||
			(jogador == Opcoes.PAPEL    && maquina == Opcoes.PEDRA)   ||
			(jogador == Opcoes.TESOURA  && maquina == Opcoes.PAPEL)
			) {
			pontuacaoJogador++;
			return "VITORIA";
			} else {
			pontuacaoMaquina++;
			return "DERROTA";
			}
	}
	
	//Metodo determinarResultadoFinal
	private String determinarResultadoFinal() {
		if(pontuacaoJogador > pontuacaoMaquina) {
			return "JOGADOR_VENCEU";
		
	}else if(pontuacaoMaquina > pontuacaoJogador) {
		return "MAQUINA_VENCEU";
	}else {
		return "EMPATE_FINAL";
	}
	
	}

}


