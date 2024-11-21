package org.example;

public class Pilha {
    Object[] pilha;
    int topo = -1;
    final int def_capacidade = 10;

    public Pilha() {
        this.pilha = new Object[def_capacidade];
    }

    public void empilha (Object valor) {
        if (length() == pilha.length) {
            Object[] newPilha = new Object[2 * pilha.length];
            for (int i = 0; i < pilha.length; i++) {
                newPilha[i] = pilha[i];
            }
            pilha = newPilha;
        }
        pilha[++topo] = valor;
    }

    public Object desempilha() {
        if (! estaVazia()) {
            return pilha[topo] = pilha[topo--];
        }
        throw new IndexOutOfBoundsException("Pilha está vazia");
    }

    public Object topo() {
        return pilha[topo];
    }

    public Boolean estaVazia() {
        if (topo == -1) {
            return true;
        }
        return false;
    }

    public void limpa() {
        while (! estaVazia()) {
            desempilha();
        }
    }

    public int length() {
        return topo + 1;
    }

    public int capacidade() {
        return pilha.length;
    }
}
