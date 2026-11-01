# Escopo — descrição do Pokémon com caracteres de controle (AV3)

Escrito antes de qualquer alteração de código.

| Campo | Conteúdo |
| --- | --- |
| Objetivo | A descrição exibida na tela de detalhes deve ser um parágrafo corrido: toda sequência de espaços em branco ou caracteres de controle (`\n`, `\r`, `\t`, `\f`) vira um único espaço, sem espaços nas pontas. Hoje só o `\n` é trocado, e o `\f` que a PokeAPI envia (ex.: Bulbasaur, `"...back at birth.\fThe plant sprouts..."`) chega à tela. |
| Pode tocar | `app/src/main/java/com/example/pokedexcompose/ui/mappers/PokemonUIMapper.kt` (só o campo `description`) e `app/src/test/java/com/example/pokedexcompose/ui/mappers/PokemonUIMapperTest.kt`; `docs/av3/`. |
| Não pode tocar | Camada de dados (mappers remotos, entidades e banco Room), versões de build e dependências, composables da UI, os demais campos de `toUI()`. |
| Invariantes | Os demais campos de `PokemonUI` ficam iguais; o texto salvo no banco não muda; `:app:testDebugUnitTest` continua verde (baseline: 49 testes) e a cobertura do Kover continua ≥ 80%. |
| Pronto quando | Um teste novo de `description` falha no código atual e passa depois da mudança; a suíte unitária inteira passa; uma sabotagem (voltar ao `replace("\n", " ")`) deixa o teste vermelho. |
| Pare e pergunte | Se for preciso mexer na camada de dados ou no banco; tratar caracteres que não são espaço em branco (soft hyphen `­`, etc.); adicionar ou atualizar dependência. |
