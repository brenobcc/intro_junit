# Avaliação dos testes de `Main` (Jokenpo)

Análise de [`src/jokenpo/Main.java`](src/jokenpo/Main.java) e [`test/jokenpo/MainTest.java`](test/jokenpo/MainTest.java).

## 1. Os testes são suficientes?

**Não.** A cobertura é fraca. O método `jogar` tem 3 saídas possíveis (vitória do jogador 1, vitória do jogador 2, empate) além do caso inválido, mas os testes efetivamente só cobrem:

- Uma combinação de vitória: `(1, 2)` → papel x pedra (jogador 1 vence) — testada **duas vezes** (métodos diferentes, mesmos valores).
- Empate (com valor aleatório).
- Um caso inválido (`jogador2 = 4`).

Faltam:

- **Vitória do jogador 2** — nenhum teste cobre esse ramo (`retorno = 2`) de forma dedicada; nunca é exercitado.
- As outras combinações válidas de vitória: `(2,1)`, `(1,3)`, `(3,1)`, `(2,3)`, `(3,2)` — a lógica usa `jogador1 - jogador2 == -1 || == 2` para decidir o vencedor, e só uma dessas seis combinações é testada. Um erro nessa fórmula (fácil de errar, já que envolve "números mágicos") passaria despercebido.
- **Limites de entrada inválida**: `0`, números negativos, valores fora do range para *ambos* os jogadores (só testa `jogador2 = 4` com `jogador1` válido).
- Nenhuma verificação de que `jogador1` inválido sozinho também retorna `-1`.

## 2. Há testes redundantes?

**Sim, e é o problema mais evidente.** `testJogar` e `testJogarTesouraPedra` são idênticos byte a byte:

```java
int jogador1 = 1;
int jogador2 = 2;
int jogo = m.jogar(jogador1, jogador2);
Assertions.assertEquals(1, jogo);
```

Isso é claramente um copy-paste com erro: o `@DisplayName` do segundo diz "Tesoura x Pedra", mas os valores usados (`1, 2`) representam papel x pedra, não tesoura x pedra (que seria `2, 3`). Esse teste não adiciona cobertura nenhuma — ao invés de testar um caso novo, ele duplica um já existente sob um nome errado, mascarando uma lacuna real de cobertura.

## 3. Os testes seguem boas práticas?

Parcialmente. Pontos positivos: uso de `@BeforeEach`, `@DisplayName` descritivo (quando correto), nomes de método claros, um teste por comportamento.

Pontos que fogem de boas práticas:

- **Uso de `Random` sem seed em teste unitário** (`testJogoIgual`): testes devem ser determinísticos e reprodutíveis. Mesmo sendo "seguro" aqui (o mesmo valor é usado nos dois jogadores), é um padrão arriscado — se alguém alterar o teste incorretamente no futuro, ele pode se tornar não-determinístico sem que isso fique óbvio. Prefira valores fixos ou `@ParameterizedTest` com `@ValueSource`.
- **`@DisplayName` incorreto**: em `testJogar`, a descrição diz "Pedra x Papel", mas os valores (`1=papel, 2=pedra`) na verdade representam "Papel x Pedra". Nome enganoso.
- **Ausência de mensagens de falha** em `assertEquals` (parâmetro `message`), o que dificulta o diagnóstico quando um teste falha.
- **Falta de `@ParameterizedTest`**: como o método tem várias combinações de entrada com comportamento previsível, seria mais idiomático usar `@ParameterizedTest` com `@CsvSource` cobrindo todas as 6 combinações de vitória + empates + inválidos, em vez de métodos manuais repetidos (isso também teria evitado a duplicação do item 2).
- **Não há teste de "nome do método reflete o comportamento"**: `testJogarTesouraPedra` não testa o que o nome promete, indicando falta de revisão antes do commit.

## Resumo

A suíte tem uma base razoável (setup, nomes, uso de JUnit 5), mas está incompleta (não cobre a vitória do jogador 2 nem a maioria das combinações), contém um teste duplicado/mal nomeado, e usa `Random` de forma desnecessária. Recomenda-se reescrever com `@ParameterizedTest` cobrindo todas as combinações de 1 a 3 para ambos os jogadores, mais casos de borda inválidos (0, negativos, >3).
