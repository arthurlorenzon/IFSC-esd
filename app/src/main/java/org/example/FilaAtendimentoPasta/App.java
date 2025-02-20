package org.example.FilaAtendimentoPasta;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FilaAtendimentoUsandoArvore fila = new FilaAtendimentoUsandoArvore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Digite uma opção (A-F para gerar senha, P para atender, S para sair): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.length() == 1) {
                char opcao = input.charAt(0);

                if (opcao >= 'A' && opcao <= 'F') {
                    fila.adicionarSenha(opcao); // Gera uma nova senha
                } else if (opcao == 'P') {
                    fila.atenderProximo(); // Atende o próximo cliente
                } else if (opcao == 'S') {
                    fila.encerrar(); // Encerra o sistema
                } else {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }
}