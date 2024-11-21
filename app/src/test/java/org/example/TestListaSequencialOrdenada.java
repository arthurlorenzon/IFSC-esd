//package org.example;
//
//import org.example.ListaSequencial;
//import org.example.ListaSequencialOrdenada;
//import org.example.ListaSequencialSimples;
//import org.junit.jupiter.api.DisplayName;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.io.*;
//import java.security.InvalidParameterException;
//import java.util.Random;
//
//class TestListaSequencialOrdenada {
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa criação de lista: deve estar vazia ao ser criada")
//    void criaListaSequencialOrdenada() throws InterruptedException, IOException {
//        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//        assert(q.getClass().getSuperclass().equals(ListaSequencial.class));
//        assert(q.esta_vazia());
//        assert(q.comprimento() == 0);
//        assert(q.capacidade() > 0);
//    }
//
//    // testa se uma lista está ordenada
//    boolean esta_ordenada(ListaSequencialOrdenada<Integer> q) {
//        Integer prev = (Integer)q.obtem(0);
//        for (int j=0; j < q.comprimento(); j++) {
//            var val = (Integer)q.obtem(j);
//            if (val.compareTo(prev) < 0) {
//                return false;
//            }
//            prev = val;
//        }
//
//        return true;
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa anexar e obter valores")
//    void adiciona() throws InterruptedException, IOException {
//        // cria uma lista de Integer
//        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//        Random rand = new Random();
//        for (int j=0; j < q.capacidade(); j++) {
//            // sorteia um número entre 0 e 100
//            var val = Integer.valueOf(rand.nextInt(100));
//            // adiciona o número à lista e confere se seu comprimento foi incrementado
//            q.adiciona(val);
//            assert(q.comprimento() == j+1);
//        }
//
//        // ao final, verifica se lista está ordenada
//        assert(esta_ordenada(q));
//
//        assert(! q.esta_vazia());
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa procurar valores na lista")
//    void procura() throws InterruptedException, IOException {
//        ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//        // procura em lista vazia
//        assert(q.procura(Integer.valueOf(3)) == -1);
//
//        // gera uma lista com valores conhecidos
//        for (int j=0; j < q.capacidade(); j++) {
//            var val = Integer.valueOf(j);
//            q.adiciona(val);
//        }
//
//        // procura cada valor conhecido na lista
//        for (int j=0; j < q.capacidade(); j++) {
//            var val = Integer.valueOf(j);
//            int indice = (Integer)q.procura(j);
//            assert(indice == j);
//        }
//
//        // procura por um valor que não foi adicionado à lista
//        assert(q.procura(Integer.valueOf(1001)) == -1);
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa substituir valores")
//    void substitui() throws InterruptedException, IOException {
//        for (int k=0; k < 5; k++) {
//            ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//            // gera uma lista com valores conhecidos, os quais são múltiplos de 2
//            for (int j = 0; j < q.capacidade(); j++) {
//                var val = Integer.valueOf(j * 2);
//                q.adiciona(val);
//            }
//
//            // substitui o valor na posição k pelo valor atual + 1
//            Integer val = (Integer) q.obtem(k) + 1;
//            q.substitui(k, val);
//            assert (esta_ordenada(q));
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa substituir valor em posição inválida")
//    void substitui_invalido() throws InterruptedException, IOException {
//        for (int k=0; k < 5; k++) {
//            ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//            // gera uma lista com valores conhecidos, os quais são múltiplos de 2
//            for (int j = 0; j < q.capacidade(); j++) {
//                var val = Integer.valueOf(j * 2);
//                q.adiciona(val);
//            }
//
//            // substitui o valor na posição k pelo valor atual + 3 ... ele é inválido para a posição k !
//            final Integer val = (Integer) q.obtem(k) + 3;
//            final int pos = k;
//            assertThrows(InvalidParameterException.class, () -> {q.substitui(pos, val);});
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa inserir valores")
//    void insere() throws InterruptedException, IOException {
//        for (int k=0; k < 5; k++) {
//            ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//            // gera uma lista com valores conhecidos, os quais são múltiplos de 2
//            for (int j = 0; j < q.capacidade(); j++) {
//                var val = Integer.valueOf(j * 2);
//                q.adiciona(val);
//            }
//
//            // insere o valor na posição k+1 por 1 + "valor da posição k"
//            Integer val = (Integer) q.obtem(k) + 1;
//            q.insere(k+1, val);
//            assert (esta_ordenada(q));
//        }
//    }
//
//    @org.junit.jupiter.api.Test
//    @DisplayName("Testa inserir valor em posição inválida")
//    void insere_invalido() throws InterruptedException, IOException {
//        for (int k=0; k < 5; k++) {
//            ListaSequencialOrdenada<Integer> q = new ListaSequencialOrdenada<>(Integer::compareTo);
//
//            // gera uma lista com valores conhecidos, os quais são múltiplos de 2
//            for (int j = 0; j < q.capacidade(); j++) {
//                var val = Integer.valueOf(j * 2);
//                q.adiciona(val);
//            }
//
//            // insere o valor na posição k+1 por 3 + "valor da posição k" ... valor inválido para posição k !
//            final Integer val = (Integer) q.obtem(k) + 3;
//            final int pos = k+1;
//            assertThrows(InvalidParameterException.class, () -> {q.insere(pos, val);});
//        }
//    }
//
//}
