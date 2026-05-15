# Japan English Delivery - Sistema de Gerenciamento de Delivery de Comida

**Integrantes do Grupo:**
<br> *Henrique de Aguiar Fernandes - 43667104* <br>
*Pedro Viana da Silva - 43477089* <br>
*Fernanda Akemi Martins Sanpei - 43931251* <br>
*Gabriel Romão da Silva - 41881184* <br>
*Geziel de Andrade - 43608906* <br>
*Luiz Eduardo dos Reis - 42973759* <br>



## Sistema 💻
O Japan English Delivery é um sistema de gerenciamento de pedidos desenvolvido em Java. O objetivo principal deste projeto é processar o fluxo completo de um cliente em um Ponto de Venda (PDV) — desde a visualização do cardápio e montagem do carrinho, até o cálculo financeiro e a finalização da compra, interagindo com o cliente através de uma interface.

Para os restaurantes, o sistema atua como uma plataforma de gestão de cardápio e pedidos. Cada restaurante cadastrado pode registrar e organizar seus produtos (pratos, bebidas e categorias), além de visualizar os pedidos recebidos. Dessa forma, o restaurante consegue gerenciar seu menu e acompanhar os pedidos feitos pelos clientes em tempo real dentro do sistema.

E para os entregadores, o encapsulamento garante a integridade dos dados, enquanto herança e abstração organizam o sistema. O polimorfismo adapta o comportamento, tornando o acompanhamento e atualização das entregas mais ágil. 

## Funcionalidades Principais ⚙️
1. **Gestão de Clientes:** Cadastro com validação de dados (CPF, Telefone, Endereço).
2. **Exibição de Cardápio:** Listagem de pratos principais, acompanhamentos e bebidas.
3. **Checkout e Pagamento:** Cálculo do total do pedido.

## Estrutura de Classes 🏗️
* `Main`: Dar inicio ao sistema, exibir o menu interativo no console e conectar as interações do usuário às demais classes.
* `Model`: Ela é uma base que padroniza e facilita as operações de banco de dados nas outras classes do sistema.
* `Endereço`: Responsável por armazenar e gerenciar os dados de um endereço, além de realizar sua integração com o banco de dados.
* `Veiculo`: Gerencia o armazenamento e também organiza os dados de um veículo, como placa, tipo, marca e entregador associado.
* `Restaurante`: Representa um restaurante cadastrado no sistema, sendo uma das entidades principais do modelo.
* `ItemPedido`: Representa um item dentro de um pedido (carrinho/compra) em um sistema de pedidos, funcionando como uma entidade de banco de dados.
* `Pedido`: Armazena o pedido feito por um cliente em um restaurante.
* `Produto`: Ela representa (prato/item do cardápio) de um restaurante dentro do sistema.
* `Entregador`: Armazena as informações básicas do entregador cadastrado como nome, CPF, telefone.
* `Database`: Ele é o principal responsável por estabelecer uma conexão com um banco de dados.
* `Cliente`: Responsável por gerenciar os dados e informações do usuário.
* `CMD`: Uma entidade para escrever no console de forma mais rapida.
* `IUsuario`: Define as operações básicas de interação do usuário no sistema.
* `UserCliente`: Ela controla as ações que o cliente pode fazer no sistema do delivery.
* `UserEntregador`: Representa a área de interação do entregador no sistema.
* `UserRestaurante`: Responsável por controlar as ações do restaurante dentro do sistema.
