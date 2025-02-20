package org.example;

public class APB <T extends Comparable> {
    class NodoAPB {
        T valor;
        NodoAPB esq = null, dir = null, pai = null;

        NodoAPB(T val, NodoAPB nodo_pai) {
            valor = val;
            pai = nodo_pai;
        }
    }

    NodoAPB raiz = null;

    public void adiciona(T val) {
        if (this.raiz == null) {
            this.raiz = new NodoAPB(val, null);
            return;
        }

        NodoAPB atual = this.raiz;
        NodoAPB novoNodo = null;

        while (atual != null) {
            int compara = val.compareTo(atual.valor);

            if (compara < 0) {
                if (atual.esq == null) {
                    novoNodo = new NodoAPB(val, atual);
                    atual.esq = novoNodo;
                    return;
                }
                atual = atual.esq;
            } else if (compara > 0) {
                if (atual.dir == null) {
                    novoNodo = new NodoAPB(val, atual);
                    atual.dir = novoNodo;
                    return;
                }
                atual = atual.dir;
            } else {
                return;
            }
        }
    }

    public void remove(T val) {
        raiz = remove(raiz, val);
    }

    public NodoAPB remove(NodoAPB raiz, T val) {
        if (raiz == null) return null;
        int compara = val.compareTo(raiz.valor);

        if (compara < 0) {
            raiz.esq = remove(raiz.esq, val);
        } else if (compara > 0) {
            raiz.dir = remove(raiz.dir, val);
        } else {
            if (raiz.esq == null) return raiz.dir;
            if (raiz.dir == null) return raiz.esq;
            NodoAPB sucessor = menorNodo(raiz.dir);
            raiz.valor = sucessor.valor;
            raiz.dir = remove(raiz.dir, sucessor.valor);
        }
        return raiz;
    }

    public NodoAPB menorNodo(NodoAPB atual) {
        while (atual.esq != null) {
            atual = atual.esq;
        }
        return atual;
    }
}
