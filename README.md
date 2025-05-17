# IoT Manager - Sistema de Gestão de Motos com IoT para a Mottu

## 📌 Descrição do Projeto

A proposta consiste no desenvolvimento de um sistema inteligente que integra tecnologias de IoT com uma plataforma web para otimizar a identificação, o monitoramento e a gestão das motos nos pátios das filiais da Mottu. Cada motocicleta será equipada com um sensor individual, semelhante à tecnologia utilizada em sistemas de pedágio automático como o “Sem Parar”, permitindo sua identificação automática e precisa ao entrar ou sair do pátio. Além disso, cada vaga de estacionamento contará com um LED sinalizador que indicará o status da moto estacionada — por exemplo, se está disponível para uso, em manutenção ou aguardando retirada —, facilitando a visualização imediata por parte da equipe operacional. Todas essas informações serão integradas a um sistema web centralizado, que oferecerá uma interface interativa e em tempo real, permitindo aos operadores visualizar a disposição das motos, acessar detalhes de cada unidade e gerenciar o fluxo de veículos com maior agilidade, segurança e eficiência.

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot. Ela gerencia o cadastro de motos e filiais, com suporte a operações de CRUD, filtros, ordenação, paginação e cache.

## 👨‍💻 Desenvolvedores

- Marcelo Antônio Scoleso Junior
- João Paulo Francisco de Oliveira

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Web
- Spring Data JPA
- Oracle Database
- ModelMapper
- Lombok
- SpringDoc OpenAPI (Swagger)
- Bean Validation
- Spring Cache

## ✅ Funcionalidades

- CRUD completo para as entidades Moto e Filial
- Relacionamento entre entidades
- Validações com Bean Validation
- Paginação e ordenação
- Busca por parâmetros
- Documentação interativa com Swagger (SpringDoc)
- Cache com Spring Cache
- Tratamento centralizado de erros
- Uso de DTOs e mapeamento com ModelMapper

## ▶️ Como executar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/seuusuario/iotmanager.git
cd iotmanager
./mvnw spring-boot:run
http://localhost:8080/swagger-ui.html
