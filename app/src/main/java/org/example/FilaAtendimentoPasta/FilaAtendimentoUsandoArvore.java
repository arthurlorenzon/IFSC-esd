package org.example.FilaAtendimentoPasta;

import esd.APB;
import org.example.ListaSequencial;

public class FilaAtendimentoUsandoArvore {
    private APB<Senha> arvore; // Árvore para armazenar as senhas
    private int contadorClientes; // Contador de clientes

    public FilaAtendimentoUsandoArvore() {
        arvore = new APB<>();
        contadorClientes = 0;
    }

    // Adiciona uma nova senha à fila
    public void adicionarSenha(char prioridade) {
        contadorClientes++;
        Senha senha = new Senha(prioridade, contadorClientes, System.nanoTime());
        arvore.adiciona(senha);
        System.out.println("Senha gerada: " + senha);
        System.out.println("Clientes na frente: " + (arvore.emOrdem().comprimento() - 1));
    }

    // Atende o próximo cliente
    public void atenderProximo() {
        if (arvore.esta_vazia()) {
            System.out.println("Nenhum cliente na fila.");
        } else {
            Senha proximaSenha = arvore.menor(); // Obtém a senha de maior prioridade
            arvore.remove(proximaSenha); // Remove a senha da árvore
            System.out.println("Atendendo: " + proximaSenha);
        }
    }

    // Encerra o sistema
    public void encerrar() {
        System.out.println("Sistema encerrado.");
        System.exit(0);
    }
}