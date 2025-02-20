package org.example;

import java.util.function.Function;

public class TabHash<K, V> {

    public class Par {
        K chave;
        V valor;

        public Par(K _chave, V _valor) {
            chave = _chave;
            valor = _valor;
        }

        public K obtemChave() {
            return chave;
        }

        public V obtemValor() {
            return valor;
        }

        @Override
        public boolean equals(Object outro) {
            Par _outro = (Par) outro;
            return _outro.equals(chave);
        }

        public void substituiValor(V _valor) {
            valor = _valor;
        }
    }

    protected ListaSequencial tab;
    Function<K, Integer> f_hash;
    final int DEFSIZE = 31;

    public TabHash(Function<K, Integer> fun_hash) {
        f_hash = fun_hash;
        tab = new ListaSequencial();
        //tab.expande(DEFSIZE);
        for (int j = 0; j < DEFSIZE; j++) {
            tab.adiciona(null);
        }
    }

    public V obtem(K chave) {
        Integer val_hash = f_hash.apply(chave);
        int posicao = val_hash % tab.comprimento();

        ListaSequencial l_pares = (ListaSequencial) tab.obtem(posicao);

//            Par par = null;
//            for (int j = 0; j < l_pares.comprimento(); j++) {
//                par = (Par)l_pares.obtem(j);
//                if (chave.equals(par.obtemChave())) {
//                    break;
//                }
//            }

        Par par = new Par(chave, null);
        int pos = l_pares.procura(par);
        if (pos >= 0) {
            par = (Par) l_pares.obtem(pos);
            return par.obtemValor();
        }

        if (par != null) {
            return par.obtemValor();
        }
        return null;
    }

    public void adiciona(K chave, V valor) {
        Integer val_hash = f_hash.apply(chave);

        int posicao = val_hash % tab.comprimento();

        Par par = new Par(chave, valor);
        ListaSequencial lista = (ListaSequencial) tab.obtem(posicao);
        int indiceCopia = lista.procura(par);
        if (indiceCopia != -1) {
            lista.substitui(indiceCopia, par);
        } else {
            lista.adiciona(par);
        }
    }


    public void remover(K chave) {
        Integer val_hash = f_hash.apply(chave);
        int posicao = val_hash % tab.comprimento();

        ListaSequencial lista = (ListaSequencial) tab.obtem(posicao);
        Par par = new Par(chave, null);

        int indice = lista.procura(par);
        if (indice != -1) {
            lista.remove(indice);
        }
    }

    public void substituir(K chave, V novoValor) {
        Integer val_hash = f_hash.apply(chave);
        int posicao = val_hash % tab.comprimento();

        ListaSequencial lista = (ListaSequencial) tab.obtem(posicao);
        Par par = new Par(chave, null);

        int indice = lista.procura(par);
        if (indice != -1) {
            Par existente = (Par) lista.obtem(indice);
            existente.substituiValor(novoValor);
        } else {
            adiciona(chave, novoValor);
        }
    }

    public ListaSequencial thash_chaves() {
        ListaSequencial chaves = new ListaSequencial();

        for (int i = 0; i < tab.comprimento(); i++) {
            ListaSequencial lista = (ListaSequencial) tab.obtem(i);
            for (int j = 0; j < lista.comprimento(); j++) {
                Par par = (Par) lista.obtem(j);
                chaves.adiciona(par.obtemChave());
            }
        }

        return chaves;
    }
}
