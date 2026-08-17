package jokenpo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import java.util.Random;

public class MainTest {
	
	private Main m;
	private Random r;

	@BeforeEach
	public void inicializa() {
		m = new Main();
		r = new Random();
	}
	
	@DisplayName("Pedra x Papel")
	@Test
	public void testJogar() {
		int jogador1 = 1;
		int jogador2 = 2;
		
		int jogo = m.jogar(jogador1, jogador2);
		Assertions.assertEquals(1, jogo);
	}
	
	@DisplayName("Teste do jogo inválido")
	@Test
	public void testJogoInvalido() {
		int jogador1 = 1;
		int jogador2 = 4;
		
		int jogo = m.jogar(jogador1, jogador2);
		Assertions.assertEquals(-1, jogo);
	}
	
	@DisplayName("Teste jogo igual")
	@Test
	public void testJogoIgual() {
		int numero = r.nextInt(3) + 1;
		
		int jogador1 = numero;
		int jogador2 = jogador1;
		
		int jogo = m.jogar(jogador1, jogador2);
		Assertions.assertEquals(0, jogo);
	}
	
	@DisplayName("Tesoura x Pedra")
	@Test
	public void testJogarTesouraPedra() {
		int jogador1 = 1;
		int jogador2 = 2;
		
		int jogo = m.jogar(jogador1, jogador2);
		Assertions.assertEquals(1, jogo);
	}
}
