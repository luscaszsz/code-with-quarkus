# API de Simulação de Investimentos

Esta aplicação é uma API REST desenvolvida com Quarkus
para simulação de investimentos com base em produtos financeiros.

A aplicação permite:
- Consulta de produtos disponíveis - GET http://localhost:8081/investimento/produtos
- Simulação de investimentos - POST http://localhost:8081/investimento/simulacoes 
- Persistência de simulações - POST http://localhost:8081/investimento/simulacoes
- Consulta de simulações por cliente - GET http://localhost:8081/investimento/simulacoes?clienteId=xxx
- Autenticação via JWT GET http://localhost:8082/jwt - Use o token no Bearer Authorization do endpoint acima (consulta de simulações por cliente)
- Swagger da aplicação - GET http://localhost:8081/q/swagger-ui
- Tratamento estruturado de erros
- Logs estruturados em JSON

Consulte o swagger ou "Evidência de teste.pdf" para mais detalhes. Suba utilizando start.sh.
## Arquitetura

A aplicação segue arquitetura em camadas:

Resource → Service → Model

- Resource: Camada REST
- Service: Regras de negócio
- Model: Entidades e persistência
- Exception Mappers: Tratamento global de erros

Além disso, a aplicação é separada em módulos, por isso as duas portas (8081 e 8082) para localhost.
## Segurança

A API utiliza autenticação baseada em JWT.
Alguns endpoints exigem role "Admin".

Códigos de erro:
- 400 → Erro de validação estrutural
- 401 → Token inválido ou ausente
- 403 → Acesso negado
- 422 → Regra de negócio
- 500 → Erro interno

## Requisitos
- Docker instalado
- Docker Compose (opcional)

Para subir a aplicação: _EXECUTAR START.SH_
## Banco de dados

- SQLite
- Migrations com Flyway
- Banco embarcado na aplicação

## Testes

Foram implementados testes unitários para:
- Services
- DTOs
- Regras de negócio

A camada JWT utiliza mecanismo padrão do Quarkus. Por ser aplicação de suporte básica, não houve necessidade de teste.

## Tecnologias
- Java 21
- Quarkus
- Panache
- Flyway
- SQLite
- JWT (SmallRye)
- Docker
- Openapi/swagger
