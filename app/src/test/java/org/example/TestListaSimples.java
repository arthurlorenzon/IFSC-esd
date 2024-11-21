package org.example;

import org.example.ListaSimples;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;
import java.util.Random;

class TestListaSimples {

    final int cap = 11;

    @org.junit.jupiter.api.Test
    @DisplayName("Testa criação de lista: deve estar vazia ao ser criada")
    void criaListaSimples() throws InterruptedException, IOException {
        ListaSimples q = new ListaSimples();
        assert(q.esta_vazia());
        assert(q.comprimento() == 0);
    }

    ListaSimples gera_lista() {
        ListaSimples q = new ListaSimples();

        for (int j=0; j < cap; j++) {
            var val = Integer.valueOf(j);
            q.adiciona(val);
        }

        return q;
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa inserir valores no início")
    void insere() throws InterruptedException, IOException {
        ListaSimples q = new ListaSimples();

        // insere valores no início
        for (int j=0; j < cap; j++) {
            var val = Integer.valueOf(j);
            q.insere(0, val);
            assert(q.comprimento() == j+1);
        }
        // verifica se sequência de valores corresponde à inserções no início
        // a sequência deve estar em ordem inversa às inserções
        for (int j=cap; j > 0; j--) {
            var val = Integer.valueOf(cap - j);
            assert(val.equals(q.obtem(j-1)));
        }

        assert(! q.esta_vazia());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa inserir valores em posições aleatórias")
    void insere_aleatorio() throws InterruptedException, IOException {
        ListaSimples q = gera_lista();
        Random rand = new Random();

        // insere valores em posições aleatórias
        for (int j=0; j < cap/2; j++) {
            // sorteia uma posição para inserção
            int pos = rand.nextInt(q.comprimento()-1);
            // copia o valor que está nessa posição
            var old = q.obtem(pos);
            // insere um valor na posição
            var val = Integer.valueOf(j);
            q.insere(pos, val);
            // confere se o valor inserido está de fato na posição
            assert(val.equals(q.obtem(pos)));
            // confere se o valor anterior está uma posição à frente
            assert(old.equals(q.obtem(pos+1)));
        }
    }


    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover valores")
    void remove() throws InterruptedException, IOException {
        ListaSimples q = gera_lista();

        // remove valores do início
        for (int j=q.comprimento(); j > 1 ; j--) {
            assert(! q.esta_vazia());
            // copia o valor que estava na posição 1
            var val = (Integer)q.obtem(1);
            // remove o valor da posição 0
            q.remove(0);
            // obtém o valor atual da posição 0
            var val2 = (Integer)q.obtem(0);
            // verifica se o valor da posição 0 é o que estava na posição 1
            assert(val.equals(val2));
        }

        assert(q.comprimento() == 1);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa substituir valores")
    void substitui() throws InterruptedException, IOException {
        ListaSimples q = gera_lista();

        Random rand = new Random();
        for (int j=q.comprimento(); j > 1 ; j--) {
            assert(! q.esta_vazia());
            // sorteia uma posição para substituição
            int pos = rand.nextInt(q.comprimento()-1);
            // obtém o valor dessa posição, e soma 100 a ele para gerar um novo valor
            Integer val = (Integer)q.obtem(pos) + 100;
            // substitui o valor da posição pelo novo valor
            q.substitui(pos, val);
            // confere se ovalor atual na posição é o novo valor lá substituido
            var val2 = (Integer)q.obtem(pos);
            assert(val.equals(val2));
        }

    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa remover valor de posição inválida")
    void remove_invalido() throws InterruptedException, IOException {
        ListaSimples q = new ListaSimples();

        // remover de lista vazia
        assertThrows(RuntimeException.class, () -> {q.remove(0);});
        q.adiciona(Integer.valueOf(10));
        // remover de posição igual ao comprimento
        assertThrows(RuntimeException.class, () -> {q.remove(1);});
        // remover de posição maior que comprimento
        assertThrows(RuntimeException.class, () -> {q.remove(7);});
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa substituir valor de posição inválida")
    void substitui_invalido() throws InterruptedException, IOException {
        ListaSimples q = new ListaSimples();

        // substituir de lista vazia
        Integer val = Integer.valueOf(10);
        assertThrows(RuntimeException.class, () -> {q.substitui(0, val);});
        q.adiciona(Integer.valueOf(7));
        // substituir de posição igual ao comprimento
        assertThrows(RuntimeException.class, () -> {q.substitui(1, val);});
        // substituir de posição maior que comprimento
        assertThrows(RuntimeException.class, () -> {q.substitui(7, val);});
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Testa inverter a lista")
    void inverte() throws InterruptedException, IOException {
        ListaSimples q = gera_lista();

        q.inverte();

        // verifica se sequência de valores corresponde à inserções no início
        // a sequência deve estar em ordem inversa às inserções
        for (int j=cap; j > 0; j--) {
            var val = Integer.valueOf(cap - j);
            assert(val.equals(q.obtem(j-1)));
        }

        assert(! q.esta_vazia());
    }
}