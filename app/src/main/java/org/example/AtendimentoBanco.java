package org.example;

import org.example.FilaAtendimento;

public class AtendimentoBanco {
    public static void main(String[] args) {
        FilaAtendimento fila = new FilaAtendimento();
        fila.adicionarCliente('A');
        fila.adicionarCliente('C');
        fila.adicionarCliente('B');
        fila.chamarProximo();
        fila.chamarProximo();
    }
}