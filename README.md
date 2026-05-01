# Pokedex Compose

Uma aplicação Pokedex moderna e fluida, construída com **Jetpack Compose** para demonstrar as melhores práticas de desenvolvimento Android, incluindo arquitetura reativa, paginação e persistência local.

## 🚀 Tecnologias e Bibliotecas

Este projeto utiliza o que há de mais moderno no ecossistema Android:

- **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Kit de ferramentas moderno para construção de UI nativa.
- **[Koin](https://insert-koin.io/):** Framework de Injeção de Dependência leve e pragmático.
- **[Retrofit](https://square.github.io/retrofit/):** Cliente HTTP type-safe para consumo da [PokeAPI](https://pokeapi.co/).
- **[Room](https://developer.android.com/training/data-storage/room):** Camada de persistência local para suporte offline.
- **[Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-paged-data):** Carregamento de dados por demanda para listas infinitas e performáticas.
- **[Coil](https://coil-kt.github.io/coil/):** Biblioteca de carregamento de imagens baseada em Coroutines.
- **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html):** Para programação assíncrona e fluxos de dados reativos.

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture** e o padrão **MVVM (Model-View-ViewModel)**, garantindo:
- Separação de responsabilidades.
- Facilidade de manutenção e testes.
- Código escalável e desacoplado.

## ✨ Funcionalidades

- **Listagem Infinita:** Explore todos os Pokemons com scroll infinito usando Paging 3.
- **Cache Offline:** Os dados são salvos localmente no banco de dados Room para acesso rápido e sem conexão.
- **Detalhes:** Informações detalhadas sobre cada Pokemon, incluindo tipos, habilidades e estatísticas.
- **Design Moderno:** UI construída inteiramente com Material 3 e animações suaves.

## 🛠️ Como rodar o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/PokedexCompose.git
   ```
2. Abra o projeto no **Android Studio**.
3. Sincronize o Gradle e execute o app em um emulador ou dispositivo físico (API 24+).

---
Desenvolvido para fins de estudo sobre Jetpack Compose e arquitetura Android.
