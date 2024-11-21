package org.example;

public class ListaSimples <T>{


    class Nodo{
        T valor;
        Nodo proximo;
        Nodo antecessor;

        //quando uma váriavel recebe this, indica que referencia ao proprio objeto
        public Nodo(){
            proximo = this;
            antecessor = this;
        }

        public Nodo(T valor){
            this.valor = valor;
            this.proximo = null;
        }
    }

    Nodo primeiro = null;
    Nodo ultimo = null;
    int len = 0;

    public ListaSimples() {}

    public int comprimento(){return len;}

    public boolean esta_vazia(){return len == 0;}

    public void adiciona(T valor){
        Nodo nodo = new Nodo(valor);
        if (esta_vazia()){
            primeiro = nodo;
        }else {
            ultimo.proximo = nodo;
        }

        ultimo = nodo;
        len++;
    }


    public T obtem(int indice){
        if(indice < 0 || indice >= len){
            throw new RuntimeException("indice invalido");
        }

        Nodo nodo = primeiro;
        while (indice-- > 0){
            nodo = nodo.proximo;
        }

        return nodo.valor;
    }

    public T obtem_ultimo(){
        if(!esta_vazia()){
            return obtem(len-1);
        }else{
            throw new RuntimeException("Lista vazia");
        }
    }
    public Nodo obtem_nodo(int indice){
        Nodo nodo = primeiro;
        while (indice-- > 0){
            nodo = nodo.proximo;
        }
        return nodo;
    }

    public void insere(int indice, T valor){
        Nodo nodo = new Nodo(valor);

        if(indice == len){
            adiciona(valor);
            return;
        }
        if(indice < 0 || indice >= len){
            throw new RuntimeException("indice invalido");
        } else if (indice == 0) {
            nodo.proximo = primeiro;
            primeiro = nodo;
        }else{
            Nodo previo = obtem_nodo(indice - 1);
            nodo.proximo = previo.proximo;
            previo.proximo = nodo;
        }

        len++;
    }

    public void remove(int indice){
        if(indice < 0 || indice >= len){
            throw new RuntimeException("indice invalido");
        }else if(indice == 0){
            primeiro = primeiro.proximo;
            if (primeiro == null){
                ultimo = null;
            }
        }else{
            Nodo nodoAnterior = obtem_nodo(indice - 1);
            Nodo atual = obtem_nodo(indice);
            nodoAnterior.proximo = atual.proximo;
            if(atual == ultimo){
                ultimo = nodoAnterior;
            }
        }
        len--;
    }

    public int procura(T valor){
        for (int i = 0; i < len; i++) {
            if(obtem_nodo(i).valor.equals(valor)){
                return i;
            }
        }
        return -1;
    }

    public void substitui(int indice, T valor){
        if(indice < 0 || indice >= len){
            throw new RuntimeException("indice invalido");
        }
        Nodo nodo = obtem_nodo(indice);
        nodo.valor = valor;
    }

    public void inverte() {
        if (esta_vazia() || len == 1) {
            return;
        }

        Nodo atual = primeiro;
        Nodo anterior = null;
        Nodo proximo;

        while (atual != null) {
            proximo = atual.proximo;
            atual.proximo = anterior;
            anterior = atual;
            atual = proximo;
        }

        Nodo temp = primeiro;
        primeiro = ultimo;
        ultimo = temp;
    }


    public void limpa(){
        primeiro = null;
        ultimo = null;
        len = 0;
    }
}