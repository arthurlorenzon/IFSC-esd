package org.example;

public class CaminhoReduzido {
    public static String reduz_caminho(String caminho) {
        if (caminho == null || caminho.isEmpty()) {
            return "";
        }

        // Dividindo o caminho em partes por "/"
        String[] componentes = caminho.split("/");

        // Criando a pilha para armazenar os diretórios válidos
        Pilha pilha = new Pilha();

        for (String componente : componentes) {
            if (componente.equals("..")) {
                // ".." remove o topo se a pilha não estiver vazia
                if (!pilha.estaVazia()) {
                    pilha.desempilha();
                }
            } else if (!componente.equals(".") && !componente.isEmpty()) {
                // Diretórios válidos são empilhados
                pilha.empilha(componente);
            }
            // "." e strings vazias são ignoradas
        }

        // Recriando o caminho reduzido
        StringBuilder caminhoReduzido = new StringBuilder();

        while (!pilha.estaVazia()) {
            caminhoReduzido.insert(0, "/" + pilha.desempilha());
        }

        // Retorna "/" caso o caminho reduzido seja vazio
        return caminhoReduzido.length() > 0 ? caminhoReduzido.toString() : "/";
    }

    public static void main(String[] args) {
        // Teste de exemplos fornecidos
        String caminho1 = "/home/aluno/Downloads/..";
        String caminho2 = "/usr/share/docs/../../lib/./x11";
        String caminho3 = "/";
        String caminho4 = "/././";

        System.out.println(reduz_caminho(caminho1)); // /home/aluno
        System.out.println(reduz_caminho(caminho2)); // /usr/lib/x11
        System.out.println(reduz_caminho(caminho3)); // /
        System.out.println(reduz_caminho(caminho4)); // /
    }
}
