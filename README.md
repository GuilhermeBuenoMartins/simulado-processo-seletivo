# Simulado Processo Seletivo

O objetivo deste projeto é simular um desafio de um 
processo seletivo para desenvolvedor java _backend_.
Assim, é proposto um desafio que deverá ser cumprido
em um determinado número de dias.

## Desafio

Você foi convidado a desenvolver um novo projeto para
nosso projeto para nosso novo _**E-commerce**_. Com 
isso, você deverá criar uma **API Rest** que atenda
os seguinte critérios abaixo.

+ Deve ser possível efetuar um CRUD de um produto.

    - **Produto:** id, quantidade estoque, valor custo,
    valor venda, data de cadastro, observação.

+ Deve ser possível efetuar um CRUD de cliente.

    - **Cliente:** id, nome, telefone, CPF, e-mail, 
    endereço (id, CEP, Rua, ...).

+ Deve ser possível efetuar um CRUD de pedido.

    - **Pedido:** id, data, cliente, lista de produtos,
    desconto no total, valor total.
    
    - Deve ser possível dar desconto unitário, desconto
    no próprio item do pedido.

    - Deve ser enviado um e-mail com HTML para o 
    cliente, sempre que um pedido for criado.

+ Deve ter autenticação JWT.

    - Deve ter um CRUD de usuário.

    - Deve existir um método separado para efetuar 
    _login_.

+ Trabalhar com DTO.

+ Documentar o projeto (JavaDoc).

### Regras do Jogo

1. O projeto deve ser entregue versionado no _github_ 
em um repositório público.

2. Geralmente 1 semana é o tempo que as empresas dão 
para este desafio.

3. Não pode pegar um projeto já existente, tem que ser
um projeto craido do zero.
