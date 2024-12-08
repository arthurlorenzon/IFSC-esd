package org.example;

public class Fila {
    Object[] area;
    Object[] areaExpandida;
    int inicio, fim, n;

    public Fila(int capacidade) {
        area = new Object[capacidade];
        inicio = 0;
        fim = 0;
        n = 0;
    }

    public boolean estaCheia() {
        return n == area.length;
    }

    public boolean estaVazia() {
        return n == 0;
    }

    public void adiciona(Object valor) {
        if (! estaCheia()) {
            if (fim == area.length) {
                fim = 0;
            }
            area[fim] = valor;
            fim++;
            n++;
            return;
        }
        throw new IndexOutOfBoundsException("Fila está cheia");
    }

    public Object remove() {
        if (! estaVazia()) {
            Object elemento = area[inicio];
            area[inicio] = null;
            n--;
            if (inicio == area.length) {
                inicio = 0;
            }
            inicio = (inicio + 1) % area.length;
            return elemento;
        }
        throw new IndexOutOfBoundsException("Fila está vazia");
    }

    public Object frente() {
        if (! estaVazia()) {
            return area[inicio];
        }
        throw new IndexOutOfBoundsException("Fila está vazia");
    }

    public int length() {
        return n;
    }

    public void limpa() {
        for (int i = 0; i < area.length; i++) {
            area[i] = null;
        }
        inicio = 0;
        fim = 0;
        n = 0;
    }

    public void expande() {
        Object[] novo = new Object[area.length * 2];
        for (int j = 0; j < n; j++) {
            novo[j] = area[(inicio + j) % area.length];
        }
        area = novo;
        inicio = 0;
        fim = n;
    }
}