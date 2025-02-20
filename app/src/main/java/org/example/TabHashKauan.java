package esd;

import java.util.function.Function;

public class TabHash <K, V> {

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

        public void substituiValor(V _valor) {
            valor = _valor;
        }

        @Override
        public boolean equals(Object obj) {
            Par comparado = (Par) obj;
            return comparado.chave.equals(chave);
        }
    }
    ListaSequencial tab;
    Function<K, Integer> f_hash;
    int DEFSIZE = 31;
    int n = 0;

    public TabHash (Function<K, Integer> fun_hash) {
        f_hash = fun_hash;
        tab = new ListaSequencial();
        for (int j = 0; j < DEFSIZE; j++) {
            tab.adiciona(new ListaSequencial());
        }
    }

    public V obtem(K chave) {
        Integer val_hash = f_hash.apply(chave);

        ListaSequencial lista =  (ListaSequencial) tab.obtem(val_hash % tab.comprimento());
        Par par = new Par(chave, null);
        int indice = lista.procura(par);
        if(contem(chave)){
            par = (Par) lista.obtem(indice);
            return par.obtemValor();
        }
        return null;
    }

    public boolean contem(K chave){
        Integer val_hash = f_hash.apply(chave);
        ListaSequencial lista =  (ListaSequencial) tab.obtem(val_hash % tab.comprimento());
        Par par = new Par(chave, null);
        int indice = lista.procura(par);
        return indice != -1;
    }

    public void adiciona(K chave, V valor) {
        Integer val_hash = f_hash.apply(chave);
        Par par = new Par(chave, valor);
        ListaSequencial lista = (ListaSequencial) tab.obtem(val_hash % tab.comprimento());
        int indiceCopia = lista.procura(par);
        if(indiceCopia != -1){
            lista.substitui(indiceCopia, par);
        }else{
            lista.adiciona(par);
            n++;
        }
    }
    public void remove(K chave) {
        adiciona(chave, null);
        n--;
    }

    public ListaSequencial chaves() {
        ListaSequencial result = new ListaSequencial();
        for (int j=0; j < tab.comprimento(); j++) {
            var lista = (ListaSequencial)tab.obtem(j);
            for (int k=0; k < lista.comprimento(); k++) {
                Par par = (Par) lista.obtem(k);
                result.adiciona(par.obtemChave());
            }
        }
        return result;
    }

    public ListaSequencial valores() {
        ListaSequencial result = new ListaSequencial();
        for (int j=0; j < tab.comprimento(); j++) {
            var lista = (ListaSequencial)tab.obtem(j);

            for (int k=0; k < lista.comprimento(); k++) {
                Par par = (Par) lista.obtem(k);
                result.adiciona(par.obtemValor());
            }
        }
        return result;
    }

    public ListaSequencial items(){
        ListaSequencial result = new ListaSequencial();

        for (int j = 0; j < tab.comprimento(); j++){
            var lista =  (ListaSequencial)tab.obtem(j);
            for(int k = 0; k< lista.comprimento(); k++){
                Par par = (Par) lista.obtem(k);
                result.adiciona(par);
            }
        }
        return result;
    }
    public boolean esta_vazia(){
        return n == 0;
    }
    public int comprimento(){
        return n;
    }

    public V obtem_ou_default(K chave, V defval){
        V obteve = obtem(chave);
        if(obteve != null){
            return obteve;
        }
        return defval;
    }

}
