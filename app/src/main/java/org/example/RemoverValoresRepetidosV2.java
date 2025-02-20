package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RemoverValoresRepetidosV2 {

    public static void main(String[] args) {
        // Verifica se o nome do arquivo foi passado como argumento
        if (args.length == 0) {
            System.out.println("Uso: java RemoveDuplicatasMatriculas <arquivo>");
            return;
        }

        String nomeArquivo = args[0]; // Nome do arquivo de entrada
        APB<String> arvore = new APB<>(); // Árvore para armazenar matrículas únicas

        // Ler o arquivo e inserir as matrículas na árvore
        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                arvore.adiciona(linha.trim()); // Adiciona a matrícula na árvore
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // Obter as matrículas únicas e ordenadas
        ListaSequencial matriculasUnicas = arvore.emOrdem();

        // Imprimir as matrículas na saída padrão
        for (int i = 0; i < matriculasUnicas.comprimento(); i++) {
            System.out.println(matriculasUnicas.obtem(i));
        }
    }

//    Um arquivo contém números de matrícula de alunos de uma escola. Foi detectado que existem
//    números de matrícula repetidos nesse arquivo, e assim deseja-se removê-los. Ao final, o que se
//    espera é que o arquivo contenha somente números de matrícula únicos.
//    Escreva um programa que leia esse arquivo, e apresente na saída padrão os números de matrícula
//            (excluindo os números repetidos). O nome do arquivo é informado como primeiro argumento de
//    linha de comando. Seu programa deve apresentar esses números de forma ordenada, usando o
//    ordenamento usual para string.
//    Você pode testar seu programa com os arquivos contidos no arquivo compactado anexado a esta
//    questão. Ele contém três pares de arquivos (X é um número):
//            • arqX.txt: arquivo com matrículas repetidas
//• resX.txt: arquivo sem matrículas repetidas
}

