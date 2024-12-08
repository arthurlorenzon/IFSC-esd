package org.example;

import java.util.function.BiFunction;

public class ListaSequencialOrdenada<T> extends ListaSequencial {
    BiFunction<T, T, Integer> comparador;

    public ListaSequencialOrdenada(BiFunction<T, T, Integer> comparador) {
        this.comparador = comparador;
    }

    public void adiciona(Object value) {
    }

    public int procura(Object value) {

        return 0;
    }

    public void insere(int indice, Object value) {

    }

    public void substitui(int indice, Object value) {

    }
}
