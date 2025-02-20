package esd;

import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

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
    int tamanho = 0;

    public void adiciona(T val) {
        tamanho++;
        if(raiz == null){
            raiz = new NodoAPB(val, null);
            return;
        }

        NodoAPB atual = raiz;
        while(true){
            int comparacao = val.compareTo(atual.valor);
            if(comparacao > 0){
                if(atual.dir == null){
                    atual.dir = new NodoAPB(val, atual);
                    return;
                }
                atual = atual.dir;
            }else{
                if(atual.esq == null) {
                    atual.esq = new NodoAPB(val, atual);
                    return;
                }
                atual = atual.esq;
            }
        }
    }

    public int tamanho() {
        return tamanho;
    }

    public boolean esta_vazia(){
        return raiz == null;
    }

    public T procura(T val) {
        if(raiz == null){
            return null;
        }

        NodoAPB atual = raiz;
        while(true){
            int comparacao = val.compareTo(atual.valor);
            if(comparacao == 0){
                return atual.valor;
            }

            if(comparacao > 0){
                if(atual.dir == null){
                    return null;
                }
                atual = atual.dir;
            }else{
                if(atual.esq == null){
                    return null;
                }
                atual = atual.esq;
            }
        }
    }

    void _emOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual == null) return;
        _emOrdem(atual.esq, lista);
        lista.adiciona(atual.valor);
        _emOrdem(atual.dir, lista);
    }

    void _preOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual == null) return;
        lista.adiciona(atual.valor);
        _preOrdem(atual.esq, lista);
        _preOrdem(atual.dir, lista);
    }

    void _posOrdem(NodoAPB atual, ListaSequencial lista) {
        if (atual == null) return;
        _posOrdem(atual.esq, lista);
        _posOrdem(atual.dir, lista);
        lista.adiciona(atual.valor);
    }

    public ListaSequencial emOrdem() {
        ListaSequencial lista = new ListaSequencial();

        if (raiz != null) _emOrdem(raiz, lista);

        return lista;
    }

    public ListaSequencial preOrdem() {
        ListaSequencial lista = new ListaSequencial();

        if (raiz != null) _preOrdem(raiz, lista);

        return lista;
    }

    public ListaSequencial posOrdem() {
        ListaSequencial lista = new ListaSequencial();

        if (raiz != null) _posOrdem(raiz, lista);

        return lista;
    }

    int _altura(NodoAPB atual) {
        int ae = 0, ad = 0;

        if(atual.esq != null) ae = 1 + _altura(atual.esq);
        if(atual.dir != null) ad = 1 + _altura(atual.dir);

        return Math.max(ae, ad);
    }

    public int altura() {
        if(raiz == null){
            return 0;
        }
        return _altura(raiz);
    }

    public int fatorB(NodoAPB atual) {
        int ae = 0, ad = 0;

        if(atual.esq != null) ae = _altura(atual.esq);
        if(atual.dir != null) ad = _altura(atual.dir);

        return ae - ad;
    }

    NodoAPB rotaciona_esquerda(NodoAPB n1){
        NodoAPB b, n2;

        n2 = n1.dir;
        b = n2.esq;
        n2.esq = n1;
        n1.dir = b;

        return n2;
    }

    NodoAPB rotaciona_direita(NodoAPB n2){
        NodoAPB b, n1;

        n1 = n2.esq;
        b = n1.dir;
        n1.dir = n2;
        n2.esq = b;

        return n1;
    }

    NodoAPB balanceia_avl(NodoAPB raiz){
        if(raiz.esq != null) raiz.esq = balanceia_avl(raiz.esq);
        if(raiz.dir != null) raiz.dir = balanceia_avl(raiz.dir);

        int fb = fatorB(raiz);

        while(fb < -1){
            if(fatorB(raiz.dir) > 0){
            raiz.dir = rotaciona_direita(raiz.dir);
            }
            raiz = rotaciona_esquerda(raiz);
            fb = fatorB(raiz);
        }

        while(fb > 1){
            if(fatorB(raiz.esq) < 0){
                raiz.esq = rotaciona_esquerda(raiz.esq);
            }
            raiz = rotaciona_direita(raiz);
            fb = fatorB(raiz);
        }

        return raiz;
    }

    void balanceia() {
        if(raiz == null){
            return;
        }
        raiz = balanceia_avl(raiz);
    }


    public T menor() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.esq != null) atual = atual.esq;
        return atual.valor;
    }

    public T maior() {
        if (raiz == null) return null;
        NodoAPB atual = raiz;
        while (atual.dir != null) atual = atual.dir;
        return atual.valor;
    }

    public T menor_que(T val) {
        NodoAPB atual = raiz, candidato = null;
        while (atual != null) {
            if (atual.valor.compareTo(val) <= 0) {
                candidato = atual;
                atual = atual.dir;
            } else {
                atual = atual.esq;
            }
        }
        return candidato != null ? candidato.valor : null;
    }

    public T maior_que(T val) {
        NodoAPB atual = raiz, candidato = null;
        while (atual != null) {
            if (atual.valor.compareTo(val) >= 0) {
                candidato = atual;
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }
        return candidato != null ? candidato.valor : null;
    }

    public T menor_igual(T val) {
        NodoAPB atual = raiz, candidato = null;
        while (atual != null) {
            if (atual.valor.compareTo(val) <= 0) {
                candidato = atual;
                atual = atual.dir;
            } else {
                atual = atual.esq;
            }
        }
        return candidato == null ? null : candidato.valor;
    }

    public T maior_igual(T val) {
        NodoAPB atual = raiz, candidato = null;
        while (atual != null) {
            if (atual.valor.compareTo(val) >= 0) {
                candidato = atual;
                atual = atual.esq;
            } else {
                atual = atual.dir;
            }
        }
        return candidato == null ? null : candidato.valor;
    }

    public ListaSequencial menores_que(T val) {
        ListaSequencial lista = new ListaSequencial();
        _menores_que(raiz, val, lista);
        return lista;
    }

    private void _menores_que(NodoAPB atual, T val, ListaSequencial lista) {
        if (atual == null) return;

        int comparacao = atual.valor.compareTo(val);
        if (comparacao <= 0) {
            lista.adiciona(atual.valor);
            _menores_que(atual.esq, val, lista);
            _menores_que(atual.dir, val, lista);
        } else{
            _menores_que(atual.esq, val, lista);
        }
    }


    public ListaSequencial maiores_que(T val) {
        ListaSequencial lista = new ListaSequencial();
        _maiores_que(raiz, val, lista);
        return lista;
    }

    private void _maiores_que(NodoAPB atual, T val, ListaSequencial lista) {
        if (atual == null) return;

        int comparacao = atual.valor.compareTo(val);
        if (comparacao >= 0) {
            _maiores_que(atual.esq, val, lista);
            lista.adiciona(atual.valor);
            _maiores_que(atual.dir, val, lista);
        } else {
            _maiores_que(atual.dir, val, lista);
        }
    }

    public ListaSequencial faixa(T inf, T sup) {
        ListaSequencial lista = new ListaSequencial();
        _faixa(raiz, inf, sup, lista);
        return lista;
    }

    private void _faixa(NodoAPB atual, T inf, T sup, ListaSequencial lista) {
        if (atual == null) return;
        if (atual.valor.compareTo(inf) >= 0 && atual.valor.compareTo(sup) <= 0) {
            _faixa(atual.esq, inf, sup, lista);
            lista.adiciona(atual.valor);
            _faixa(atual.dir, inf, sup, lista);
        } else if (atual.valor.compareTo(inf) < 0) {
            _faixa(atual.dir, inf, sup, lista);
        } else {
            _faixa(atual.esq, inf, sup, lista);
        }
    }

    public ListaSequencial emLargura() {
        ListaSequencial lista = new ListaSequencial();
        if (raiz == null) return lista;

        Fila fila = new Fila();
        fila.adiciona(raiz);

        while (!fila.estaVazia()) {
            NodoAPB atual = (NodoAPB) fila.remove();
            lista.adiciona(atual.valor);

            if (atual.esq != null) fila.adiciona(atual.esq);
            if (atual.dir != null) fila.adiciona(atual.dir);
        }

        return lista;
    }

}