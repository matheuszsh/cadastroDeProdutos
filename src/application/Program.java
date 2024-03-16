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
            System.out.println("Erro: RuntimeException");
        }
    }
    // Aperte enter
    public static void aperteEnter(){
        System.out.println("Aperte Enter para retornar ao menu.");
        get.nextLine();
        for (int i =0; i < 10; i++){
            System.out.println(">\n");
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
        System.out.println("(2) - Buscar Produto");
        System.out.println("(3) - Entrada & Saida de Produto");
        System.out.println("(4) - Editar Produto");
        System.out.println("(5) - Logs de produto");
        System.out.println("(6) - Remover Produto");
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
                System.out.println(buscarIdProduto());
                aperteEnter();
                menuDaAplicacao();
            case 3:
                entradaSaidaProduto();
                menuDaAplicacao();
            case 4:
                editarProduto();
                menuDaAplicacao();
            case 5:
                logsDeProduto();
                menuDaAplicacao();
            case 6:
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

        System.out.print("Código do produto:");
        String pCodId = get.nextLine();

        if (buscarIdProduto(pCodId) == null) {

            System.out.print("Descrição do produto:");
            String pDescricao = get.nextLine();

            System.out.print("Preço do produto:");
            Double pPreco = get.nextDouble();
            get.nextLine();

            System.out.print("Quantidade do produto:");
            Integer pQntd = get.nextInt();
            get.nextLine();

            produtos.add(new Produto(pCodId, pDescricao, pPreco, pQntd));
            System.out.println(produtos.get(produtos.size() - 1));
        }
        else {
            System.out.println("Código já cadastrado em outro produto.");
        }

        aperteEnter();
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

    public static String buscarIdProduto(String pCodId){
        for (Produto produto: produtos ) {
            if (pCodId.equals(produto.getCodigoDeId())){
                return produto.getCodigoDeId();
            }
        }
        return null;
    }

    // Entrada e saída de produtos
    public static void entradaSaidaProduto(){
        Produto produtoEncontrado = buscarIdProduto();

        if (produtoEncontrado != null){
            System.out.println(produtoEncontrado);
            int opEnSa;
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

        aperteEnter();
    }

    // Função para editar produtos
    public static void editarProduto(){
        Produto produtoEncontrado = buscarIdProduto();

        if (produtoEncontrado != null){
            int opEditar;
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
                    String novoCodProduto;
                    do {
                        System.out.print("Novo Código:");
                        novoCodProduto = get.nextLine();
                    }while(buscarIdProduto(novoCodProduto) != null);
                    produtoEncontrado.setCodigoDeId(novoCodProduto);
                    System.out.println("Código Alterado.");
                    break;
                case 2:
                    System.out.println("Editar Descrição "+ produtoEncontrado.getDescricao());
                    System.out.print("Nova Descrição:");
                    produtoEncontrado.setDescricao(get.nextLine());
                    System.out.println("Descrição Alterada.");
                    break;
                case 3:
                    System.out.println("Editar Preço "+ produtoEncontrado.getPreco());
                    System.out.print("Novo Preço:");
                    produtoEncontrado.setPreco(get.nextDouble());
                    get.nextLine();
                    System.out.println("Preço Alterado.");
                    break;
            }


        }
        else {
            System.out.println("Produto não Encontrado.");
        }
        aperteEnter();
    }
    public static void logsDeProduto(){
        Produto produtoEncontrado = buscarIdProduto();

        if (produtoEncontrado != null){
            System.out.print("Selecione o tipo de Log:\n\n(1) - Entradas & Saidas\n(2) - Edições\n\n>>:");
            int logOpcao = get.nextInt();
            get.nextLine();

            switch (logOpcao){
                case 1, 2:
                    produtoEncontrado.mostrarLogProduto(logOpcao);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        else {
            System.out.println("Produto Inexistente.");
        }

        aperteEnter();
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

        aperteEnter();
    }
}

