# Padrões de Projeto GoF — Strategy e Decorator

Atividade da disciplina Técnicas de Programação II (TPII), Fatec Zona Leste.
Aluno: Ryan Leite.

O repositório tem dois projetos Java independentes:

| Pasta | Padrão | Exemplo |
|---|---|---|
| `strategy-descontos/` | Strategy | Cálculo de descontos de uma loja (sem desconto, 10% e 20%) |
| `decorator-cafeteria/` | Decorator | Café personalizado com leite e chocolate |

O PDF de entrega (pesquisa, código, capturas de tela, análise e referências) fica em `entrega/`.

## Como compilar e executar

Requisito: JDK 17 ou mais recente (testado com o JDK 21).

```powershell
cd strategy-descontos
javac -encoding UTF-8 -d out src\descontos\*.java
java -cp out descontos.Main
```

```powershell
cd decorator-cafeteria
javac -encoding UTF-8 -d out src\cafeteria\*.java
java -cp out cafeteria.Main
```

No Linux/macOS, troque `\` por `/` nos caminhos.

## Saída esperada

Strategy:

```
Valor original: R$ 200,00

Sem desconto: R$ 200,00
Desconto de 10%: R$ 180,00
Desconto de 20%: R$ 160,00
```

Decorator:

```
Café simples: R$ 5,00

Café com leite: R$ 7,00

Café com leite e chocolate: R$ 10,00

Composição do último pedido: Café simples, leite, chocolate
```
