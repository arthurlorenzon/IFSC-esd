package org.example;

public class Deque {
    Object[] area;
    int inicio, fim;
    int n;

    public Deque() {
        area = new Object[10];
        this.inicio = 0;
        this.fim = 0;
        this.n = 0;
    }

    public void adiciona(Object valor) {
        if (estaCheia()) {
            expande();
        }
        area[fim] = valor;
        fim = (fim + 1) % area.length;
        n++;
    }

    public void insere(Object valor) {
        if (estaCheia()) {
            expande();
        }
        inicio = (inicio - 1 + area.length) % area.length;
        area[inicio] = valor;
        n++;
    }

    public void expande() {
        Object[] novo = new Object[2 * area.length];

        for (int j = 0; j < n; j++) {
            novo[j] = area[(inicio+j) % area.length];
        }

        area = novo;
        inicio = 0;
        fim = n;

    }

    public boolean estaCheia(){
        return n == area.length;
    }

    public boolean esta_vazia(){
        return n == 0;
    }

    public Object acessa_inicio() {
        if (!esta_vazia()) {
            return area[inicio];
        }
        throw new IndexOutOfBoundsException("fila vazia");
    }

    public Object acessa_final() {
        if (!esta_vazia()) {
            int ultimoIndice = (fim - 1 + area.length) % area.length; // Decrementa circularmente
            return area[ultimoIndice];
        }
        throw new IndexOutOfBoundsException("fila vazia");
    }

    public Object extrai_inicio() {
        if (!esta_vazia()) {
            Object valor = area[inicio];
            area[inicio] = null; // Limpa o slot
            inicio = (inicio + 1) % area.length; // Incrementa circularmente
            n--;
            return valor;
        }
        throw new IndexOutOfBoundsException("fila vazia");
    }

    public Object extrai_final() {
        if (!esta_vazia()) {
            fim = (fim - 1 + area.length) % area.length;
            Object valor = area[fim];
            area[fim] = null;
            n--;
            return valor;
        }
        throw new IndexOutOfBoundsException("fila vazia");
    }


    public Object acessa(int posicao) {
        if (posicao < 0 || posicao >= n) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
        }
        return area[(inicio + posicao) % area.length];
    }


    public int comprimento() {  return this.n; }

    public int capacidade() {
        return area.length;
    }

    public void limpa() {
        for (int i = 0; i < area.length; i++) {
            area[i] = null;
        }
    }

}
