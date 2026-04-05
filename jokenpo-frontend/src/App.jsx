import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

const API_BASE_URL = 'http://localhost:8080/api/jokenpo';

function App() {
  const [nomeJogador, setNomeJogador] = useState('');
  const [numRodadas, setNumRodadas] = useState(0);
  const [jogoIniciado, setJogoIniciado] = useState(false);
  const [estadoJogo, setEstadoJogo] = useState(null);
  const [erro, setErro] = useState('');

  const iniciarJogo = async () => {
    if (!nomeJogador || numRodadas <= 0) {
      setErro('Por favor, preencha seu nome e um número de rodadas válido.');
      return;
    }

    setErro('');

    try {
      const response = await axios.post(`${API_BASE_URL}/iniciar`, null, {
        params: {
          nomeJogador,
          numRodadas
        },
          withCredentials: true

      });


      setEstadoJogo(response.data);
      setJogoIniciado(true);
    } catch (error) {
      console.error('Erro ao iniciar o jogo:', error);
      setErro('Erro ao iniciar o jogo. Verifique o console.');
    }
  };

  const fazerJogada = async (escolha) => {
    setErro('');

    try {
      const response = await axios.post(`${API_BASE_URL}/jogar`, 
        {escolhaJogador: escolha},
          { withCredentials: true }
      );


      setEstadoJogo(response.data);
    } catch (error) {
      console.error('Erro ao fazer jogada:', error);
      setErro('Erro ao fazer jogada. Verifique o console.');
    }
  };

  const reiniciarJogo = () => {
    setJogoIniciado(false);
    setEstadoJogo(null);
    setNomeJogador('');
    setNumRodadas(0);
    setErro('');
  };

  const renderInicioJogo = () => (
    <div className="card">
      <h1>Bem-vindo ao Jokenpo Web!</h1>

      {erro && <p className="erro">{erro}</p>}

      <div className="campo">
        <label>Seu Nome:</label>
        <input
          type="text"
          value={nomeJogador}
          onChange={(e) => setNomeJogador(e.target.value)}
          placeholder="Digite seu nome"
        />
      </div>

      <div className="campo">
        <label>Número de Rodadas:</label>
        <input
          type="number"
          value={numRodadas}
          onChange={(e) => setNumRodadas(parseInt(e.target.value) || 0)}
          min="1"
        />
      </div>

      <button onClick={iniciarJogo}>Iniciar Jogo</button>
    </div>
  );

  const renderResultadoFinal = () => (
    <div className="resultado-final">
      <h2>FIM DO JOGO!</h2>

      <p><strong>Jogador:</strong> {estadoJogo.nomeJogador}</p>
      <p><strong>Pontos do Jogador:</strong> {estadoJogo.pontuacaoJogador}</p>
      <p><strong>Pontos da Máquina:</strong> {estadoJogo.pontuacaoMaquina}</p>

      {estadoJogo.resultadoFinal === 'JOGADOR_VENCEU' && (
        <p className="mensagem-final">
          {`PARABÉNS, ${nomeJogador.toUpperCase()}! VOCÊ É O GRANDE VENCEDOR!`}
        </p>
      )}

      {estadoJogo.resultadoFinal === 'MAQUINA_VENCEU' && (
        <p className="mensagem-final">
          {`A MÁQUINA VENCEU! Mais sorte na próxima vez, ${nomeJogador}!`}
        </p>
      )}

      {estadoJogo.resultadoFinal === 'EMPATE_FINAL' && (
        <p className="mensagem-final">
          O JOGO TERMINOU EM EMPATE!
        </p>
      )}

      <button onClick={reiniciarJogo}>Jogar Novamente</button>
    </div>
  );

  const renderJogo = () => (
    <div className="card">
      <h2>
        Rodada {estadoJogo?.rodadaAtual} de {estadoJogo?.totalRodadas}
      </h2>

      {erro && <p className="erro">{erro}</p>}

      {estadoJogo?.resultadoFinal === 'EM_ANDAMENTO' ? (
        <>
          <div className="placar">
            <p><strong>Jogador:</strong> {estadoJogo?.nomeJogador}</p>
            <p><strong>Pontos do Jogador:</strong> {estadoJogo?.pontuacaoJogador}</p>
            <p><strong>Pontos da Máquina:</strong> {estadoJogo?.pontuacaoMaquina}</p>
          </div>

          {estadoJogo?.escolhaJogador && estadoJogo?.escolhaMaquina && (
            <div className="resultado-rodada">
              <p><strong>Sua jogada:</strong> {estadoJogo.escolhaJogador}</p>
              <p><strong>Jogada da Máquina:</strong> {estadoJogo.escolhaMaquina}</p>
              <p><strong>Resultado da Rodada:</strong> {estadoJogo.resultadoRodada}</p>
            </div>
          )}

          <div className="botoes-jogada">
            <button onClick={() => fazerJogada('PEDRA')}>PEDRA</button>
            <button onClick={() => fazerJogada('PAPEL')}>PAPEL</button>
            <button onClick={() => fazerJogada('TESOURA')}>TESOURA</button>
          </div>
        </>
      ) : (
        renderResultadoFinal()
      )}
    </div>
  );

  return (
    <div className="App">
      <h1>Jokenpo Online</h1>
      {!jogoIniciado ? renderInicioJogo() : renderJogo()}
    </div>
  );
}

export default App;