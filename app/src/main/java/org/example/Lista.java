package esd;

import java.security.InvalidParameterException;

public class Lista<T> {
    class Nodo{

        T valor;
        Nodo proximo;
        Nodo antecessor;

        public Nodo(){
            proximo = this;
            antecessor = this;
        }

        public Nodo(T valor){
            proximo = this;
            antecessor = this;
            this.valor = valor;
        }

        public void conecta(Nodo sucessor){
            this.proximo = sucessor;
            this.antecessor = sucessor.antecessor;
            sucessor.antecessor.proximo = this;
            sucessor.antecessor = this;
        }

        public void desconecta(){
            this.antecessor.proximo = this.proximo;
            this.proximo.antecessor = this.antecessor;
        }
    }

    Nodo guarda;
    int len = 0;


    public Lista(){
        guarda = new Nodo();
    }

    public void adiciona(T valor){
        Nodo nodo = new Nodo(valor);

        nodo.conecta(guarda);
        len++;
    }

    public void insere(int indice, T valor) throws InvalidParameterException {
        if(indice < 0 || indice > len){
            throw new InvalidParameterException("Indice");
        }
        Nodo nodo = new Nodo(valor);
        Nodo sucessor = obtem_nodo(indice);
        nodo.conecta(sucessor);
        len++;
    }

    public Nodo obtem_nodo(int indice){
        Nodo nodo;
        if (indice < len / 2) {
            nodo = guarda.proximo;
            while (indice-- > 0) {
                nodo = nodo.proximo;
            }
        } else {
            nodo = guarda;
            int passos = len - indice;
            while (passos-- > 0) {
                nodo = nodo.antecessor;
            }
        }
        return nodo;
    }

    public int procura(T valor) {
        Nodo atual = guarda.proximo;
        int indice = 0;

        while (atual != guarda) {
            if (atual.valor.equals(valor)) {
                return indice;
            }
            atual = atual.proximo;
            indice++;
        }

        return -1;
    }

    public boolean esta_vazia(){
        return len == 0;
    }

    public int comprimento(){
        return len;
    }

    public T obtem(int indice){
        if (indice < 0 || indice >= len) {
            throw new RuntimeException("Índice inválido");
        }
        return obtem_nodo(indice).valor;
    }

    public T obtem_primeiro(){
        return guarda.proximo.valor;
    }

    public T obtem_ultimo(){
        return guarda.antecessor.valor;
    }

    public void remove(int indice){
        if (indice < 0 || indice >= len) {
            throw new InvalidParameterException("Índice inválido");
        }
        Nodo nodo = obtem_nodo(indice);
        nodo.desconecta();
        len--;
    }

    public void substitui(int indice, T valor){
        if (indice < 0 || indice >= len) {
            throw new InvalidParameterException("Índice inválido");
        }
        Nodo nodo = obtem_nodo(indice);
        nodo.valor = valor;
    }

    public void limpa() {
        guarda = new Nodo();
        len = 0;
    }

    public void inverte(){
        Nodo nodoAtual = guarda;
        do {
            Nodo temp = nodoAtual.proximo;
            nodoAtual.proximo = nodoAtual.antecessor;
            nodoAtual.antecessor = temp;
            nodoAtual = temp;
        } while (nodoAtual != guarda);
    }

    public void ordena() {
        if (len <= 1) {
            return;
        }

        boolean trocou;
        do {
            trocou = false;
            Nodo atual = guarda.proximo;

            while (atual.proximo != guarda) {
                Nodo proximo = atual.proximo;


                if (((Comparable<T>) atual.valor).compareTo(proximo.valor) > 0) {

                    T temp = atual.valor;
                    atual.valor = proximo.valor;
                    proximo.valor = temp;

                    trocou = true;
                }

                atual = atual.proximo;
            }
        } while (trocou);
    }

}