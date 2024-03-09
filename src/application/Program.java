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
                editarProduto();
                menuDaAplicacao();
            case 4:
                removerProduto();
                menuDaAplicacao();
            default:
                System.out.println("Opção Inválida. Tente novamente.");
                menuDaAplicacao();
                break;

        }
    }
    //Função para cadastrar um produto
    public static void cadastrarProduto(){
        System.out.println("CADASTRO DE PRODUTO:\n");

        System.out.print("Codigo de identificação do produto:");
        String pCodId = get.nextLine();

        System.out.print("Descrição do produto:");
        String pDescricao = get.nextLine();

        System.out.print("Preço do produto:");
        Double pPreco = get.nextDouble();
        get.nextLine();

        System.out.print("Quantidade do produto:");
        Integer pQntd = get.nextInt();
        get.nextLine();

        produtos.add(new Produto(pCodId,pDescricao, pPreco, pQntd));

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

    // Entrada e saída de produtos
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
                    System.out.print("Insira qntd de saída:");
                    produtoEncontrado.saidaDeEstoque(get.nextInt());
                    get.nextLine();
                    System.out.println(produtoEncontrado);
                    break;
                default:
                    break;
            }
        }
        else {
            System.out.println("Produto não Encontrado.");
        }
    }

    // Função para editar produtos
    public static void editarProduto(){
        Produto produtoEncontrado = buscarIdProduto();

        if (produtoEncontrado != null){
            int opEditar = 0;
            do {
                System.out.println("Selecione o elemento para editar:\n");
                System.out.println("(1) - Código");
                System.out.println("(2) - Descrição");
                System.out.println("(3) - Preço");
                System.out.println();
                System.out.println(">>:");
                opEditar = get.nextInt();
                get.nextLine();
            }while (opEditar <= 0 || opEditar >= 4);

            switch (opEditar){
                case 1:
                    System.out.println("Editar Código "+ produtoEncontrado.getCodigoDeId());
                    System.out.print("Novo Código:");
                    produtoEncontrado.setCodigoDeId(get.nextLine());
                    break;
                case 2:
                    System.out.println("Editar Descrição "+ produtoEncontrado.getDescricao());
                    System.out.print("Nova Descrição:");
                    produtoEncontrado.setDescricao(get.nextLine());
                    break;
                case 3:
                    System.out.println("Editar Preço "+ produtoEncontrado.getPreco());
                    System.out.print("Novo Preço:");
                    produtoEncontrado.setPreco(get.nextDouble());
                    get.nextLine();
                    break;
            }
        }
        else {
            System.out.println("Produto não Encontrado.");
        }
    }

    //Função pra remover produtos
    public static void removerProduto(){
        Produto produtoEncontrado = buscarIdProduto();

        if (produtoEncontrado != null){
            System.out.println("Produto Código '"+produtoEncontrado.getCodigoDeId() + "' Removido.");
            produtos.remove(produtoEncontrado);
        }
        else {
            System.out.println("Produto Inexistente.");
        }
    }
}

