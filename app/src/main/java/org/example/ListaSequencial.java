package org.example;

public class ListaSequencial extends ListaSequencialSimples {

    public void insere(int indice, Object valor) {
        if (indice < 0 || indice > len) {
            throw new IndexOutOfBoundsException("Indice inválido!");
        }

        if (len >= area.length) {
            throw new ArrayIndexOutOfBoundsException("Lista cheia!");
        }

        for (int i = len; i > indice; i--) {
            area[i] = area[i - 1];
        }

        area[indice] = valor;
        len++;
    }

    public void remove(int indice) {
        if (esta_vazia()) {
            throw new IndexOutOfBoundsException("A lista esta vazia");
        }

        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Indice invalido");
        }

        for (int i = indice; i < len - 1; i++) {
            area[i] = area[i + 1];
        }
        len--;
        area[len] = null;
    }

    public void substitui(int indice, Object value) {
        if (indice < 0 || indice >= len) {
            throw new IndexOutOfBoundsException("Indice inválido!");
        }
        area[indice] = value;
    }


}
