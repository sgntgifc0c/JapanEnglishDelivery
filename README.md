# Japan English Delivery - Sistema de Gerenciamento de Delivery de Comida

**Integrantes do Grupo:**
<br> *Fernanda Akemi Martins Sanpei - 43931251* <br>
*Pedro Viana da Silva - 43477089* <br>
*Gabriel Romão da Silva - 41881184* <br>
*Geziel de Andrade - 43608906* <br>
*Henrique de Aguiar Fernandes - 43667104* <br>
*Luiz Eduardo dos Reis - 42973759* <br>



## Sistema 💻
O Japan English Delivery é um sistema de gerenciamento de pedidos desenvolvido em Java. O objetivo principal deste projeto é processar o fluxo completo de um cliente em um Ponto de Venda (PDV) — desde a visualização do cardápio e montagem do carrinho, até o cálculo financeiro e a finalização da compra, interagindo com o usuário através de uma interface.

A arquitetura do sistema foi desenhada com foco na aplicação prática dos quatro pilares da Programação Orientada a Objetos (POO). O encapsulamento garante a segurança dos dados e das regras de negócio através de validações nos atributos das classes. A herança e a abstração foram utilizadas para organizar de forma lógica o nosso catálogo. Já o polimorfismo flexibiliza a aplicação, permitindo tratar dinamicamente diferentes formas de pagamento e cálculos do sistema.

## Funcionalidades Principais ⚙️
1. **Gestão de Clientes:** Cadastro com validação de dados (CPF, Telefone, Endereço).
2. **Exibição de Cardápio:** Listagem de pratos principais, acompanhamentos e bebidas.
3. **Checkout e Pagamento:** Cálculo do total do pedido.

## Estrutura de Classes 🏗️
* `Main`: Dar inicio ao sistema, exibir o menu interativo no console e conectar as interações do usuário às demais classes.
* `Model`: Ela é uma base que padroniza e facilita as operações de banco de dados nas outras classes do sistema.
* `Endereço`: Responsável por armazenar e gerenciar os dados de um endereço, além de realizar sua integração com o banco de dados.
* `Veiculo`: Gerencia o armazenamento e também organiza os dados de um veículo, como placa, tipo, marca e entregador associado.
*  `Entregador`: Armazenar as informações básicas como nome, CPF, telefone e status.
* `Database`: Ele é o principal responsável por estabelecer uma conexão com um banco de dados.
* `Cliente`: Responsável por gerenciar os dados e informações do usuário.
* `IUsuario`: Define as operações básicas de interação do usuário no sistema.
* `UserCliente`: Ela controla as ações que o cliente pode fazer no sistema do delivery.
* `UserEntregador`: Representa a área de interação do entregador no sistema.
* `UserRestaurante`: Responsável por controlar as ações do restaurante dentro do sistems.

