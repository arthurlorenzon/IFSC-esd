package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercicio3 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, forneça o nome do arquivo como argumento.");
            return;
        }

        String nomeArquivo = args[0];
        ListaSimples<String> palavrasLidas = new ListaSimples<>();
        ListaSimples<Integer> posicoes = new ListaSimples<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                processaLinha(linha, palavrasLidas, posicoes);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private static void processaLinha(String linha, ListaSimples<String> palavrasLidas, ListaSimples<Integer> posicoes) {
        StringBuilder resultado = new StringBuilder();
        String[] palavras = linha.split("\\s+");

        for (String palavra : palavras) {
            int indice = palavrasLidas.procura(palavra);

            if (indice == -1) {
                palavrasLidas.adiciona(palavra);
                resultado.append(palavra).append(" ");
            } else {
                int posicao = posicoes.procura(indice);
                if (posicao == -1) {
                    posicao = palavrasLidas.comprimento();
                    posicoes.adiciona(posicao);
                }
                resultado.append(posicao).append(" ");
            }
        }

        System.out.println(resultado.toString().trim());
    }
}