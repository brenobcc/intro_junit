package calculadora;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Classe para teste da calculadora")
public class CalculadoraTest {
	
	private Calculadora calc;
	
	@BeforeEach
	public void inicializa() {
		calc = new Calculadora();
	}
	
	@DisplayName("Testa a soma de dois n�meros")
	@Test
	public void testSomaDoisNumeros() {
		int soma = calc.soma(4, 5);		
		Assertions.assertEquals(9, soma);		
	}
	
	@DisplayName("Testa a subtração de dois n�meros")
	@Test
	public void testSubtracaoDoisNumeros() {
		int subtracao = calc.subtracao(7, 4);
		Assertions.assertEquals(3, subtracao);
	}
	
	@DisplayName("Testa a multiplicação de dois n�meros")
	@Test
	public void testMultiplicacaoDoisNumeros() {
		int multiplicacao = calc.multiplicacao(7, 4);
		Assertions.assertEquals(28, multiplicacao);
	}
	
	@DisplayName("Testa a divisão de dois n�meros")
	@Test
	public void testDivisaoDoisNumeros() {
		int divisao = calc.divisao(8, 4);
		assertTrue(divisao == 2);
	}
	
	@DisplayName("Testa a divisão por zero")
	@Test
	public void testDivisaoPorZero() {
		try {
			int divisao = calc.divisao(8, 0);
			fail("Exce��o n�o lan�ada");
		}catch (ArithmeticException e) {
			assertEquals("/ by zero", e.getMessage());
		}		
	}
	
	@DisplayName("Testa a divisão por zero com assert throws")
	@Test
	public void testDivisaoPorZeroComAssertThrows() {
		assertThrows(ArithmeticException.class,
				() -> calc.divisao(8, 0));
	}
	
	@DisplayName("Testa a soma de um somatório")
	@Test
	public void testSomatorio() {
		int soma = calc.somatoria(8);
		assertEquals(36,soma);
		
	}
	
	@DisplayName("Testa comparação de dois números")
	@Test
	public void testComparaDoisNumeros() {
		int compara = calc.compara(4, 5);
		Assertions.assertTrue(compara < 0);
	}
	
	@DisplayName("Testa se um número é positivo")
	@Test
	public void testNumeroEhPositivo() {
		boolean ehPositivo = calc.ehPositivo(-19);
		Assertions.assertFalse(ehPositivo);
	}

}
