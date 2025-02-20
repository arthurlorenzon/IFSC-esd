package org.example;

import esd.ListaSequencial;

public class Main {
    public static void main(String[] args) {
        // Criando uma tabela hash
        TabHash<Integer, String> tabelaHash = new TabHash<>(chave -> chave % 31);

        // Teste 1: Tabela hash vazia
        ListaSequencial chavesVazias = ListarChaves.thash_chaves(tabelaHash);
        System.out.println("Chaves na tabela hash vazia: " + chavesVazias.comprimento()); // Deve imprimir 0

        // Teste 2: Tabela hash com algumas chaves
        tabelaHash.adiciona(1, "Valor1");
        tabelaHash.adiciona(2, "Valor2");
        tabelaHash.adiciona(3, "Valor3");
        tabelaHash.adiciona(4, "Valor4");
        tabelaHash.adiciona(5, "Valor5");
        tabelaHash.adiciona(6, "Valor6");
        tabelaHash.adiciona(7, "Valor7");
        tabelaHash.adiciona(8, "Valor8");
        tabelaHash.adiciona(9, "Valor9");
        tabelaHash.adiciona(10, "Valor10");

        ListaSequencial chaves = ListarChaves.thash_chaves(tabelaHash);
        System.out.println("Chaves na tabela hash com elementos: " + chaves.comprimento()); // Deve imprimir 10

        // Exibindo as chaves
        for (int i = 0; i < chaves.comprimento(); i++) {
            System.out.println("Chave " + (i + 1) + ": " + chaves.obtem(i));
        }
    }
}