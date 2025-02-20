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

    public T procura(T val) {
        NodoAPB atual = this.raiz;

        while (atual != null) {
            int compara = val.compareTo(atual.valor);

            if (compara == 0) {
                return atual.valor;
            } else if (compara < 0) {
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }
        return null;
    }

    void _emOrdem(NodoAPB atual, ListaSequencial lista) {
        // aqui se faz a enumeração com abordagem recursiva
        if (atual.esq != null) _emOrdem(atual.esq, lista);
        lista.adiciona(atual.valor);
        if (atual.dir != null) _emOrdem(atual.dir, lista);
    }

    void _preOrdem(NodoAPB atual, ListaSequencial lista) {
        lista.adiciona(atual.valor);
        if (atual.esq != null) _preOrdem(atual.esq, lista);
        if (atual.dir != null) _preOrdem(atual.dir, lista);
    }

    void _posOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual.esq != null) _posOrdem(atual.esq, lista);
        if (atual.dir != null) _posOrdem(atual.dir, lista);
        lista.adiciona(atual.valor);
    }

    public ListaSequencial posOrdem() {
        // aqui apenas cria-se uma lista, onde os valores enumerados serão armazenados
        ListaSequencial lista = new ListaSequencial();

        // aqui se faz de fato a enumeração a partir da raiz, guardando na lista os valores acessados
        if (raiz != null) _posOrdem(raiz, lista);

        return lista;
    }

    public ListaSequencial preOrdem() {
        // aqui apenas cria-se uma lista, onde os valores enumerados serão armazenados
        ListaSequencial lista = new ListaSequencial();

        // aqui se faz de fato a enumeração a partir da raiz, guardando na lista os valores acessados
        if (raiz != null) _preOrdem(raiz, lista);

        return lista;
    }

    public ListaSequencial emOrdem() {
        // aqui apenas cria-se uma lista, onde os valores enumerados serão armazenados
        ListaSequencial lista = new ListaSequencial();

        // aqui se faz de fato a enumeração a partir da raiz, guardando na lista os valores acessados
        if (raiz != null) _emOrdem(raiz, lista);

        return lista;
    }


    public ListaSequencial preOrdemNR() {
        ListaSequencial lista = new ListaSequencial();

        if (raiz != null) {
            Pilha pilha = new Pilha();
            pilha.empilha(raiz);

            while (!pilha.estaVazia()) {
                NodoAPB nodo = (NodoAPB) pilha.desempilha();

                lista.adiciona(nodo.valor);

                if(nodo.dir != null) pilha.empilha(nodo.dir);
                if(nodo.esq != null) pilha.empilha(nodo.esq);
            }
        }

        return lista;
    }

    int _altura(NodoAPB atual) {
        // aqui se faz de fato o cálculo da altura
        if (atual.esq == null && atual.dir == null) {
            return 0;
        }
        int he = 0, hd = 0;
        if (atual.esq != null) he = 1 + _altura(atual.esq);
        if (atual.dir != null) hd = 1 + _altura(atual.dir);

        return Math.max(he, hd);
    }

    public int altura() {
        // aqui prepara-se para calcular a altura a partir da raiz
        if (raiz != null) {
            return _altura(raiz);
        }

        // uma árvore vazia tem altura ???
        return 0;
    }

    int fatorBalanceamento(NodoAPB atual) {
        int he=0, hd=0;

        if (atual.esq != null) he = _altura(atual.esq);
        if (atual.dir != null) hd = _altura(atual.dir);
        return he - hd;
    }

    // Rotação esquerda
    NodoAPB rotaciona_esquerda(NodoAPB n1) {
        // aqui se faz a rotação esquerda. Ao final, deve-se retornar o novo nodo que representa a raiz da árvore
        // rotacionada
        NodoAPB b, n2;

        n2 = n1.dir;
        b = n2.dir;
        n2.esq = n1;
        n1.dir = b;

        return n2;
    }

    // Rotação direita
    NodoAPB rotaciona_direita(NodoAPB n2) {
        // aqui se faz a rotação direita. Ao final, deve-se retornar o novo nodo que representa a raiz da árvore
        // rotacionada
        NodoAPB b, n1;

        n1 = n2.esq;
        b = n1.esq;
        n1.dir = n2;
        n2.esq = b;

        return n1;
    }

    // Método para balancear a árvore
    public void balanceia() {
        if (raiz != null) {
            // realiza o balanceamento da árvore. A raiz é modificada pelo algoritmo, portanto precisa
            // ser atualizada
            raiz = balanceia_avl(raiz);
        }
    }

    // Este método implementa de fato o balanceamento AVL
    NodoAPB balanceia_avl(NodoAPB raiz) {
        if(raiz.esq != null) balanceia_avl(raiz.esq);
        if(raiz.dir != null) balanceia_avl(raiz.dir);

        int fb = fatorBalanceamento(raiz);

        while(fb < -1){
            if(fatorBalanceamento(raiz.dir) > 0){
                raiz = rotaciona_direita(raiz);
            }
            raiz = rotaciona_esquerda(raiz);
            fb = fatorBalanceamento(raiz);
        }

        while(fb > 1){
            if(fatorBalanceamento(raiz.esq) < 0){
                raiz = rotaciona_esquerda(raiz);
            }
            raiz = rotaciona_direita(raiz);
            fb = fatorBalanceamento(raiz);
        }

        return raiz;
    }

    public T menor() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.esq != null) {
            atual = atual.esq;
        }
        return atual.valor;
    }

    public T maior() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.dir != null) {
            atual = atual.dir;
        }
        return atual.valor;
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