package application;

import model.entities.Produto;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    static Scanner get = new Scanner(System.in);
    static ArrayList<Produto> produtos;

    public static void main(String[] args) {
        try {
            produtos = new ArrayList<>();
            menuDaAplicacao();
        } catch (InputMismatchException erro) {
            System.out.println("Erro: Insira uma entrada válida do tipo inteiro.");
        }   catch (RuntimeException erro){
            System.out.println("Erro: ParseException");
        }
    }

    // Menu Da Aplicação De Gerenciamento De Produtos
    public static void menuDaAplicacao(){
        System.out.println("  _____          _____   \n" +
                " / ____|   /\\   |  __ \\  \n" +
                "| |       /  \\  | |  | | \n" +
                "| |      / /\\ \\ | |  | | \n" +
                "| |____ / ____ \\| |__| | \n" +
                " \\_____/_/    \\_\\_____(_)\n");

        System.out.println("(1) - Cadastrar Produto");
        System.out.println("(2) - Entrada & Saida de Produto");
        System.out.println("(3) - Editar Produto");
        System.out.println("(4) - Remover Produto");
        System.out.println("\n(0) - Sair");
        System.out.print("\n>>:");

        int opcaoMenu = get.nextInt();
        get.nextLine();

        switch (opcaoMenu) {
            case 0:
                System.exit(0);
                break;
            case 1:
                cadastrarProduto();
                menuDaAplicacao();
                break;
            case 2:
                entradaSaidaProduto();
                menuDaAplicacao();
            case 3:
            case 4:
            default:
                System.out.println("Opção Inválida. Tente novamente.");
                menuDaAplicacao();
                break;

        }
    }

    public static void cadastrarProduto(){
        System.out.println("CADASTRO DE PRODUTO:\n");

        System.out.print("Codigo De Identificação do produto:");
        String pCodId = get.nextLine();

        System.out.print("Nome Do Produto:");
        String pNome = get.nextLine();

        System.out.print("Preço do Produto:");
        Double pPreco = get.nextDouble();
        get.nextLine();

        System.out.print("Quantidade do Produto:");
        Integer pQntd = get.nextInt();
        get.nextLine();

        produtos.add(new Produto(pCodId,pNome, pPreco, pQntd));

        System.out.println(produtos.get(produtos.size()-1));
    }

    // Métodos Utiliátios

    // Busca Por ID
    public static Produto buscarIdProduto(){
        System.out.print("Informe o código do produto:");
        String pCodId = get.nextLine();

        for (Produto produto: produtos ) {
            if (pCodId.equals(produto.getCodigoDeId())){
                    return produto;
            }
        }
        return null;
    }


    public static void entradaSaidaProduto(){
        Produto produtoEncontrado = buscarIdProduto();

        if (produtoEncontrado != null){
            int opEnSa = 0;
            do {
                System.out.println("Operação que deseja realizar:\n");
                System.out.println("(1) - Entrada");
                System.out.println("(2) - Saida");
                System.out.println();
                System.out.print(">>:");
                opEnSa = get.nextInt();
                get.nextLine();
            }while(opEnSa <= 0 || opEnSa >= 3);

            switch (opEnSa){
                case 1:
                    System.out.print("Insira qntd de entrada:");
                    produtoEncontrado.entradaDeEstoque(get.nextInt());
                    get.nextLine();
                    System.out.println(produtoEncontrado);
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }
    }
}

