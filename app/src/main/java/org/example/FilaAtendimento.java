package org.example;

class Cliente implements Comparable<Cliente> {
    String senha;
    int prioridade;
    int ordemChegada;

    Cliente(String senha, int prioridade, int ordemChegada) {
        this.senha = senha;
        this.prioridade = prioridade;
        this.ordemChegada = ordemChegada;
    }

    @Override
    public int compareTo(Cliente outro) {
        if (this.prioridade != outro.prioridade) {
            return Integer.compare(outro.prioridade, this.prioridade);
        }
        return Integer.compare(this.ordemChegada, outro.ordemChegada);
    }
}

public class FilaAtendimento {
    private APB<Cliente> arvore;
    private int contador;

    public FilaAtendimento() {
        this.arvore = new APB<>();
        this.contador = 0;
    }

    public void adicionarCliente(char categoria) {
        String senha = categoria + String.format("%03d", ++contador);
        int prioridade = 'F' - categoria;
        Cliente novoCliente = new Cliente(senha, prioridade, contador);
        arvore.adiciona(novoCliente);
        System.out.println("Senha gerada: " + senha);
    }

    public void chamarProximo() {
        Cliente proximo = arvore.menorNodo();
        if (proximo == null) {
            System.out.println("Fila vazia");
            return;
        }
        System.out.println("Chamando: " + proximo.senha);
        arvore.remove(proximo);
    }
}


