# Trabalho1-TPPE


## Descrição 

Este projeto implementa um sistema de varejo utilizando Java, com foco em Test-Driven Development (TDD). O sistema inclui funcionalidades para cadastro de clientes, produtos e realização de vendas, levando em consideração diferentes tipos de clientes ("padrão", "especial" e "prime") e suas respectivas regras de desconto e cashback.

## Estrutura do projeto

- main: Contém as classes principais do sistema.

    - Cliente: Representa um cliente do sistema.
    - Endereco: Representa o endereço de um cliente.
    - Produto: Representa um produto à venda.
    - Venda: Representa uma venda realizada.
    - SistemaVarejo: Classe principal que gerencia clientes, produtos e vendas.

- tests: Contém as classes de teste.

    - ClienteTest: Testes para a classe Cliente.
    - EnderecoTest: Testes para a classe Endereco.
    - ProdutoTest: Testes para a classe Produto.
    - SistemaVarejoTest: Testes para a classe SistemaVarejo.

## Como foi desenvolvido

Este projeto foi desenvolvido utilizando a abordagem de Desenvolvimento Orientado a Testes (TDD). Usamos testes parametrizados e triangulação para garantir a robustez e confiabilidade do código.

## Metodologia
### Desenvolvimento Orientado a Testes (TDD)

Utilizamos a prática de TDD, onde os testes são escritos antes do código de produção. Isso nos ajudou a definir claramente os requisitos e a criar um código mais limpo e bem estruturado.
Testes Parametrizados

Os testes parametrizados permitiram verificar múltiplas entradas e saídas para os mesmos casos de teste, garantindo uma maior cobertura e eficiência na validação das funcionalidades do projeto.

### Triangulação

Aplicamos a técnica de triangulação para desenvolver soluções incrementais e testar várias condições e cenários. Isso ajudou a validar a generalidade e correção do nosso código em diferentes contextos.

## Como Executar os Testes

Este trabalho foi construído utilizando o Maven. Para executar os testes, utilize o comando abaixo:

```bash
mvn test
```
Outra opção é executando pelo Eclipse, basta rodar a classe de teste desejada

## Tecnologias utilizadas

- Java: Linguagem de programação utilizada para o desenvolvimento do sistema.

- JUnit 4: Framework de testes utilizado para a criação e execução dos testes unitários.



 ## Membros:
   | **Matrícula** | **Nome**                        | **Foto**                                                                                                    |
   | ------------- | ------------------------------- | ----------------------------------------------------------------------------------------------------------  |
   | 190045817     | Gabriel Costa de Oliveira       | <img src="https://avatars.githubusercontent.com/GabrielCostaDeOliveira " width="120px;" alt="Foto Gabriel"/>|
   | 200062379     | Marcos Vinícius de Deus         | <img src="https://avatars.githubusercontent.com/u/87666623?v=4" width="120px;" alt="Foto Marcos Vinicius"/> |
