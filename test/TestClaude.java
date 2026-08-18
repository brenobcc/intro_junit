import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import calculadora.Calculadora;
import carrinho.Carrinho;
import produto.Produto;
import produto.ProdutoNaoEncontradoException;

/**
 * Casos de teste gerados para as classes Calculadora e Carrinho.
 */
@DisplayName("TestClaude - Calculadora e Carrinho")
public class TestClaude {

	@Nested
	@DisplayName("Testes da Calculadora")
	class CalculadoraTest {

		private Calculadora calc;

		@BeforeEach
		public void inicializa() {
			calc = new Calculadora();
		}

		@Test
		@DisplayName("Soma de dois números positivos")
		public void testSoma() {
			assertEquals(9, calc.soma(4, 5));
		}

		@Test
		@DisplayName("Soma envolvendo números negativos")
		public void testSomaComNegativos() {
			assertEquals(-3, calc.soma(-8, 5));
		}

		@Test
		@DisplayName("Subtração de dois números")
		public void testSubtracao() {
			assertEquals(3, calc.subtracao(7, 4));
		}

		@Test
		@DisplayName("Subtração resultando em número negativo")
		public void testSubtracaoResultadoNegativo() {
			assertEquals(-3, calc.subtracao(4, 7));
		}

		@Test
		@DisplayName("Multiplicação de dois números")
		public void testMultiplicacao() {
			assertEquals(28, calc.multiplicacao(7, 4));
		}

		@Test
		@DisplayName("Multiplicação por zero")
		public void testMultiplicacaoPorZero() {
			assertEquals(0, calc.multiplicacao(7, 0));
		}

		@Test
		@DisplayName("Divisão de dois números")
		public void testDivisao() {
			assertEquals(2, calc.divisao(8, 4));
		}

		@Test
		@DisplayName("Divisão por zero lança ArithmeticException")
		public void testDivisaoPorZero() {
			ArithmeticException ex = assertThrows(ArithmeticException.class,
					() -> calc.divisao(8, 0));
			assertEquals("/ by zero", ex.getMessage());
		}

		@Test
		@DisplayName("Somatório de 0 até n")
		public void testSomatoria() {
			assertEquals(36, calc.somatoria(8));
		}

		@Test
		@DisplayName("Somatório com n igual a zero")
		public void testSomatoriaComZero() {
			assertEquals(0, calc.somatoria(0));
		}

		@Test
		@DisplayName("Verifica número positivo")
		public void testEhPositivoComNumeroPositivo() {
			assertTrue(calc.ehPositivo(19));
		}

		@Test
		@DisplayName("Verifica número negativo")
		public void testEhPositivoComNumeroNegativo() {
			assertFalse(calc.ehPositivo(-19));
		}

		@Test
		@DisplayName("Zero é considerado positivo")
		public void testEhPositivoComZero() {
			assertTrue(calc.ehPositivo(0));
		}

		@Test
		@DisplayName("Compara dois números iguais")
		public void testComparaIguais() {
			assertEquals(0, calc.compara(5, 5));
		}

		@Test
		@DisplayName("Compara quando o primeiro é maior")
		public void testComparaMaior() {
			assertEquals(1, calc.compara(5, 4));
		}

		@Test
		@DisplayName("Compara quando o primeiro é menor")
		public void testComparaMenor() {
			assertEquals(-1, calc.compara(4, 5));
		}
	}

	@Nested
	@DisplayName("Testes do Carrinho")
	class CarrinhoTest {

		private Carrinho carrinho;

		@BeforeEach
		public void inicializa() {
			carrinho = new Carrinho();
		}

		@Test
		@DisplayName("Carrinho recém-criado está vazio")
		public void testCarrinhoNovoEstaVazio() {
			assertEquals(0, carrinho.getQtdeItems());
			assertEquals(0.0, carrinho.getValorTotal());
		}

		@Test
		@DisplayName("Adiciona um item ao carrinho")
		public void testAdicionaItem() {
			carrinho.addItem(new Produto("Caneta", 2.5));
			assertEquals(1, carrinho.getQtdeItems());
			assertEquals(2.5, carrinho.getValorTotal());
		}

		@Test
		@DisplayName("Adiciona múltiplos itens e soma o valor total")
		public void testAdicionaMultiplosItens() {
			carrinho.addItem(new Produto("Caneta", 2.5));
			carrinho.addItem(new Produto("Caderno", 15.0));
			carrinho.addItem(new Produto("Lápis", 1.0));

			assertEquals(3, carrinho.getQtdeItems());
			assertEquals(18.5, carrinho.getValorTotal(), 0.0001);
		}

		@Test
		@DisplayName("Remove um item existente do carrinho")
		public void testRemoveItemExistente() throws ProdutoNaoEncontradoException {
			Produto caneta = new Produto("Caneta", 2.5);
			carrinho.addItem(caneta);
			carrinho.addItem(new Produto("Caderno", 15.0));

			carrinho.removeItem(caneta);

			assertEquals(1, carrinho.getQtdeItems());
			assertEquals(15.0, carrinho.getValorTotal());
		}

		@Test
		@DisplayName("Remover item inexistente lança ProdutoNaoEncontradoException")
		public void testRemoveItemInexistente() {
			carrinho.addItem(new Produto("Caderno", 15.0));

			assertThrows(ProdutoNaoEncontradoException.class,
					() -> carrinho.removeItem(new Produto("Caneta", 2.5)));
		}

		@Test
		@DisplayName("Esvazia o carrinho com itens")
		public void testEsvaziaCarrinho() {
			carrinho.addItem(new Produto("Caneta", 2.5));
			carrinho.addItem(new Produto("Caderno", 15.0));

			carrinho.esvazia();

			assertEquals(0, carrinho.getQtdeItems());
			assertEquals(0.0, carrinho.getValorTotal());
		}

		@Test
		@DisplayName("Esvaziar carrinho já vazio não lança erro")
		public void testEsvaziaCarrinhoVazio() {
			carrinho.esvazia();
			assertEquals(0, carrinho.getQtdeItems());
		}
	}
}
