
import java.io.IOException;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import java.util.stream.Collectors;
import java.util.ArrayList;


import java.util.List;
import java.util.Map;
import java.util.IntSummaryStatistics;



public class App {
    public static void main(String[] args) throws InterruptedException, IOException {
        int opcao;
        String buscap;
        int qt;

        Scanner in = new Scanner(System.in);
        List<produto> merc = new ArrayList<>();
        List<venda> vd = new ArrayList<>();

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        do {
            System.out.println("\n****\nMENU\n****\n");
            System.out.println("1 - Incluir produto");
            System.out.println("2 - Consultar produto ");
            System.out.println("3 - Listagem de produtos");
            System.out.println("4 - Vendas por período");
            System.out.println("5 - Realizar venda");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            opcao = in.nextInt();
            in.nextLine(); // Tira o ENTER que ficou na entrada na instrução anterior

            if (opcao == 1) {
                System.out.println(
                        "A T E N Ç Â O\nprimeiro: digite o codigo do produto\nsegundo: nome\nterceiro: preço\nquarto: A quantidade total do produto");
                produto p = new produto(in.nextLine(), in.nextLine(), in.nextInt(), in.nextInt());

                merc.add(p);
                voltarMenu(in);
            } else if (opcao == 2) {
                if (merc.isEmpty()) {
                    System.out.println("\nNão há produtos para serem consultados.");
                    voltarMenu(in);
                    continue;
                }
                System.out.println("Digite o codigo do produto: ");
                buscap = in.nextLine();
                for (produto p : merc) {
                    if (buscap.equals(p.getCodigo())) {
                        System.out.println("\nnome do produto: " + p.getNome() + " - preço: " + p.getPreco() +
                                " - quantidade total: " + p.getQuantidadetotal());
                    }
                    if (buscap != p.getCodigo()) {
                        System.out.println("CODIGO NÃO CADASTRADO!");
                    }

                    voltarMenu(in);
                }
            } else if (opcao == 3) {
                if (merc.isEmpty()) {
                    System.out.println("\nNão há produtos inclusos para exibir.");
                    voltarMenu(in);
                    continue;
                }
                for (produto p : merc) {
                    System.out.println("Lista de produtos:\n");
                    System.out.printf("Codigo: %s - Nome: %s - Preço: %d - Quantidade total: %d\n", p.getCodigo(),
                            p.getNome(), p.getPreco(), p.getQuantidadetotal());

                }
                IntSummaryStatistics resumo = merc.stream().collect(Collectors.summarizingInt(produto::getPreco));
                resumo.getAverage();
                resumo.getMin();
                resumo.getMax();
                System.out.println("Valor médio: " + resumo.getAverage() + "\nmínimo: " + resumo.getMin() +
                        "\nmáximo: " + resumo.getMax());

                voltarMenu(in);
            } else if (opcao == 4) {
                System.out.println("Relatório de vendas:\n");
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");
                System.out.println("yy/MM/dd HH:mm:ss-> " + dtf2.format(LocalDateTime.now()));
                for (venda sell : vd) {

                    System.out.printf("Data da Compra: %s - Nome: %s - Preço: %d - Quantidade Vendida: %d - Valor unitário: %d - Valor Total: %d \n", sell.getData(),
                            sell.getNome(), sell.getPreco(), sell.getquantidadeVendida(), sell.getPreco(), sell.getPreco()*sell.getquantidadeVendida());
                }
                //Rodapé
                Map<LocalDate, Double> valoresMedios = vd.stream()
                        .collect(Collectors.groupingBy(venda::getData, Collectors.averagingDouble(venda::getValorVenda)));

                valoresMedios.entrySet().forEach(
                        item -> System.out.printf("Data %s - Valor médio do dia: %.2f\n",
                                item.getKey().format(df), item.getValue()));

                voltarMenu(in);
            } else if (opcao == 5) {

                System.out.println("Digite o codigo do produto: ");
                buscap = in.nextLine();

                for (produto p : merc) {
                    if (buscap.equals(p.getCodigo())) {

                        System.out.println(
                                "Verifique abaixo as informações do produto selecionado: ");
                        System.out.printf(
                                "Nome: %s - Preço: %d\n", p.getNome(), p.getPreco());
                        System.out.printf("Entre com a quantidade do produto desejada: ");
                        qt = in.nextInt();

                        venda sell = new venda(LocalDate.now(), p.getNome(), p.getPreco(), qt);

                        if (qt <= p.getQuantidadetotal()) { // Verifica se tem Produto no Estoque
                            vd.add(sell);
                            p.setQuantidadetotal(p.getQuantidadetotal() - qt); // Diminuindo do estoque
                            System.out.println("Venda Realizada com sucesso!");
                            voltarMenu(in);
                            continue;
                            // venda vd = new produto(vd.setCodigo(), in.nextLine(), in.nextInt(),
                            // in.nextInt());
                            // merc.add(vd);
                        } else {
                            System.out.println("QUANTIA DIGITADA MAIOR DO QUE A QUANTIA DISPONIVEL! COMPRA CANCELADA!!");
                            voltarMenu(in);
                            continue;
                        }
                        
                    }
                    // if (buscap != p.getCodigo()) {
                    // System.out.println("CODIGO NÃO CADASTRADO!");
                    // voltarMenu(in);
                    // continue;
                    // }

                }
            } else if (opcao != 0) {
                System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);

        System.out.println("Fim do programa!");

        in.close();
    }

    private static void voltarMenu(Scanner in) throws InterruptedException, IOException {
        System.out.println("\nPressione ENTER para voltar ao menu.");
        in.nextLine();

        // Limpa toda a tela, deixando novamente apenas o menu
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            System.out.print("\033[H\033[2J");

        System.out.flush();
    }
}