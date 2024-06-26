package application;

import model.entities.Produto;

import java.io.*;
import java.util.*;

public class Program {
    static Scanner get = new Scanner(System.in);
    static ArrayList<Produto> produtos;

    static String pathFilesCsv = "C:\\Users\\Matheus\\OneDrive\\Documentos\\EstudosTi\\Programação\\Java\\ExercicioJavaPOO\\cadastroDeProdutos\\src\\resources\\csvFiles";
    static String pathFilesTxt = "C:\\Users\\Matheus\\OneDrive\\Documentos\\EstudosTi\\Programação\\Java\\ExercicioJavaPOO\\cadastroDeProdutos\\src\\resources\\txtFiles";


    public static void main(String[] args) {
        try {
            Locale.setDefault(new Locale("US"));
            produtos = new ArrayList<>();
            menuDaAplicacao();
        } catch (InputMismatchException erro) {
            System.out.println("Erro: Insira uma entrada válida do tipo inteiro.");
        } catch (NullPointerException erro){
            System.out.println(erro);
        } catch (IndexOutOfBoundsException erro){
            System.out.println(erro);
        } catch (ClassCastException erro){
            System.out.println(erro);
        } catch (RuntimeException erro){
            System.out.println(erro);
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
                System.out.println(buscarIdProdutoNoArgs());
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

        System.out.print("Informe o código do produto:");
        String pCodId = get.nextLine();

        Produto produtoEncontrado = buscarIdProdutoWithArgs(pCodId);


        if (produtoEncontrado == null) {
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

            try (BufferedWriter bf = new BufferedWriter(new FileWriter(pathFilesCsv + "\\produtosCad.csv", true))){
                bf.write(pCodId+",");
                bf.write(pDescricao+",");
                bf.write(pPreco+",");
                bf.write(String.valueOf(pQntd));
                bf.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Código já cadastrado em outro produto.");
        }

        aperteEnter();
    }

    // Métodos Utiliátios

    // Busca Por ID
    public static Produto buscarIdProdutoWithArgs(String novoCodProduto){

        try(BufferedReader bf = new BufferedReader( new FileReader(pathFilesCsv + "\\produtosCad.csv"))){
            String line;
            while ((line = bf.readLine()) != null){
                String[] lineArray = line.split(",");
                if (lineArray.length > 0 && lineArray[0].trim().equals(novoCodProduto.trim())){
                    updateDataProduct(lineArray);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Produto produto: produtos ) {
            if (novoCodProduto.equals(produto.getCodigoDeId())){
                    return produto;
            }
        }
        return null;
    }

    public static Produto buscarIdProdutoNoArgs(){
        System.out.print("Informe o código do produto:");
        String pCodId = get.nextLine();

        try(BufferedReader bf = new BufferedReader( new FileReader(pathFilesCsv + "\\produtosCad.csv"))){
            String line;
            while ((line = bf.readLine()) != null){
                String[] lineArray = line.split(",");
                if (lineArray.length > 0 && lineArray[0].trim().equals(pCodId.trim())){
                    updateDataProduct(lineArray);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Produto produto: produtos ) {
            if (pCodId.equals(produto.getCodigoDeId())){
                return produto;
            }
        }
        return null;
    }


    // Entrada e saída de produtos
    public static void entradaSaidaProduto(){
        Produto produtoEncontrado = buscarIdProdutoNoArgs();

        String path = "cadastroDeProdutos/src/resources/csvFiles";

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

            int qntdMovimentacao = 0;

            System.out.print("Insira qntd de Entrada/Saida:");
            qntdMovimentacao = get.nextInt();
            get.nextLine();

            switch (opEnSa){
                case 1:
                    produtoEncontrado.entradaDeEstoque(qntdMovimentacao);
                    System.out.println(produtoEncontrado);

                    path += "/buy.csv";
                    break;
                case 2:
                    produtoEncontrado.saidaDeEstoque(qntdMovimentacao);
                    System.out.println(produtoEncontrado);

                    path += "/sell.csv";
                    break;
                default:
                    break;
            }

            try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
                bw.write(produtoEncontrado.getDescricao()+",");
                bw.write(produtoEncontrado.getPreco()+",");
                bw.write(qntdMovimentacao+"");
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (path.equals("cadastroDeProdutos/src/resources/csvFiles/buy.csv")){

                path = "cadastroDeProdutos/src/resources/csvFiles/summaries/summaryBuy.csv";

                try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
                    bw.write(produtoEncontrado.getDescricao()+",");
                    bw.write(String.format("%.2f", produtoEncontrado.montanteEmEstoque()));
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                path = "cadastroDeProdutos/src/resources/csvFiles/summaries/summarySell.csv";

                try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
                    bw.write(produtoEncontrado.getDescricao()+",");
                    bw.write(String.format("%.2f", produtoEncontrado.montanteEmEstoque()));
                    bw.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFilesTxt+"\\sellBuyLogs.txt", true))){
                bw.write(String.valueOf(produtoEncontrado.getLastLogMov()));
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Produto não Encontrado.");
        }

        aperteEnter();
    }

    // Função para editar produtos
    public static void editarProduto(){
        Produto produtoEncontrado = buscarIdProdutoNoArgs();

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

            ArrayList<String> lines = new ArrayList<String>();

            switch (opEditar){
                case 1:
                    System.out.println("Editar Código "+ produtoEncontrado.getCodigoDeId());
                    String novoCodProduto;
                    do {
                        System.out.print("Novo Código:");
                        novoCodProduto = get.nextLine();
                    }while(buscarIdProdutoWithArgs(novoCodProduto) != null);

                    try (BufferedReader br = new BufferedReader(new FileReader(pathFilesCsv+"\\produtosCad.csv"))){
                        String line;
                        while ((line = br.readLine()) != null){
                            if (line.contains(produtoEncontrado.getCodigoDeId())){
                                String[] arrayline = line.split(",");
                                arrayline[opEditar-1] = novoCodProduto.trim();

                                String newLine = String.join(",", arrayline);

                                lines.add(newLine);
                            }else {
                                lines.add(line);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    produtoEncontrado.gerarLogEdicoes(opEditar, novoCodProduto);
                    System.out.println("Código Alterado.");
                    break;
                case 2:
                    System.out.println("Editar Descrição "+ produtoEncontrado.getDescricao());
                    System.out.print("Nova Descrição:");
                    String novaDescricao = get.nextLine();

                    try (BufferedReader br = new BufferedReader(new FileReader(pathFilesCsv+"\\produtosCad.csv"))){
                        String line;
                        while ((line = br.readLine()) != null){
                            if (line.contains(produtoEncontrado.getCodigoDeId())){
                                String[] arrayline = line.split(",");
                                arrayline[opEditar-1] = novaDescricao.trim();

                                String newLine = String.join(",", arrayline);

                                lines.add(newLine);
                            }else {
                                lines.add(line);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFilesCsv+"\\ProtutosCad.csv", true))){

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    produtoEncontrado.gerarLogEdicoes(opEditar, novaDescricao);
                    System.out.println("Descrição Alterada.");
                    break;
                case 3:
                    System.out.println("Editar Preço "+ produtoEncontrado.getPreco());
                    System.out.print("Novo Preço:");
                    Double novoPreco = get.nextDouble();

                    try (BufferedReader br = new BufferedReader(new FileReader(pathFilesCsv+"\\produtosCad.csv"))){
                        String line;
                        while ((line = br.readLine()) != null){
                            if (line.contains(produtoEncontrado.getCodigoDeId())){
                                String[] arrayline = line.split(",");
                                arrayline[opEditar-1] = String.valueOf(novoPreco).trim();

                                String newLine = String.join(",", arrayline);

                                lines.add(newLine);
                            }else {
                                lines.add(line);
                            }
                        }
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    produtoEncontrado.gerarLogEdicoes(opEditar, novoPreco);
                    get.nextLine();
                    System.out.println("Preço Alterado.");
                    break;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFilesCsv+"\\produtosCad.csv"))){
                for (String l : lines){
                    bw.write(l);
                    bw.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFilesTxt+"\\editProductLog.txt",true))){
                bw.write(String.valueOf(produtoEncontrado.getLastLogEdit()));
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            System.out.println("Produto não Encontrado.");
        }
        aperteEnter();
    }
    public static void logsDeProduto(){
        Produto produtoEncontrado = buscarIdProdutoNoArgs();

        if (produtoEncontrado != null){
            System.out.print("Selecione o tipo de Log:\n\n(1) - Entradas & Saidas\n(2) - Edições\n\n>>:");
            int logOpcao = get.nextInt();
            get.nextLine();

            switch (logOpcao){
                case 1:
                    try(BufferedReader br = new BufferedReader( new FileReader(pathFilesTxt+"\\sellBuyLogs.txt"))) {
                        String line = br.readLine();
                        while (line != null) {
                            if (line.contains(produtoEncontrado.getCodigoDeId())){
                                System.out.println(line);
                            }
                            line = br.readLine();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } ;
                    break;
                case 2:
                    try(BufferedReader br = new BufferedReader( new FileReader(pathFilesTxt+"\\editProductLog.txt"))) {
                        String line = br.readLine();
                        while (line != null) {
                            if (line.contains(produtoEncontrado.getCodigoDeId())){
                                System.out.println(line);
                            }
                            line = br.readLine();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } ;
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
    public static void removerProduto() {
        Produto produtoEncontrado = buscarIdProdutoNoArgs();

        if (produtoEncontrado != null) {
            System.out.println("Produto Código '" + produtoEncontrado.getCodigoDeId() + "' Removido.");
            produtos.remove(produtoEncontrado);

            ArrayList<String> lines = new ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(pathFilesCsv + "\\produtosCad.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (!line.contains(produtoEncontrado.getCodigoDeId())) {
                        lines.add(line);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathFilesCsv + "\\produtosCad.csv"))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Produto Inexistente.");
        }

        aperteEnter();
    }

    //Funcção que carrega os dados a partir dos arquivos estabelecidos anterirormente na execução
    public static void updateDataProduct(String[] arrayProduto){
        Produto updateProduto = new Produto();

        updateProduto.setCodigoDeId(arrayProduto[0].trim());
        updateProduto.setDescricao(arrayProduto[1].trim());

        Double preco = Double.parseDouble(arrayProduto[2].trim());
        Integer qntd = Integer.parseInt(arrayProduto[3].trim());

        updateProduto.setPreco(preco);
        updateProduto.setQntd(qntd);

        produtos.add(updateProduto);
    };
}

