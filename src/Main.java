import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import delivery.model.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        List<Produto> cardapio = new ArrayList<>();
        
        // --- PRATOS PRINCIPAIS ---
        cardapio.add(new Produto("Lamen", "Caldo a base de misso ou shoyu, macarrao, carne suina, ovo cozido, cebolinha e alga.", 38.00));
        cardapio.add(new Produto("Udon", "Macarrao grosso japones, caldo leve, legumes, proteina e cebolinha.", 36.00));
        cardapio.add(new Produto("Yakissoba", "Macarrao oriental, legumes, carne/frango e molho shoyu.", 32.00));
        cardapio.add(new Produto("Uramaki (8 un.)", "Arroz por fora, recheio de salmao ou kani, cream cheese e gergelim.", 28.00));
        cardapio.add(new Produto("Sashimi (10 un.)", "Fatias de peixe cru (salmao ou atum).", 42.00));
        cardapio.add(new Produto("Temaki", "Alga nori, arroz, salmao/kani, cream cheese e cebolinha.", 26.00));
        cardapio.add(new Produto("Temaki Frito", "Temaki empanado e frito com recheio de salmao e cream cheese.", 30.00));
        cardapio.add(new Produto("Temaki Grelhado", "Temaki com salmao grelhado, cream cheese e molho especial.", 32.00));
        cardapio.add(new Produto("Missoshiru", "Sopa de misso com tofu, cebolinha e alga.", 12.00));
        cardapio.add(new Produto("Hossomaki (8 un.)", "Arroz e alga com recheio simples (salmao, pepino ou kani).", 24.00));
        cardapio.add(new Produto("Hot Roll (8 un.)", "Sushi empanado e frito com salmao e cream cheese.", 30.00));
        cardapio.add(new Produto("Hamburguer", "Pao, carne bovina, queijo, alface, tomate e molho da casa.", 28.00));
        cardapio.add(new Produto("Mac and Cheese", "Macarrao com molho cremoso de queijos.", 25.00));
        cardapio.add(new Produto("Buffalo Wings (6 un.)", "Asinhas de frango com molho picante.", 30.00));
        cardapio.add(new Produto("Barbecue Ribs", "Costela suina ao molho barbecue.", 45.00));
        cardapio.add(new Produto("Hot Dog", "Pao, salsicha, molho, milho, batata palha e vinagrete.", 18.00));
        cardapio.add(new Produto("Fried Chicken", "Frango empanado e frito, crocante.", 28.00));
        cardapio.add(new Produto("Pulled Pork Sandwich", "Pao, carne suina desfiada, molho barbecue e salada.", 32.00));

        // --- ACOMPANHAMENTOS ---
        cardapio.add(new Produto("Gohan", "Arroz branco temperado japones.", 10.00));
        cardapio.add(new Produto("Shimeji na manteiga", "Cogumelos salteados com manteiga e shoyu.", 18.00));
        cardapio.add(new Produto("Sunomono", "Salada de pepino agridoce com gergelim.", 12.00));
        cardapio.add(new Produto("Salada verde", "Alface, tomate e molho.", 12.00));
        cardapio.add(new Produto("Batata frita", "Porcao de batatas crocantes.", 15.00));
        cardapio.add(new Produto("Onion rings", "Aneis de cebola empanados.", 16.00));
        cardapio.add(new Produto("Molhos extras", "Tare, shoyu ou maionese temperada.", 3.00));
        cardapio.add(new Produto("Guioza (4 un.)", "Pastel japones recheado com carne suina.", 18.00));
        cardapio.add(new Produto("Tempura de legumes", "Legumes empanados e fritos.", 20.00));

        // --- SOBREMESAS ---
        cardapio.add(new Produto("Mochi", "Bolinho de arroz com recheio doce.", 12.00));
        cardapio.add(new Produto("Dorayaki", "Panqueca japonesa com recheio.", 14.00));
        cardapio.add(new Produto("Tempura de sorvete", "Sorvete empanado e frito.", 18.00));
        cardapio.add(new Produto("Cheesecake", "Creme de queijo e calda de frutas vermelhas.", 20.00));
        cardapio.add(new Produto("Brownie", "Bolo de chocolate denso com calda.", 15.00));
        cardapio.add(new Produto("Petit gateau", "Bolinho cremoso com sorvete.", 22.00));
        cardapio.add(new Produto("Sorvete", "Bola de sorvete (sabores variados).", 10.00));
        cardapio.add(new Produto("Banana caramelizada", "Banana frita com calda de acucar.", 14.00));
        cardapio.add(new Produto("Churros", "Massa frita com doce de leite.", 12.00));

        // --- BEBIDAS ---
        cardapio.add(new Produto("Refrigerante", "Opcoes de latas geladas.", 7.00));
        cardapio.add(new Produto("Suco natural", "Suco feito na hora.", 10.00));
        cardapio.add(new Produto("Suco industrializado", "Opcoes em lata ou caixa.", 8.00));
        cardapio.add(new Produto("Agua", "Garrafa 500ml.", 5.00));
        cardapio.add(new Produto("Cha gelado", "Ice Tea gelado.", 8.00));
        cardapio.add(new Produto("Cha quente", "Cha tradicional.", 7.00));
        cardapio.add(new Produto("Cerveja", "Lata 350ml.", 12.00));
        cardapio.add(new Produto("Saque", "Dose tradicional.", 18.00));
        
        Cliente clienteAtual = null;
        Pedido pedidoAtual = null;
        List<Avaliacao> avaliacoes = new ArrayList<>();

        int opcao = 0;

        System.out.println("=====================================");
        System.out.println("   Bem-vindo ao Japan English Delivery");
        System.out.println("=====================================");

        do {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Ver Cardapio (" + cardapio.size() + " itens)");
            System.out.println("3. Adicionar Item ao Pedido");
            System.out.println("4. Finalizar e Pagar Pedido");
            System.out.println("5. Deixar Avaliacao");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opcao: ");
            
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); 

                try {
                    switch (opcao) {
                        case 1:
                            System.out.print("Nome: ");
                            String nome = scanner.nextLine();
                            System.out.print("CPF (min. 11 digitos): ");
                            String cpf = scanner.nextLine();
                            System.out.print("Telefone: ");
                            String tel = scanner.nextLine();
                            System.out.print("Endereco: ");
                            String end = scanner.nextLine();
                            
                            clienteAtual = new Cliente(nome, cpf, tel, end);
                            pedidoAtual = new Pedido(clienteAtual);
                            System.out.println("Cliente cadastrado e carrinho aberto com sucesso!");
                            break;

                        case 2:
                            System.out.println("\n--- CARDAPIO COMPLETO ---");
                            for (int i = 0; i < cardapio.size(); i++) {
                                System.out.printf("%02d. %s%n", (i + 1), cardapio.get(i));
                            }
                            break;

                        case 3:
                            if (pedidoAtual == null) {
                                System.out.println("Cadastre o cliente primeiro (Opcao 1).");
                                break;
                            }
                            System.out.print("Digite o numero do produto do cardapio (1-" + cardapio.size() + "): ");
                            int prodIdx = scanner.nextInt() - 1;
                            System.out.print("Quantidade: ");
                            int qtd = scanner.nextInt();
                            scanner.nextLine(); 
                            System.out.print("Observacao/Sabor (ou deixe em branco): ");
                            String obs = scanner.nextLine();

                            if (prodIdx >= 0 && prodIdx < cardapio.size()) {
                                Produto escolhido = cardapio.get(prodIdx);
                                ItemPedido novoItem = new ItemPedido(escolhido, qtd, obs);
                                pedidoAtual.adicionarItem(novoItem);
                                System.out.println(qtd + "x " + escolhido.getNome() + " adicionado ao carrinho!");
                            } else {
                                System.out.println("Produto nao encontrado.");
                            }
                            break;

                        case 4:
                            if (pedidoAtual == null || pedidoAtual.getItens().isEmpty()) {
                                System.out.println("Seu carrinho esta vazio ou cliente nao cadastrado.");
                            } else {
                                System.out.println("\n" + pedidoAtual.toString());
                                pedidoAtual.setStatus("Finalizado");
                                System.out.println("Pedido enviado para a cozinha!");
                                pedidoAtual = new Pedido(clienteAtual); 
                            }
                            break;
                        case 5:
                            if (clienteAtual == null) {
                                System.out.println("Cadastre o cliente primeiro (Opcao 1).");
                                break;
                            }
                            System.out.print("Nota de 1 a 5 estrelas: ");
                            int nota = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Comentario: ");
                            String comentario = scanner.nextLine();
                            
                            Avaliacao aval = new Avaliacao(clienteAtual, nota, comentario);
                            avaliacoes.add(aval);
                            System.out.println(aval.toString());
                            break;

                        case 6:
                            System.out.println("Encerrando o sistema. Ate logo!");
                            break;

                        default:
                            System.out.println("Opcao invalida.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Erro de Validacao: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            } else {
                System.out.println("Por favor, digite apenas numeros.");
                scanner.nextLine(); 
                opcao = 0;
            }

        } while (opcao != 6);

        scanner.close();
    }
}