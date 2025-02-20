package org.example.FilaAtendimentoPasta;

public class Senha implements Comparable<Senha> {
    private char prioridade; // Prioridade (A a F)
    private int numero;      // Número sequencial
    private long tempoChegada; // Tempo de chegada (para desempate)

    public Senha(char prioridade, int numero, long tempoChegada) {
        this.prioridade = prioridade;
        this.numero = numero;
        this.tempoChegada = tempoChegada;
    }

    public char getPrioridade() {
        return prioridade;
    }

    public int getNumero() {
        return numero;
    }

    public long getTempoChegada() {
        return tempoChegada;
    }

    @Override
    public int compareTo(Senha outra) {
        // Ordena por prioridade (A é maior que B, etc.)
        if (this.prioridade != outra.prioridade) {
            return Character.compare(outra.prioridade, this.prioridade); // Prioridade maior vem primeiro
        }
        // Em caso de empate, ordena por tempo de chegada (menor tempo vem primeiro)
        return Long.compare(this.tempoChegada, outra.tempoChegada);
    }

    @Override
    public String toString() {
        return String.format("%c%03d", prioridade, numero); // Formato: Xnnn
    }
}