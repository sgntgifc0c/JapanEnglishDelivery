# Japan English Delivery - Sistema de Gerenciamento de Delivery de Comida

**Integrantes do Grupo:**
Fernanda Akemi Martins Sanpei - 43931251
Gabriel Romão da Silva - 41881184
Geziel de Andrade - 43608906
Henrique de Aguiar Fernandes - 43667104
Luiz Eduardo dos Reis - 42973759
Pedro Viana da Silva - 43477089


## Objetivo do Sistema
O Japan English Delivery é um sistema de gerenciamento de pedidos desenvolvido em Java, criado para simular o backend de um restaurante híbrido que une as culinárias japonesa e americana. O objetivo central deste projeto é processar o fluxo completo de um cliente em um Ponto de Venda (PDV) — desde a visualização do cardápio e montagem do carrinho, até o cálculo financeiro (com descontos progressivos) e a finalização da compra, interagindo com o usuário através de uma interface de linha de comando.

A arquitetura do sistema foi desenhada com foco na aplicação prática dos quatro pilares da Programação Orientada a Objetos (POO). O encapsulamento garante a segurança dos dados e das regras de negócio através de validações nos atributos das classes. A herança e a abstração foram utilizadas para organizar de forma lógica o nosso catálogo (criando, por exemplo, uma superclasse genérica para derivar Pratos, Bebidas e Sobremesas). Já o polimorfismo flexibiliza a aplicação, permitindo tratar dinamicamente diferentes formas de pagamento e cálculos do sistema.

Nesta etapa do desenvolvimento, todos os dados de produtos, clientes e pedidos são gerenciados diretamente em memória durante a execução do programa. O projeto prioriza boas práticas de engenharia de software, apresentando um código limpo, com nomenclaturas padronizadas, responsabilidades bem divididas entre as classes e documentação estruturada utilizando Javadoc, resultando em um sistema organizado e de fácil manutenção.

## Funcionalidades Principais
1. **Gestão de Clientes:** Cadastro com validação de dados (CPF, Telefone, Endereço).
2. **Exibição de Cardápio:** Listagem de pratos principais, acompanhamentos e bebidas.
3. **Carrinho de Compras:** Adição de itens com suporte a observações customizadas.
4. **Checkout e Pagamento:** Cálculo do total do pedido aplicando regras de negócio de descontos progressivos.
5. **Sistema de Avaliações:** Registro de feedback dos clientes (notas de 1 a 5 e comentários).

## Estrutura de Classes Planejada
* `Cliente`: Gerencia os dados do usuário.
* `Produto`: Representa os itens do cardápio (Sushis, Burgers, etc).
* `ItemPedido`: Associa um produto a uma quantidade e observações do cliente no carrinho.
* `Pedido`: Consolida os itens, vincula ao cliente e gerencia o total e o status.
* `Avaliacao`: Armazena o feedback pós-pedido.
* `Main`: Ponto de entrada do sistema, responsável por exibir o menu interativo no console e integrar as interações do usuário com as demais classes.

## Regra de Negócio Complexa
**Cálculo de Valor Total do Pedido com Descontos Progressivos:** O sistema aplica automaticamente um desconto no carrinho no momento do fechamento. Pedidos com valor superior a R$ 100,00 recebem 5% de desconto. Pedidos acima de R$ 200,00 recebem o teto de 15% de desconto.
