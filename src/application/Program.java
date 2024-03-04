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

            case 1:
            case 2:
            case 3:
            case 4:
            default:
                System.out.println("Opção Inválida. Tente novamente.");
                menuDaAplicacao();
                break;

        }
    }
}

