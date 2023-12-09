# MovieList - Descrição do Projeto
>  *Este repositório tem como objetivo disponibilizar o projeto de referência MoviesList*

## Documentação e testes de API com Swagger: 
http://localhost:8080/swagger-ui/index.html

## Domain Model

![Model](https://github.com/adrianoazevedo/movielist/blob/main/projeto/diagrama.png "Diagrama")
## Pré-configurado
Os seguintes recursos estão pré-configurados:
- Conteiners Docker Postgres e PGAdmin
- Profile H2 [test]
- Profile Postgres [dev]

### Features
- Busca Filmes
- Busca Filmes por id
- Insira novo Filmes
- Atualizar Filmes
- Excluir Filmes
- Busca Lista de Filmes
- Busca Lista por id
- Insira nova Lista
- Atualizar Lista
- Excluir Lista
- Associar filme a sua Lista sua posição
- Trocar filme de posição da Lista
- Exception handling: recurso não encontrado (*404 not found*)
- Exception handling: Erro no banco de dados (*500 bad request*)

### Spring Boot versions
- 3.2.0






