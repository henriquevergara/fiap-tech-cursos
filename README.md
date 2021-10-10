<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/henriquevergara/android-alura-orgs">
    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Android_robot.png/504px-Android_robot.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Fiap-tech-cursos</h3>

  <p align="center">
    Entregável da atividade front-end mobile
    <br />
</p>

<!-- ABOUT THE PROJECT -->
## Sobre o projeto

[![Product Name Screen Shot][product-screenshot]](https://user-images.githubusercontent.com/36764823/127744569-b5f72ef4-4c15-4390-96b6-5cd5ccbfd59e.png)

Este projeto foi desenvolvido para simular uma plataforma de cursos online, propondo-se a aplicar as melhorias levantadas na primeira fase do MBA.

Instruções Gerais:
* Aplicativo desenvolvido para plataforma Android, utilizando Android Studio.
* Para se logar na plataforma, não esta sendo utilizado Autenticação, ja que o foco da entrega de valor estava na navegação e utilização do app, então basta apenas acessar o aplicativo pelo botão "Entrar" para se conectar a plataforma.
* A listagem dos cursos da plataforma esta sendo consumida de uma api desenvolvida em Java e utilizando o framework Spring, para facilitar o uso da API, foi realizado um deploy na plataforma Heroku, e a integração foi feita utilizando a biblioteca Retrofit.

### Tecnologias utilizadas

* [Kotlin](https://developer.android.com/kotlin)
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [API](https://api-tech-cursos.herokuapp.com/cursos)

<!-- GETTING STARTED -->
# Feedback de usuários externos

A aplicação foi submetida para usuários externos avaliarem a usabilidade da aplicação, obtivemos e corrigimos os seguintes detalhes 

## Tela de Login 

- Botão entrar e botão de cadastro estão muito próximos 
- Botão entrar deve estar inativo até o preenchimento estar completo 
- Placeholder está sumindo após preenchimento do campo 

## Tela principal (Painel com a lista de cursos)

- Card está muito próximo da margem superior e inferior 

## Tela de detalhes do curso

- Botão de adicionar ao carrinho está muito próximo da margem superior e inferior 
- Tela de Detalhamento está sem botão de retorno para a tela principal 

## Tela de Cadastro 

- Botão de cadastro deve estar inativo até o preenchimento estar completo 
- Tela de cadastro está sem botão de retornar para a tela de login 
- Placeholder está sumindo após preenchimento do campo 



[product-screenshot]: https://user-images.githubusercontent.com/36764823/127744569-b5f72ef4-4c15-4390-96b6-5cd5ccbfd59e.png
