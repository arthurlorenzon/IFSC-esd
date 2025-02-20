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

//    Uma função útil para a tabela hash gera uma lista das chaves existentes em uma tabela. Essa
//    função tem esta declaração em thash.h:
//// Retorna uma lista contendo as chaves existentes na tabela hash "tab"
//    lista_linear_t * thash_chaves(thash_t * tab);
//    Acrescente essa função a sua tabela hash, e escreva um programa que mostre seu funcionamento
//    nestes casos:
//            1. Tabela hash vazia: a lista retornada deve também estar vazia
//2. Tabela hash com algumas chaves (pelo menos 10): a lista deve conter todas as chaves existentes na tabela
}