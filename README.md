# Alegrete Locacoes

AlegreteLocacoes é um projeto Java que visa gerenciar locações de veículos. Este projeto é estruturado para facilitar a manutenção e a expansão, utilizando as melhores práticas de desenvolvimento.

## Estrutura do Projeto

O projeto é organizado da seguinte forma:

```
AlegreteLocacoes
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── alegretelocacoes
│   │   │           ├── App.java
│   │   │           └── models
│   │   │               └── Locacao.java
│   │   └── resources
│   │       └── application.properties
│   └── test
│       └── java
│           └── com
│               └── alegretelocacoes
│                   └── AppTest.java
├── pom.xml
└── README.md
```

## Funcionalidades

- **Gerenciamento de Locações**: Permite registrar e gerenciar locações de veículos.
- **Configuração Flexível**: Utiliza um arquivo de propriedades para configuração de ambiente e banco de dados.
- **Testes Automatizados**: Inclui testes unitários para garantir a qualidade do código.

## Como Executar

1. Clone o repositório:
   ```
   git clone https://github.com/seu_usuario/AlegreteLocacoes.git
   ```

2. Navegue até o diretório do projeto:
   ```
   cd AlegreteLocacoes
   ```

3. Compile o projeto usando Maven:
   ```
   mvn clean install
   ```

4. Execute a aplicação:
   ```
   mvn exec:java -Dexec.mainClass="com.alegretelocacoes.App"
   ```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.

## Licença

Este projeto está licenciado sob a MIT License. Veja o arquivo LICENSE para mais detalhes.