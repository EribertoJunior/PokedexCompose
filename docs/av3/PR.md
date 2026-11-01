# fix: descrição do Pokémon com caracteres de controle (`\f`)

## Intenção
A tela de detalhes mostra o flavor text da PokeAPI. Esse texto separa linhas com `\n` e frases com `\f` (form feed). `PokemonUIMapper.toUI()` só trocava o `\n`, então o `\f` chegava à UI. Exemplo real, `GET https://pokeapi.co/api/v2/pokemon-species/1/`, primeira entrada `en` (versão red):

```
'A strange seed was\nplanted on its\nback at birth.\x0cThe plant sprouts\nand grows with\nthis POKéMON.'
```

## Escopo (declarado antes, commit `04c118a`, `docs/av3/escopo.md`)
- **Objetivo:** a descrição vira um parágrafo com espaço simples, sem espaço nas pontas.
- **Pode tocar:** `PokemonUIMapper.kt` (campo `description`), `PokemonUIMapperTest.kt` e `docs/av3/`.
- **Não pode tocar:** camada de dados e Room, build e dependências, composables e os demais campos.
- **Invariantes:** outros campos iguais, banco inalterado, suíte verde e Kover ≥ 80%.
- **Pronto quando:** teste novo vermelho antes e verde depois, mais a sabotagem.
- **Pare e pergunte:** dados ou banco, soft hyphen, dependências.

Barreira executada: `git diff --stat master...HEAD` mostra 4 arquivos, todos no "pode tocar":
```
 .../pokedexcompose/ui/mappers/PokemonUIMapper.kt   |  2 +-
 .../ui/mappers/PokemonUIMapperTest.kt              | 35 ++++++++++++++
 docs/av3/escopo.md                                 | 12 +++++
 docs/av3/mutacao_desc.py                           | 55 ++++++++++++++++++++++
```

## Plano
1. Commit do escopo.
2. Commit só do teste: ver o teste vermelho.
3. Commit da correção, de 1 linha: `replace(Regex("\\s+"), " ").trim()`.
4. Rodar a suíte inteira e o Kover.
5. Sabotagem e mutação manual.

Cortado do plano: tratar o soft hyphen (`­`) e normalizar o texto já na camada de dados. Os dois mudariam o banco ou outro mapper, o que está fora do escopo.

## Commits (atômicos)
| Commit | Conteúdo |
| --- | --- |
| `04c118a` | escopo |
| `e404be8` | 2 testes novos (falham sem a correção) |
| `b8cdbf6` | correção (1 linha) |
| `d9cf7f3` | script de mutação reproduzível |

## Verificação
| Etapa | Comando | Saída |
| --- | --- | --- |
| Baseline (`master`) | `gradlew.bat :app:testDebugUnitTest` | `BUILD SUCCESSFUL`, 49 testes, 0 falhas |
| Antes da correção (`e404be8`) | `gradlew.bat :app:testDebugUnitTest --tests "*PokemonUIMapperTest*"` | `3 tests completed, 2 failed` |
| Depois (`b8cdbf6`) | `gradlew.bat :app:testDebugUnitTest` | `BUILD SUCCESSFUL`, 51 testes, 0 falhas |
| Cobertura | `gradlew.bat :app:koverVerifyDebug :app:koverLogDebug` | `application line coverage: 84.141%`, verify OK |

Falhas observadas antes da correção (relatório JUnit):

```
expected:<...n its back at birth.[ ]The plant sprouts an...> but was:<...n its back at birth.[?]The plant sprouts an...>
expected:<[It breathes flames. However, it will never torch a weaker foe.]> but was:<[  It breathes&#13; flames.&#9;However,   it will??never torch a weaker foe. ]>
```

**Testes que falhariam sem a mudança:**
- `toUI should turn the PokeAPI flavor text into a single-spaced paragraph` usa o dado real.
- `toUI should collapse whitespace runs and trim the description` cobre `\r\n`, `\t`, espaços duplos, `\f\f` e as bordas.

**Mutação** (`python -I docs/av3/mutacao_desc.py .`): 5 de 5 mutantes mortos, com controle.
```
M0 controle (sem mutacao)    -> OK
M1 codigo antigo (so \n)     -> MORTO 3 tests completed, 2 failed
M2 sem trim                  -> MORTO 3 tests completed, 1 failed
M3 \s sem +                  -> MORTO 3 tests completed, 1 failed
M4 so \n e \f                -> MORTO 3 tests completed, 1 failed
M5 so trim                   -> MORTO 3 tests completed, 2 failed
```
M2, M3 e M4 morrem só pelo caso sintético. Por isso existe o segundo teste: o dado real sozinho não os detecta.

## Dependências novas
Nenhuma. `Regex` e `trim` são da stdlib do Kotlin, e `libs.versions.toml` e `build.gradle.kts` ficaram intactos.

## Modo de interação e por quê
**Agente (Claude Code, Claude Opus 5.5) em modo plano:** o agente investigou e escreveu o plano sem editar nada, eu aprovei o plano, e depois o agente executou com o escopo commitado antes do código. Revisei as saídas no fim de cada etapa (vermelho, verde, Kover, mutação), não cada edição.
- Raio de impacto pequeno: 1 função pura de apresentação, sem I/O, reversível por `git`.
- Oráculo forte: o payload real da API mais uma regra de exibição verificável por igualdade exata.
- Familiaridade alta: o projeto é meu e já tinha um teste do mapper.

**Alternativa comparada:** agente com aprovação manual de cada edição (o modo A da minha AV1.1). Nela, a T1 levou 9 min e 6 idas e vindas, contra 3 min e 1 ida e volta no modo com revisão no fim (n=1, com efeito de ordem). Aprovar edição por edição só se paga sem oráculo automático ou com raio maior que um arquivo, e nenhuma das duas condições vale aqui. O que eu precisava controlar era o **escopo**: na AV1.2, declará-lo antes reduziu as idas e vindas de 12 para 5. Por isso o escopo veio no primeiro commit, antes do código.

## Limites
- Verificado só na camada de UI (teste JVM). Não abri o app num emulador para ver o `\f` renderizado.
- O texto no banco continua bruto.
- O soft hyphen e outros caracteres Unicode invisíveis que não são espaço em branco ficam fora do escopo.
